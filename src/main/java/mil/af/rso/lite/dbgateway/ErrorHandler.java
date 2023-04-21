package mil.af.rso.lite.dbgateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {
    // custom Error format is generated using this class
    public static Map<String, Object> CreateResponse(String message, String Errorcode) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("developerMessage", message);
            map.put("errorCode", Errorcode);

            return map;
    }
    public static ResponseEntity<Object> generateResponse(Object responseObj, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("error", responseObj);
            return new ResponseEntity<Object>(map,status);
    }
    
    
}
