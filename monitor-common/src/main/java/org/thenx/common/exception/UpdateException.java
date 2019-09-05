package org.thenx.common.exception;

/**
 * @author May
 * <p>
 * 全局统一异常管理 质量APP 修改异常
 */
public class UpdateException extends ProjectException {

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
