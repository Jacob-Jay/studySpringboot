package com.jq.spb.own;

import com.jq.spb.Example;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-09 9:36
 */
public class TestFinal {

    public static void main(String[] args) {
        Example example = new Example();
        final  Example example1 = example;
        example = new Example();
    }
}
