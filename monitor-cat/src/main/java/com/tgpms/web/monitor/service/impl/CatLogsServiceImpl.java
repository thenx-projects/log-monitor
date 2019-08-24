package com.tgpms.web.monitor.service.impl;

import com.tgpms.web.monitor.core.CoreControl;
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
     * 查询 指定 日志
     *
     * @param location 文件路径
     * @param fileName 文件名称
     * @return null
     */
    @Override
    public List<String> catLogs(String location, String fileName) {
        CoreControl control = new CoreControl();
        return control.core(location, fileName);
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
        File[] files = dir.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                listAll(file);
            } else {
                fileData.add(file.getName());
            }
        }
        return fileData;
    }
}
