package org.apache.skywalking.demo.provider;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.monitor.Monitor;
import com.alibaba.dubbo.monitor.MonitorFactory;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.support.RpcUtils;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Activate(
        group = {"provider", "consumer"}
)
public class MonitorFilterCopy implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(com.alibaba.dubbo.monitor.support.MonitorFilter.class);
    private final ConcurrentMap<String, AtomicInteger> concurrents = new ConcurrentHashMap();
    private MonitorFactory monitorFactory;

    public MonitorFilterCopy() {
    }

    public void setMonitorFactory(MonitorFactory monitorFactory) {
        this.monitorFactory = monitorFactory;
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        System.out.println("enter sink");
        if (invoker.getUrl().hasParameter("monitor")) {
            RpcContext context = RpcContext.getContext();
            String remoteHost = context.getRemoteHost();
            long start = System.currentTimeMillis();
            this.getConcurrent(invoker, invocation).incrementAndGet();

            Result var8;
            try {
                Result result = invoker.invoke(invocation);
                this.collect(invoker, invocation, result, remoteHost, start, false);
                var8 = result;
                System.out.println("leave sink");
            } catch (RpcException var12) {
                this.collect(invoker, invocation, (Result) null, remoteHost, start, true);
                System.out.println("leave sink");
                throw var12;
            } finally {
                this.getConcurrent(invoker, invocation).decrementAndGet();
            }

            return var8;
        } else {
            System.out.println("leave sink");
            return invoker.invoke(invocation);
        }
    }

    public Result invoke2(Invoker<?> invoker, Invocation invocation) {
        if (invoker.getUrl().hasParameter("monitor")) {
            RpcContext context = RpcContext.getContext();
            String remoteHost = context.getRemoteHost();
            long start = System.currentTimeMillis();
            this.getConcurrent(invoker, invocation).incrementAndGet();

            Result var8;
            try {
                Result result = invoker.invoke(invocation);
                this.collect(invoker, invocation, result, remoteHost, start, false);
                var8 = result;
            } catch (RpcException var12) {
                this.collect(invoker, invocation, (Result) null, remoteHost, start, true);
                throw var12;
            } finally {
                this.getConcurrent(invoker, invocation).decrementAndGet();
            }

            return var8;
        } else {
            return invoker.invoke(invocation);
        }
    }


    private void collect(Invoker<?> invoker, Invocation invocation, Result result, String remoteHost, long start,
            boolean error) {
        try {
            long elapsed = System.currentTimeMillis() - start;
            int concurrent = this.getConcurrent(invoker, invocation).get();
            String application = invoker.getUrl().getParameter("application");
            String service = invoker.getInterface().getName();
            String method = RpcUtils.getMethodName(invocation);
            String group = invoker.getUrl().getParameter("group");
            String version = invoker.getUrl().getParameter("version");
            URL url = invoker.getUrl().getUrlParameter("monitor");
            Monitor monitor = this.monitorFactory.getMonitor(url);
            if (monitor == null) {
                return;
            }

            int localPort;
            String remoteKey;
            String remoteValue;
            if ("consumer".equals(invoker.getUrl().getParameter("side"))) {
                localPort = 0;
                remoteKey = "provider";
                remoteValue = invoker.getUrl().getAddress();
            } else {
                localPort = invoker.getUrl().getPort();
                remoteKey = "consumer";
                remoteValue = remoteHost;
            }

            String input = "";
            String output = "";
            if (invocation.getAttachment("input") != null) {
                input = invocation.getAttachment("input");
            }

            if (result != null && result.getAttachment("output") != null) {
                output = result.getAttachment("output");
            }

            monitor.collect(new URL("count", NetUtils.getLocalHost(), localPort, service + "/" + method,
                    new String[]{"application", application, "interface", service, "method", method, remoteKey,
                            remoteValue, error ? "failure" : "success", "1", "elapsed", String.valueOf(elapsed),
                            "concurrent", String.valueOf(concurrent), "input", input, "output", output, "group", group,
                            "version", version}));
        } catch (Throwable var23) {
            logger.error("Failed to monitor count service " + invoker.getUrl() + ", cause: " + var23.getMessage(),
                    var23);
        }

    }

    private AtomicInteger getConcurrent(Invoker<?> invoker, Invocation invocation) {
        String key = invoker.getInterface().getName() + "." + invocation.getMethodName();
        AtomicInteger concurrent = (AtomicInteger) this.concurrents.get(key);
        if (concurrent == null) {
            this.concurrents.putIfAbsent(key, new AtomicInteger());
            concurrent = (AtomicInteger) this.concurrents.get(key);
        }

        return concurrent;
    }
}
