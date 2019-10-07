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

package org.thenx.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.thenx.common.exception.ExceptionExplain;
import org.thenx.common.exception.QueryException;

/**
 * @author May
 * <p>
 * 判断当前的操作系统
 */
@Slf4j
@Configuration
public class SystemType {

    /**
     * 判断当前系统是 Windows 还是 Linux
     */
    public static String SYSTEM_OS = "Windows";

    /**
     * 默认系统路径
     */
    public static String DEFAULT_LOCATION = "";

    static {
        log.info("\n ------> right here the system os is: " + System.getProperties().getProperty("os.name"));
        log.info("\n ------> right here the file type is : " + System.getProperties().getProperty("file.separator"));
        String system = System.getProperties().getProperty("file.separator");
        if ("\\".equals(system)) {
            String property = System.getProperty("user.name");
            SYSTEM_OS = "Windows";
            DEFAULT_LOCATION = "C:\\Users\\" + property + "\\Documents\\";
        } else if ("/".equals(system)) {
            SYSTEM_OS = "Linux";
        } else {
            throw new QueryException(ExceptionExplain.ERROR_BY_QUERY.getExplain());
        }
    }
}
