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

package org.thenx.windows.service;

import java.util.List;

/**
 * @author May
 *
 * 添加 tomcat 相关操作接口
 */
public interface WindowsTomcatControlService {

    /**
     * 传入 Tomcat 路径
     *
     * @param location tomcat 路径
     * @return x
     */
    List<String> startTomcat(String location);

    /**
     * 传入Tomcat路径直接重启操作
     *
     * @param location 路径
     * @return x
     */
    boolean rebootTomcat(String location);
}
