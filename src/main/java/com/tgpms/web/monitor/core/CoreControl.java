package com.tgpms.web.monitor.core;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author May
 * <p>
 * 核心功能模块控制
 */
@Slf4j
public class CoreControl {

    public String core() {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        String data = null;
        try {
            p = rt.exec("cmd /k cd C:\\Users\\May\\Downloads\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\logs & " +
                    "type catalina.2019-08-06.log");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(p).getInputStream()));
        String line = null;
        while (true) {
            try {
                if ((line = br.readLine()) == null) {
                    break;
                }
                data = new String(line.getBytes(), "GBK");
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("=============> over");
        return data;
    }
}
