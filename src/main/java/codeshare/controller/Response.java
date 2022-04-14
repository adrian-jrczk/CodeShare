package codeshare.controller;

import codeshare.code.Code;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
class Response {

    private String result;
    private String message;
    private String uuid;
    private Code code;

    Response setResult(String result) {
        this.result = result;
        return this;
    }

    Response setMessage(String message) {
        this.message = message;
        return this;
    }

    Response setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    Response setCode(Code code) {
        this.code = code;
        return this;
    }
}
