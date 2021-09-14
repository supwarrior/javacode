package com.github.interfaces;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 康盼Java开发工程师
 */
public interface InputStreamSource {
    /**
     * Return an {@link InputStream} for the content of an underlying resource.
     */
    InputStream getInputStream() throws IOException;
}
