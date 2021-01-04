package com.example.springweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class RequestReadUtils {
    private static final int BUFFER_SIZE = 1024 * 8;
    private static final Logger logger = LoggerFactory.getLogger(RequestReadUtils.class);

    public static String read(HttpServletRequest request) throws IOException {
        StringWriter writer = new StringWriter();
        String contentType = request.getContentType();
        logger.info("Content-Type：" + contentType);
        if (StringUtils.startsWithIgnoreCase(contentType, "multipart/")) {
            return "";
        }
        BufferedReader bufferedReader = request.getReader();
        write(bufferedReader, writer);
        return writer.getBuffer().toString();
    }

    public static long write(Reader reader, Writer writer) throws IOException {
        return write(reader, writer, BUFFER_SIZE);
    }


    public static long write(Reader reader, Writer writer, int bufferSize) throws IOException {
        int read;
        long total = 0;
        char[] buf = new char[bufferSize];
        while ((read = reader.read(buf)) != -1) {
            writer.write(buf, 0, read);
            total += read;
        }
        return total;
    }

}
