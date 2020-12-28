package com.example.springweb;

import com.alibaba.fastjson.JSON;
import com.example.generic.Result;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-12-28 11:07:24
 * @description
 */
public class HandlerInterceptor1 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request instanceof MultipartHttpServletRequest) {
            System.out.println("MultipartHttpServletRequest......");
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            MultiValueMap<String, MultipartFile> multiFileMap = multipartRequest.getMultiFileMap();
//            for (Map.Entry<String, List<MultipartFile>> entry : multiFileMap.entrySet()) {
//                System.out.println(entry.getKey());
//                for (MultipartFile multipartFile : entry.getValue()) {
//                    System.out.println(multipartFile.getContentType());
//                    System.out.println(multipartFile.getName());
//                    System.out.println(multipartFile.getOriginalFilename());
//                    System.out.println(multipartFile.getSize());
//                }
//            }
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            System.out.println(handlerMethod.getMethod().getName());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ServletOutputStream outputStream = response.getOutputStream();
            Map<String, Object> map = new HashMap<>();
            map.put("status", "0001");
            map.put("msg", "失败");
            map.put("timestamp", Instant.now().toEpochMilli());
            map.put("data", null);
            Result<Map<String, Object>> result = Result.ok(map);
            outputStream.write(JSON.toJSONString(result).getBytes());
            outputStream.flush();
            outputStream.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
