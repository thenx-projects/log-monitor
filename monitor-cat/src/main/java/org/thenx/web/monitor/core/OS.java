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







package org.thenx.web.monitor.core;

public class OS {

    public static final String WINDOWS_ENCODE = "GBK";
    public static final String LINUX_ENCODE = "UTF-8";

    private OS(){
        // 私有构造方法必须要加上不被支持的操作异常
        throw new UnsupportedOperationException();
    }

    public static boolean isWindows(){
        if (platform().toLowerCase().startsWith("win")){
            return true;
        }
        return false;
    }

    public static String getEncodeByPlatform(){
        if (isWindows()){
            return WINDOWS_ENCODE;
        }
        return LINUX_ENCODE;
    }

    public static String platform(){
        return System.getProperty("os.name");
    }
}
