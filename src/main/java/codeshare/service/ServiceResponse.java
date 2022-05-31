package codeshare.service;

import codeshare.code.Code;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse {

    private String result;
    private String message;
    private String uuid;
    private Code code;

    public ServiceResponse setResult(String result) {
        this.result = result;
        return this;
    }

    public ServiceResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public ServiceResponse setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public ServiceResponse setCode(Code code) {
        this.code = code;
        return this;
    }
}
