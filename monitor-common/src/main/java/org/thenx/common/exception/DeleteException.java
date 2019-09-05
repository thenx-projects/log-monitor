package org.thenx.common.exception;

/**
 * @author May
 * <p>
 * 全局统一异常管理 质量APP 删除异常
 */
public class DeleteException extends ProjectException {

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
