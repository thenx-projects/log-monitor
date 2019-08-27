package com.tgpms.web.monitor.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author May
 * <p>
 * 日志查询相关实体
 */
@Data
public class LogsDto {

    /**
     * 1. 日志名称
     */
    private String logsName;

    /**
     * 2. 日志时间
     */
    private String logsDate;

}
