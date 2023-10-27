package com.mtech.sjmsuser.util;

import java.util.Base64;

import org.springframework.boot.json.JsonParserFactory;

//public class JwtTokenUtil {
//
//    public static String getUserNameFromJWT(String token) {
//        var tokenParts = token.split("\\.");
//        String tokenWithoutSignature = tokenParts[1];
//        Base64.Decoder decoder = Base64.getDecoder();
//        var tokenWithoutSignatureDecode = new String(decoder.decode(tokenWithoutSignature));
//
//        var jsonParser = JsonParserFactory.getJsonParser();
//        var map = jsonParser.parseMap(tokenWithoutSignatureDecode);
//        return (String) map.get("username");
//    }
//}
