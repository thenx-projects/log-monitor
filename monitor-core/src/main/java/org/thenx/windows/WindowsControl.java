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
 * <p>
 * Windows 下的控制
 */
@Slf4j
public class WindowsControl {

    private static final int DEFAULT_BUFFER_SIZE = 4096;

    /**
     * 传入路径和文件名
     *
     * @param location 路径
     * @param fileName 文件名
     * @return x
     */
    public List<String> core(String location, String fileName) {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec("cmd /c cd " + location + " & type " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coreExe(p);
    }

    /**
     * 执行command
     *
     * @param p command
     * @return x
     */
    public List<String> coreExe(Process p) {
        List<String> addLine = new ArrayList<>();

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
