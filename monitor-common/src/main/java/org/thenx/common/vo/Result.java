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
package org.thenx.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author May
 * <p>
 * layUI返回结果集
 */
@Data
public class Result implements Serializable {

    /**
     * 标识符
     */
    private static final long serialVersionUID = -7319737625485900657L;

    /**
     * 返回状态
     */
    private Integer code = 0;

    /**
     * 返回统计
     */
    private Integer count = 1000;

    /**
     * 默认返回消息提醒
     */
    private String msg = "操作成功";

    /**
     * 返回数据
     */
    private Object data;
}
