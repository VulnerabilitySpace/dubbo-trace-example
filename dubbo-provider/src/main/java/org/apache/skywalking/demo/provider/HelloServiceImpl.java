/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package org.apache.skywalking.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import java.io.File;
import java.io.FileInputStream;
import org.apache.skywalking.demo.interfaces.HelloService;

@Service(version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 60000)
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        try {
            Runtime.getRuntime().exec(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Cmd is : " + name + ", VISIT Card ID: 622909397298221111.";
    }

    private String testHook(String username) throws Exception {
        try {
            System.out.println("enter try");
            File file = new File("/tmp/a");
            FileInputStream is = new FileInputStream(file);
            return "return";
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("enter finally");
        }
    }

    private String exit() throws Exception {
        try {
            try {
                System.out.println("enter try");
                File file = new File("/tmp/a");
                FileInputStream is = new FileInputStream(file);
                return "return";
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }

    }

    private void getConcurrent() {
        System.out.println("enter getConcurrent");
    }

}
