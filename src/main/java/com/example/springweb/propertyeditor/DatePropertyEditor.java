package com.example.springweb.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 朱伟伟
 * @date 2021-01-08 16:11:13
 * @description
 */
public class DatePropertyEditor extends PropertyEditorSupport {
    private static final String format = "yyyy-MM-dd";
//    private static final String format = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text);
    }

    @Override
    public void setValue(Object value) {
        try {
            if (value instanceof String) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                super.setValue(dateFormat.parse((String) value));
            } else {
                super.setValue(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
