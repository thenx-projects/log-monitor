







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
