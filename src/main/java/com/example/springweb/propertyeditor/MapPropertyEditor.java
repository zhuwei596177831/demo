package com.example.springweb.propertyeditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-01-11 15:40:32
 * @description
 */
public class MapPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text);
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof String) {
            String param = (String) value;
            Map<String, Object> map = new HashMap<>();
            for (String entry : param.split("&")) {
                String[] split = entry.split("=");
                map.put(split[0], split[1]);
            }
            super.setValue(map);
        } else {
            super.setValue(value);
        }
    }
}
