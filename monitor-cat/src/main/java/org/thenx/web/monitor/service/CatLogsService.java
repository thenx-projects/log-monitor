






package org.thenx.web.monitor.service;

import org.thenx.web.monitor.dto.LogsDto;
import org.thenx.common.vo.PageView;

import java.util.List;

public interface CatLogsService {

    List<String> catLogs(String location, String fileName);

    List<LogsDto> findAll(String location);

    PageView catLogsPage(PageView pageView);

    PageView findAllPage(PageView pageView);

    List<String> findAllName(String location);

    List<String> findByLogsName(String location, String logsName);

    /**
     * 根据日志名称精确查找
     *
     * @param location 日志路径
     * @param logsName 日志名称
     * @return null
     */
    List<String> findByEqualsName(String location, String logsName);
}
