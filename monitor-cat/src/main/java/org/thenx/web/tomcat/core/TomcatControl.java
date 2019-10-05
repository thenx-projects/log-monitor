






package org.thenx.web.tomcat.core;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class TomcatControl {

    public void rebootTomcat(String tomcatLocation) {
        core(tomcatLocation);
    }

    private List<String> core(String location) {
        List<String> addLine = new ArrayList<>();
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec("cmd /c cd " + location + " & catalina.bat start");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                addLine.add(new String(Objects.requireNonNull(line).getBytes(), "GBK"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addLine;
    }
}
