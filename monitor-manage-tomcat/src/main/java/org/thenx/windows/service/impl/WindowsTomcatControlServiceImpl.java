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

package org.thenx.windows.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thenx.windows.WindowsControl;
import org.thenx.windows.service.WindowsTomcatControlService;

import java.io.IOException;
import java.util.List;

/**
 * @author May
 *
 * Tomcat 操作相关接口实现
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
@Slf4j
public class WindowsTomcatControlServiceImpl implements WindowsTomcatControlService {

    /**
     * 传入 Tomcat 路径
     *
     * @param location tomcat 路径
     * @return x
     */
    @Override
    public List<String> startTomcat(String location) {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec("cmd /c cd " + location + " & catalina.bat start");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new WindowsControl().coreExe(p);
    }

    /**
     * 传入Tomcat路径直接重启操作
     *
     * @param location 路径
     * @return x
     */
    @Override
    public boolean rebootTomcat(String location) {
        List<String> list = this.startTomcat(location);
        log.info("\n ----> tomcat start command: " + list);
        return list.size() >= 1;
    }
}
