package Info_Parser;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseAPIDetail {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static ApiRequestConfig loadApiRequestConfig(String jsonString) throws Exception {
    return objectMapper.readValue(jsonString, ApiRequestConfig.class);
    }

}