/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thenx.web.monitor.service;

import org.thenx.web.monitor.dto.LogsDto;
import org.thenx.common.vo.PageView;

import java.util.List;

/**
 * @author May
 * <p>
 * 查询日志相关接口
 */
public interface CatLogsService {

    /**
     * 查询 对应 日志
     *
     * @param location 文件路径
     * @param fileName 文件名称
     * @return null
     */
    List<String> catLogs(String location, String fileName);

    /**
     * 当前目录所有日志
     *
     * @return null
     */
    List<LogsDto> findAll(String location);

    /**
     * 查询对应日志 分页版
     *
     * @param pageView 分页
     * @return null
     */
    PageView catLogsPage(PageView pageView);

    /**
     * 查询所有日志 分页版
     *
     * @param pageView 分页
     * @return null
     */
    PageView findAllPage(PageView pageView);

    /**
     * 当前目录所有日志 仅仅查找名称
     *
     * @return null
     */
    List<String> findAllName(String location);

    /**
     * 根据日志名称查询相关日志
     *
     * @param location 日志路径
     * @param logsName 日志名称
     * @return null
     */
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