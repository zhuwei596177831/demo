package com.example.springweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

/**
 * @author 朱伟伟
 * @date 2021-01-04 17:56:59
 * @description
 */
//@Component
@WebFilter(filterName = "customRequestLoggingFilter", urlPatterns = {"/*"})
public class CustomRequestLoggingFilter extends AbstractRequestLoggingFilter {
    private HttpServletResponse httpServletResponse;

    //这些配置都可以在init-param中进行设置,但是基于注解的，这里就不要这么麻烦了，统一在初始化的时候设置值吧
    //private boolean includeQueryString = false;
    //private boolean includeClientInfo = false;
    //private boolean includeHeaders = false;
    //private boolean includePayload = false;
    private static final String PROCESS_START_TIME_SUFFIX = ".PROCESS_START_TIME";

    @Override
    protected void initFilterBean() throws ServletException {
        super.setIncludeQueryString(true);
        super.setIncludeClientInfo(true);
        //因为headers里面的信息太多了 所以这里不输出了，否则过于干扰，只手动把content-type等关键信息输出即可
        super.setIncludeHeaders(false);
        super.setIncludePayload(true);
        super.setMaxPayloadLength(1000); //最大支持到1000个字符
        //头信息
        super.setBeforeMessagePrefix("请求开始 [");
        super.setBeforeMessageSuffix("]");
        super.setAfterMessagePrefix("请求结束 [");
        super.setAfterMessageSuffix("]");

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        this.httpServletResponse = response;
        super.doFilterInternal(request, response, filterChain);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        String method = request.getMethod();
        return HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method) || HttpMethod.DELETE.matches(method);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(calcRequestTime(request)
                .concat(getConfigTypeLog(request))
                .concat(getThreadId())
                .concat(message));
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(calcRequestTime(request)
                .concat(getConfigTypeLog(request))
                .concat(getThreadId())
                .concat(message));
    }

    @Override
    protected String getMessagePayload(HttpServletRequest request) {
        String payload = super.getMessagePayload(request);
        if (payload != null) {
            try {
                payload = URLDecoder.decode(payload, request.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return payload;
    }

    //拼装contentType
    private String getConfigTypeLog(HttpServletRequest request) {
        String contentType = request.getContentType();
        String method = request.getMethod();
        return "[contentType=" + contentType + "] " + method.toUpperCase() + " ";
    }

    //计算请求耗时
    private String calcRequestTime(HttpServletRequest request) {
        long mills = 0;
        String requestTimeUniqueName = getRequestTimeUniqueName();

        Object processStartTime = request.getAttribute(requestTimeUniqueName);
        if (processStartTime == null) { //首次 放置值
            request.setAttribute(requestTimeUniqueName, Instant.now());
        } else { //请求结束的处理  开始计算吧
            Instant start = (Instant) processStartTime;
            Instant now = Instant.now();
            mills = Duration.between(start, now).toMillis();

            request.removeAttribute(requestTimeUniqueName);
        }
        return mills == 0 ? "" : ("[耗时:" + mills + "ms] ");

    }

    private String getRequestTimeUniqueName() {
        return this.getClass().getName().concat(PROCESS_START_TIME_SUFFIX);
    }

    private String getThreadId() {
        return "[ThreadId:" + Thread.currentThread().getId() + "] ";
    }
}
