package com.github.interfaces;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 康盼Java开发工程师
 */
public interface InputStreamSource {

    /**
     * getInputStream
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
