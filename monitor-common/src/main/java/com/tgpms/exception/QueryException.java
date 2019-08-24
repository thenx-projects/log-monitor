package com.tgpms.exception;

/**
 * @author May
 * <p>
 * 全局统一异常管理 质量APP 查询相关异常
 */
public class QueryException extends ProjectException {

    public QueryException(String message) {
        super(message);
    }

    public QueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
