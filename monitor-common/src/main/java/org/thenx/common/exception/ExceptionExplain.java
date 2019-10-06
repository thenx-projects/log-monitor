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


package org.thenx.common.exception;

public enum ExceptionExplain {

    // 成功相关信息描述
    SUCCESS_BY_INFOS(0, "操作成功"),
    SUCCESS_BY_INSERT(1, "增加成功"),
    SUCCESS_BY_UPDATE(2, "修改成功"),
    SUCCESS_BY_DELETE(3, "删除成功"),

    // 失败操作相关信息描述
    ERROR_BY_INSERT(-1, "增加失败"),
    ERROR_BY_UPDATE(-2, "修改失败"),
    ERROR_BY_DELETE(-3, "删除失败"),
    ERROR_BY_QUERY(-4, "查询异常"),


    // 空信息描述
    EMPTY_BY_DATA(-5, "数据为空"),

    // 解析失败描述
    ERROR_BY_PARSING(-6, "数据解析失败"),
    ERROR_BY_OVERDUE(-7, "会话过期"),

    // 其他异常信息描述
    ERROR_BY_INSERT_REPETITION(-8, "增加重复"),
    ERROR_BY_QUERY_ENPTY(-9, "查询结果为空"),

    //Vform错误
    ERROR_BY_VFORM_POST(-10, "同步请求失败"),

    // Token 为空
    ERROR_BY_TOKEN_EMPTY(-11, "Token 为空"),

    EMPTY_BY_LOCATION_OR_FILENAME(-12, "文件路径或文件名为空"),

    EMPTY_OR_INSUFFICIENT_PERMISSIONS(-13, "数据为空或权限不足");

    private Integer status;

    private String explain;

    ExceptionExplain(Integer status, String explain) {
        this.status = status;
        this.explain = explain;
    }


    ExceptionExplain(String explain) {
        this.explain = explain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public static ExceptionExplain exceptionExplain(int index) {
        for (ExceptionExplain exceptionExplain : values()) {
            if (exceptionExplain.getStatus().equals(index)) {
                return exceptionExplain;
            }
        }
        return null;
    }

}
