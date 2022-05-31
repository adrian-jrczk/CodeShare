package codeshare.service;

import codeshare.code.Code;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String result;
    private String message;
    private String uuid;
    private Code code;

    public Response setResult(String result) {
        this.result = result;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Response setCode(Code code) {
        this.code = code;
        return this;
    }
}
