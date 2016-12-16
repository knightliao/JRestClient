package com.github.knightliao.canalx.rest.client.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 */
@Data
@AllArgsConstructor
public class ResponseDto {

    // 返回的object
    private Object result;
    // 返回的status code
    private int statusCode;

    /**
     *
     */
    static public class ResponseDtoBuilder {

        public static ResponseDto getResponseDto(Object result, int statusCode) {

            return new ResponseDto(result, statusCode);
        }
    }

}
