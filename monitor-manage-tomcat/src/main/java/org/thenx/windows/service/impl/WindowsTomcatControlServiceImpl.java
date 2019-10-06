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
