package org.apache.skywalking.demo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import java.util.HashMap;
import java.util.Map;
import org.apache.skywalking.demo.interfaces.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    private static int COUNT = 0;

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 60000)
    private HelloService helloService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable(name = "name") String name) {
        System.out.println("Enter MonitorFilter Invoke");
        //LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        // -Ddongtai.app.create=true -Ddongtai.app.name=Dubbo-Trace-Consumer -javaagent:/tmp/agent.jar -Diast.dump.class.enable=false -Ddongtai.debug=true -Dlog.level=info
        return helloService.sayHello(name);
    }

    @GetMapping("/gzip/json")
    @ResponseBody
    public Map<String, String> sayGzipJson() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("a", "1");
        result.put("b", "2");
        return result;
    }

    @GetMapping("/gzip/html")
    @ResponseBody
    public String sayGzipHtml() {
        return "this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.this is a gzip html result.";
    }

    private static void enterDubbo(String namespace) {
        System.out.println(namespace);
    }

    private static boolean isFirstLevelDubbo(String namespace) {
        return false;
    }

    private static void onBefore(String namespace) {
        System.out.println(namespace);
    }

}
