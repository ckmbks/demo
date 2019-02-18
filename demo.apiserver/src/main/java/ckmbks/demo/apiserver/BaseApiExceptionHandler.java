package ckmbks.demo.apiserver;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * RestController执行过程中发生异常会被此处捕获处理
 */
@RestControllerAdvice
public class BaseApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ApiResponse globalExceptionHandler(Exception e) {
        return new ApiResponse(e);
    }

}