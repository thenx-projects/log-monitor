package org.thenx.web.monitor.core;

/**
 * @author X.Y
 * @classname OS
 * @date 2019/9/9 0009 下午 6:41
 */
public class OS {

    public static final String WINDOWS_ENCODE = "GBK";
    public static final String LINUX_ENCODE = "UTF-8";

    private OS(){
        // 私有构造方法必须要加上不被支持的操作异常
        throw new UnsupportedOperationException();
    }

    /**
     * <p>判断系统平台是否是windows平台</p>
     *
     * @return
     */
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

    /**
     * <p>
     *     获取系统平台名称
     * </p>
     * @return
     */
    public static String platform(){
        return System.getProperty("os.name");
    }
}
