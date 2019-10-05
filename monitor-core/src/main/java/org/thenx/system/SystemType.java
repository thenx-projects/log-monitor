package org.thenx.system;

import lombok.extern.slf4j.Slf4j;
import org.thenx.common.exception.ExceptionExplain;
import org.thenx.common.exception.QueryException;

/**
 * @author May
 * <p>
 * 判断当前的操作系统
 */
@Slf4j
public class SystemType {

    /**
     * 判断当前系统是 Windows 还是 Linux
     */
    public static String SYSTEM_OS = "Windows";

    static {
        log.info("\n ------> right here the system os is: " + System.getProperties().getProperty("os.name"));
        log.info("\n ------> right here the file type is : " + System.getProperties().getProperty("file.separator"));
        String system = System.getProperties().getProperty("file.separator");
        if ("\\".equals(system)) {
            SYSTEM_OS = "Windows";
        } else if ("/".equals(system)) {
            SYSTEM_OS = "Linux";
        } else {
            throw new QueryException(ExceptionExplain.ERROR_BY_QUERY.getExplain());
        }
    }
}
