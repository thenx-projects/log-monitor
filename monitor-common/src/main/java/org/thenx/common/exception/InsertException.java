package org.thenx.common.exception;

/**
 * @author May
 * <p>
 * 全局统一异常管理 质量APP 插入异常
 */
public class InsertException extends ProjectException {

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
