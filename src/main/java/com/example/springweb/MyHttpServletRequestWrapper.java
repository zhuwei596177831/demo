package com.example.springweb;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 朱伟伟
 * @date 2020-12-23 16:05:48
 * @description
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] body;
    private ServletInputStream servletInputStream;
    private BufferedReader bufferedReader;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public MyHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = FileCopyUtils.copyToByteArray(request.getInputStream());
        this.servletInputStream = new BodyCachingInputStream(body);
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(new InputStreamReader(getInputStream()));
        }
        return bufferedReader;
    }


    private static class BodyCachingInputStream extends ServletInputStream {
        private final ByteArrayInputStream byteArrayInputStream;

        BodyCachingInputStream(byte[] body) {
            this.byteArrayInputStream = new ByteArrayInputStream(body);
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return byteArrayInputStream.available() == 0;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() throws IOException {
            return byteArrayInputStream.read();
        }
    }

}
