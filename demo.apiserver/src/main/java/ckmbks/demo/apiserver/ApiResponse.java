package ckmbks.demo.apiserver;

import lombok.Data;

@Data
public class ApiResponse {

    public ApiResponse() {
    }

    public ApiResponse(Exception e) {
        setSuccess(false);
        setErrorMessage(e.getMessage());
    }

    private String errorMessage;

    private Object data;

    private boolean isSuccess = true;

}
