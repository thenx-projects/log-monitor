package com.tgpms.web.monitor.service.impl;

import com.tgpms.common.PageView;
import com.tgpms.web.monitor.core.CoreControl;
import com.tgpms.web.monitor.dto.LogsDto;
import com.tgpms.web.monitor.service.CatLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public List<LogsDto> findAll(String location) {
        File dir = new File(location);
        return listAll(dir);
    }

    /**
     * 查询指定日志 分页版
     *
     * @param pageView 分页
     * @return null
     */
    @Override
    public PageView catLogsPage(PageView pageView) {
        CoreControl control = new CoreControl();
        Map map = pageView.getQueryMap();
        String location = (String) map.get("location");
        String fileName = (String) map.get("fileName");
        pageView.setRecords(control.core(location, fileName));
        return pageView;
    }

    /**
     * 查询所有日志 分页版
     *
     * @param pageView 分页
     * @return null
     */
    @Override
    public PageView findAllPage(PageView pageView) {
        Map queryMap = pageView.getQueryMap();
        String location = (String) queryMap.get("location");
        File dir = new File(location);
        List<LogsDto> logsDtos = listAll(dir);
        pageView.setRecords(logsDtos);
        return pageView;
    }

    /**
     * 拿到所有的日志文件
     *
     * @param dir 指定目录
     * @return null
     */
    private List<LogsDto> listAll(File dir) {
        List<LogsDto> fileData = new ArrayList<>();
        File[] files = dir.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                listAll(file);
            } else {
                LogsDto logsDto = new LogsDto();
                logsDto.setLogsName(file.getName());
                logsDto.setLogsDate(file.getName().substring(file.getName().indexOf("."), file.getName().lastIndexOf(".")).replace(".", ""));
                fileData.add(logsDto);
            }
        }
        return fileData;
    }

    /**
     * 仅仅查询所有的日志名称
     *
     * @param location 路径名称
     * @return null
     */
    @Override
    public List<String> findAllName(String location) {
        File dir = new File(location);
        List<String> list = new ArrayList<>();
        List<LogsDto> logsDtos = listAll(dir);
        for (LogsDto logsDto : logsDtos) {
            String logsName = logsDto.getLogsName();
            String substring = logsName.substring(0, logsName.indexOf("."));
            if (! list.contains(substring)) {
                list.add(substring);
            }
        }
        return list;
    }

    /**
     * 根据日志名称查询相关日志
     *
     * @param location 路径
     * @param logsName 日志名称
     * @return null
     */
    @Override
    public List<String> findByLogsName(String location, String logsName) {
        List<String> list = new ArrayList<>();
        File file = new File(location);
        List<LogsDto> logsDtos = listAll(file);
        for (LogsDto logsDto : logsDtos) {
            String name = logsDto.getLogsName();
            if (name.contains(logsName)) {
                list.add(name);
            }
        }
        return list;
    }

    /**
     * 根据日志名称精确查找
     *
     * @param location 日志路径
     * @param logsName 日志名称
     * @return null
     */
    @Override
    public List<String> findByEqualsName(String location, String logsName) {
        List<String> list = new ArrayList<>();
        File file = new File(location);
        List<LogsDto> logsDtos = listAll(file);
        for (LogsDto logsDto : logsDtos) {
            String name = logsDto.getLogsName();
            if (name.equals(logsName)) {
                list.add(name);
            }
        }
        return list;
    }
}
