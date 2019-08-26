package com.jq.custom.filter;

import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.annotation.WebFilter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-25 9:56
 */
@WebFilter(urlPatterns = "/*")
public class MethodTransFilter extends HiddenHttpMethodFilter {
}
