







package org.thenx.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = -7319737625485900657L;

    private Integer code = 0;

    private Integer count = 1000;

    private String msg = "操作成功";

    private Object data;
}
