package com.tgpms.exception;

/**
 * @author May
 * <p>
 * 全局统一管理异常
 */
public class ProjectException extends RuntimeException{

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
