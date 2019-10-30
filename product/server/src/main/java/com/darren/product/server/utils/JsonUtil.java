package com.darren.product.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author luosihao
 * @date 2019/10/14 10:23
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}
