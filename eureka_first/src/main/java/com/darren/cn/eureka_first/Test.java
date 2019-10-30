package com.darren.cn.eureka_first;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luosihao
 * @date 2019/9/26 19:12
 */
public class Test {
    public static void main(String[] args) {

        String s = " ";
        Boolean is = StringUtils.isBlank(s);
        Boolean has = StringUtils.isEmpty(s);
        System.out.println(is);
        System.out.println(has);
    }
}
