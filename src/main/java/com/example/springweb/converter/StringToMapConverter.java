package com.example.springweb.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-01-11 15:28:40
 * @description
 */
public class StringToMapConverter implements Converter<String, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(String source) {
        if (source != null) {
            Map<String, Object> map = new HashMap<>();
            for (String entry : source.split("&")) {
                String[] split = entry.split("=");
                map.put(split[0], split[1]);
            }
            return map;
        }
        return Collections.emptyMap();
    }
}
