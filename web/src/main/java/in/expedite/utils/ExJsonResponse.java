package in.expedite.utils;

/**
 * Created by vijaykarthik on 28/8/16.
 * general class to handle the Json response
 */
public class ExJsonResponse {

    private int code;
    private String message;

    public ExJsonResponse(){}
    
    /**
     * Constructor
     * @param code
     * @param message
     */
    public ExJsonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}

