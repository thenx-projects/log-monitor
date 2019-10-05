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





package org.thenx.web.monitor.service.impl;

import org.thenx.web.monitor.core.CoreControl;
import org.thenx.web.monitor.dto.LogsDto;
import org.thenx.web.monitor.service.CatLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thenx.common.vo.PageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class CatLogsServiceImpl implements CatLogsService {

    @Override
    public List<String> catLogs(String location, String fileName) {
        CoreControl control = new CoreControl();
        return control.core(location, fileName);
    }

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
                logsDto.setLogsDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));
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
