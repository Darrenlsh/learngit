package com.darren.sellweixin.utils;

import java.util.Random;

/**
 * @author: luosihao
 * @date: 2019/1/23 15:40
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+ 6位随机数
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        // 生成6位随机数，巧妙的方法保证了生成的数一定是6位。
        Integer number = random.nextInt() + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
