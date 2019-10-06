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
