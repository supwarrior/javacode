package com.github.resource;

import com.sun.istack.internal.Nullable;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 康盼Java开发工程师
 */
public class UrlResource extends AbstractFileResolvingResource {


    @Nullable
    private final URI uri;

    private final URL url;

    private final URL cleanedUrl;


    public UrlResource(URI uri) throws MalformedURLException {
        this.uri = uri;
        this.url = uri.toURL();
        this.cleanedUrl = getCleanedUrl(this.url, uri.toString());
    }

    private URL getCleanedUrl(URL originalUrl, String originalPath) {
        String cleanedPath = StringUtils.cleanPath(originalPath);
        if (!cleanedPath.equals(originalPath)) {
            try {
                return new URL(cleanedPath);
            }
            catch (MalformedURLException ex) {
            }
        }
        return originalUrl;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        ResourceUtils.useCachesIfNecessary(con);
        try {
            return con.getInputStream();
        }
        catch (IOException ex) {
            // Close the HTTP connection (if applicable).
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }
}
