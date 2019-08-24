package com.jq.custom.view.resolver;

import com.jq.custom.view.ExcelView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-24 10:56
 */
public class ExcelViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return new ExcelView();
    }
}
