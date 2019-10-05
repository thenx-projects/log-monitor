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








package org.thenx.windows.monitor.service;

import org.thenx.windows.monitor.dto.LogsDto;
import org.thenx.common.vo.PageView;

import java.util.List;

public interface CatLogsService {

    List<String> catLogs(String location, String fileName);

    List<LogsDto> findAll(String location);

    PageView catLogsPage(PageView pageView);

    PageView findAllPage(PageView pageView);

    List<String> findAllName(String location);

    List<String> findByLogsName(String location, String logsName);

    List<String> findByEqualsName(String location, String logsName);
}
