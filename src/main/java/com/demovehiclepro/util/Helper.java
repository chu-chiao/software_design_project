package com.demovehiclepro.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.StringWriter;

final public class Helper {

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
}
