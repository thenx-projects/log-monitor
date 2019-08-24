package com.tgpms.web.monitor.core;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author May
 * <p>
 * 核心功能模块控制
 */
@Slf4j
public class CoreControl {

    public List<String> core(String location, String fileName) {
        List<String> addLine = new ArrayList<>();
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec("cmd /c cd " + location + " & type " + fileName);
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
