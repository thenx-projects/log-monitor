package org.thenx.windows.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thenx.windows.service.WindowsTomcatControlService;

import java.util.List;

/**
 * @author May
 *
 * Tomcat 操作相关接口实现
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class WindowsTomcatControlServiceImpl implements WindowsTomcatControlService {

    /**
     * 传入 Tomcat 路径
     *
     * @param location tomcat 路径
     * @return x
     */
    @Override
    public List<String> startTomcat(String location) {
        return null;
    }

    /**
     * 传入Tomcat路径直接重启操作
     *
     * @param location 路径
     * @return x
     */
    @Override
    public boolean rebootTomcat(String location) {
        return false;
    }
}
