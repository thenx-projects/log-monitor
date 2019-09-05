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
