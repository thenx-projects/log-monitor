
package org.thenx.windows;

import lombok.extern.slf4j.Slf4j;
import org.thenx.system.OS;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author May
 *
 * Windows 下的控制
 */
@Slf4j
public class WindowsControl {

    private static final int DEFAULT_BUFFER_SIZE = 4096;

    public List<String> core(String location, String fileName) {
        List<String> addLine = new ArrayList<>();
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec("cmd /c cd " + location + " & type " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CharBuffer charBuffer = CharBuffer.allocate(DEFAULT_BUFFER_SIZE);
        CharArrayWriter bufferWriter = new CharArrayWriter();
        BufferedReader bufferedReader = null;

        try {
            int length;
            bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(p).getInputStream(), OS.getEncodeByPlatform()));
            while ((length = bufferedReader.read(charBuffer)) > 0) {
                charBuffer.flip();
                bufferWriter.write(charBuffer.array(), 0, length);
                charBuffer.clear();
            }
            addLine.add(new String(bufferWriter.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭bufferedReader
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ignored) {
            }

        }
        return addLine;
    }
}
