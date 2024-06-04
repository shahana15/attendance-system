package com.threeaxislabs.ams.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Utils {
    public static String printJson(Object payload) {
        String jsonResponse = null;
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            jsonResponse = objectWriter.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            log.error("Exception occurred while parsing to json ", e);
        }
        return jsonResponse;
    }
}
