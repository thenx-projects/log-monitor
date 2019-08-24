package com.tgpms.web.monitor.dto;

import lombok.Data;

/**
 * @author May
 * <p>
 * 日志查询相关实体
 */
@Data
public class LogsDto {

    /**
     * 1. catalina 日志
     */
    private String catalina;

    /**
     * 2. host-manager 日志
     */
    private String hostManager;

    /**
     * 3. localhost 日志
     */
    private String localhost;

    /**
     * 4. localhost_access_log 日志
     */
    private String localhostAccessLog;

    /**
     * 5. manager 日志
     */
    private String manager;

    /**
     * 6. debug 日志
     */
    private String debug;
}
