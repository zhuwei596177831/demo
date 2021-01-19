package com.example.springweb.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author 朱伟伟
 * @date 2020-12-23 16:05:48
 * @description
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String body;
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
        if (isFormPost()) {
            this.body = URLDecoder.decode(getPostParameterValue(), getCharacterEncoding());
        } else {
            this.body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        }
        logger.info("MyHttpServletRequestWrapper body:\n{}", body);
        this.servletInputStream = new BodyCachingInputStream(body.getBytes());
    }


    private boolean isFormPost() {
        String contentType = getContentType();
        return (contentType != null && (contentType.contains(MediaType.APPLICATION_FORM_URLENCODED_VALUE) ||
                StringUtils.startsWithIgnoreCase(contentType, "multipart/")) &&
                HttpMethod.POST.matches(getMethod()));
    }

    @Override
    public String getCharacterEncoding() {
        String characterEncoding = super.getCharacterEncoding();
        return characterEncoding == null ? StandardCharsets.UTF_8.displayName() : characterEncoding;
    }

    private String getPostParameterValue() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        Map<String, String[]> form = super.getParameterMap();
        for (Iterator<String> nameIterator = form.keySet().iterator(); nameIterator.hasNext(); ) {
            String name = nameIterator.next();
            List<String> values = Arrays.asList(form.get(name));
            for (Iterator<String> valueIterator = values.iterator(); valueIterator.hasNext(); ) {
                String value = valueIterator.next();
                stringBuilder.append(name);
                if (value != null) {
                    stringBuilder.append('=');
                    stringBuilder.append(value);
                    if (valueIterator.hasNext()) {
                        stringBuilder.append('&');
                    }
                }
            }
            if (nameIterator.hasNext()) {
                stringBuilder.append('&');
            }
        }
        return stringBuilder.toString();
    }

    public String getBody() {
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
