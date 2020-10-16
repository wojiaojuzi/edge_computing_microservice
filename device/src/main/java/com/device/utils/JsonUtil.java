package com.device.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    // 对象转字符串
    public static <T> String objToString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 字符串转对象
    public static <T> T stringToObj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }

}
