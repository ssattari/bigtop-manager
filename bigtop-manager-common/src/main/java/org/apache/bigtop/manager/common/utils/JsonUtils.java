package org.apache.bigtop.manager.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonUtils {
    public static final ObjectMapper OBJECTMAPPER;

    static {
        OBJECTMAPPER = new ObjectMapper();
        OBJECTMAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Generate json file
     *
     * @param fileName json file
     * @param object   json content
     */
    public static void writeJson(String fileName, Object object) {
        try {
            JsonUtils.OBJECTMAPPER.writeValue(new File(fileName), object);
        } catch (IOException e) {
            log.error("[writeJson] error, ", e);
        }
    }
}