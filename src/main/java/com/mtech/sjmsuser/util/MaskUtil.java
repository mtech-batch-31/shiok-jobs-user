package com.mtech.sjmsuser.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaskUtil {

    public static String applyMask(String input){
        // Mask the values of the sensitive fields
        List<String> sensitiveFields = Arrays.asList("name", "email");
        String maskedInput = maskSensitiveFields(input, sensitiveFields);
        return maskedInput;
    }


    public static String maskSensitiveFields(String jsonInput, List<String> sensitiveFields) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonInput);
            Map<String, String> maskedValues = new HashMap<>();

            // Mask sensitive fields
            for (String fieldName : sensitiveFields) {
                if (rootNode.has(fieldName)) {
                    String fieldValue = rootNode.get(fieldName).asText();
                    maskedValues.put(fieldName, maskString(fieldValue));
                }
            }

            // Replace sensitive field values with masked values
            for (Map.Entry<String, String> entry : maskedValues.entrySet()) {
                ((com.fasterxml.jackson.databind.node.ObjectNode) rootNode).put(entry.getKey(), entry.getValue());
            }

            return objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonInput; // Return original JSON input if masking fails
        }
    }

    public static String maskString(String str) {
        // Replace characters in the string with asterisks
        return str.replaceAll(".", "*");
    }
}
