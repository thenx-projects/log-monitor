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

package org.thenx.common.properties;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author May
 *
 * 公共参数类
 */
@Slf4j
public class Properties implements Serializable {

    /**
     * 线上请求接口
     */
    public static String BASE_REQUEST_PROD = "";

    static {
        java.util.Properties properties = new java.util.Properties();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("request.properties");

        try {
            properties.load(Objects.requireNonNull(resourceAsStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE_REQUEST_PROD = (String) properties.get("base_request");
    }
}
