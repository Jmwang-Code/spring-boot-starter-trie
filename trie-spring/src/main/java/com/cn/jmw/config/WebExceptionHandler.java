package com.cn.jmw.config;

import com.cn.jmw.dto.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @Date 2023/4/20 14:31
 * @Author jmw
 * @Description 全局异常处理
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class WebExceptionHandler {


    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseData<Object> exceptionHandler(Exception e) {
        Object data = null;
        //获取异常信息

        String msg = null;
        msg = e.getMessage();
        if (msg == null) {
            Throwable cause = e.getCause();
            if (cause != null) {
                msg = cause.getMessage();
            }
        }
        log.error(msg, e);
        ResponseData.ResponseDataBuilder<Object> builder = ResponseData.builder();
        return builder.success(false)
                .message(msg)
                .data(data)
                .build();
    }

}
