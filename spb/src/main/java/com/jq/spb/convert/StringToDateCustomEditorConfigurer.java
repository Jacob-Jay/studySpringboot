package com.jq.spb.convert;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-13 17:25
 */
public class StringToDateCustomEditorConfigurer extends PropertyEditorSupport{
       @Override
    public void setAsText(String text) throws IllegalArgumentException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setValue(parse);
    }
}
