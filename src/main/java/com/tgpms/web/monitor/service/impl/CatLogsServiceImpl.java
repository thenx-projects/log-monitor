package com.tgpms.web.monitor.service.impl;

import com.tgpms.web.monitor.service.CatLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author May
 * <p>
 * 日志查询相关接口实现
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class CatLogsServiceImpl implements CatLogsService {

    @Override
    public List<String> catCatalina() {
        return null;
    }

    @Override
    public List<String> catHostManager() {
        return null;
    }

    @Override
    public List<String> catLocalhost() {
        return null;
    }

    @Override
    public List<String> catLocalhostAccessLog() {
        return null;
    }

    @Override
    public List<String> catManager() {
        return null;
    }
}
