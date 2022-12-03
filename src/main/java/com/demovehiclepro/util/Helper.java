package com.demovehiclepro.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.StringWriter;
import java.security.Key;

final public class Helper {

    public static SecretKey secretKey = Keys.hmacShaKeyFor(generateSecurityKey().getBytes());

    public static Boolean notBlank(String value){
        return value != null && !value.isBlank();
    }

    public static ObjectNode convObjToONode(Object o) {
        StringWriter stringify = new StringWriter();
        ObjectNode objToONode = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(stringify, o);
            objToONode = (ObjectNode) mapper.readTree(stringify.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objToONode;
    }

    public static String generateSecurityKey() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        return secretString;
    }
}
