package com.yue.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by yue on 2017/9/10
 */
public class JsonUtil {

    public static ObjectMapper mapper;

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String objectToJson(Object o)  {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
