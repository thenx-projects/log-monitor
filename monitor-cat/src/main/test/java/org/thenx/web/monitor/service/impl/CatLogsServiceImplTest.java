/*-
 * <<
 * log-monitor
 * >
 * Copyright (C) 2019 thenx
 * >
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * >>
 */




package org.thenx.windows.monitor.service.impl;

import org.thenx.windows.monitor.service.CatLogsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class CatLogsServiceImplTest {

    @Resource
    private CatLogsService catLogsService;

    @Test
    public void catCatalina() {
        String location = "C:\\Users\\May\\Downloads\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\logs\\catalina.2019-08-06.log";
        String file = "catalina.2019-08-06.log";
        File file1 = new File(location);
        long time = file1.lastModified();//返回文件最后修改时间，是以个long型毫秒数
        String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file1.lastModified()));

        log.info("\n ----> data: " + ctime);
//        log.info("\n ----> data: " + catLogsService.catLogs(location, file));
    }

    @Test
    public void catHostManager() {
    }

    @Test
    public void catLocalhost() {
    }

    @Test
    public void catLocalhostAccessLog() {
    }

    @Test
    public void catManager() {
    }

    @Test
    public void findAll() {
        log.info("\n --> " +
                catLogsService.findAll("C:\\Users\\May\\Downloads\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\logs"));
    }

    @Test
    public void getCreateDate() {
        String filePath = "C:\\test.txt";
        String strTime = null;
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir "
                    + filePath
                    + "/tc");
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.endsWith(".txt")) {
                    strTime = line.substring(0, 17);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("创建时间    " + strTime);
    }
}
