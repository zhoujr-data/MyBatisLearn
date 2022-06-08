package com.fude.io;

import java.io.InputStream;

/**
 * @author zhoujr
 * created at 2022/5/23 17:37
 * //TODO
 **/
public class Resources {

    // 根据配置文件路径，加载字节输入流。存储在内存中
    public static InputStream getResourceAsStream(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }

}
