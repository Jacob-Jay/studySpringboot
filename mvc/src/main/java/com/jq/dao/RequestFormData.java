package com.jq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-25 20:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFormData {
    private String name;
    private int age;
    private Date date;
}
