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


package org.thenx.linux;

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
 * Linux 对 Tomcat的操作
 */
@Slf4j
public class LinuxTomcatControl {

    public void rebootTomcat(String tomcatLocation) {
        core(tomcatLocation);
    }

    private List<String> core(String location) {
        List<String> addLine = new ArrayList<>();
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec("./ " + location + "./startup.sh start");
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
