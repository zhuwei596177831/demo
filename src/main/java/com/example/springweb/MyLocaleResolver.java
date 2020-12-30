package com.example.springweb;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author 朱伟伟
 * @date 2020-12-30 15:20:06
 * @description
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String locale = request.getParameter("locale");
        if (StringUtils.isEmpty(locale)) {
            return Locale.getDefault();
        }//zh_CN
        String[] split = locale.split(",");
        return new Locale(split[0], split[1]);
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
