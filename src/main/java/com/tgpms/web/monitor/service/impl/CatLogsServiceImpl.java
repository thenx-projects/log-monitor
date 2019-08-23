package com.tgpms.web.monitor.service.impl;

import com.tgpms.web.monitor.service.CatLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author May
 * <p>
 * 日志查询相关接口实现
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class CatLogsServiceImpl implements CatLogsService {

    /**
     * 查询 catalina 日志
     *
     * @return null
     */
    @Override
    public List<String> catCatalina() {
        return null;
    }

    /**
     * 查询 hostManager 日志
     *
     * @return null
     */
    @Override
    public List<String> catHostManager() {
        return null;
    }

    /**
     * 查询 localhost 日志
     *
     * @return null
     */
    @Override
    public List<String> catLocalhost() {
        return null;
    }

    /**
     * 查询 localhostAccessLog 日志
     *
     * @return null
     */
    @Override
    public List<String> catLocalhostAccessLog() {
        return null;
    }

    /**
     * 查询 manager 日志
     *
     * @return null
     */
    @Override
    public List<String> catManager() {
        return null;
    }

    /**
     * 查询 debug 日志
     *
     * @return null
     */
    @Override
    public List<String> debug() {
        return null;
    }

    /**
     * 当前目录所有日志
     *
     * @return null
     */
    @Override
    public List<String> findAll(String location) {
        File dir = new File(location);
        return listAll(dir);
    }

    /**
     * 拿到所有的日志文件
     *
     * @param dir 指定目录
     * @return null
     */
    private List<String> listAll(File dir) {
        List<String> fileData = new ArrayList<>();
        log.info("\n =======> dir is : " + dir.getName());
        File[] files = dir.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                listAll(file);
            } else {
                fileData.add(file.getName());
                log.info("\n ==========> file is : " + file.getName());
            }
        }
        return fileData;
    }
}
