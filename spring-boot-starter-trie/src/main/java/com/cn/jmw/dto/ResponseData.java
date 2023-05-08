
package com.cn.jmw.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {

    @JsonInclude()
    private boolean success;

    @JsonInclude()
    private int errCode;

    @JsonInclude()
    private String message;

    private List<String> warnings;

    @JsonInclude()
    private T data;

    public static <E> ResponseData<E> success(E data) {
        return (ResponseData<E>) ResponseData.builder()
                .data(data)
                .success(true)
                .build();
    }

    public static <E> ResponseData<E> failure(String message) {
        return (ResponseData<E>) ResponseData.builder()
                .success(false)
                .message(message)
                .build();
    }

}