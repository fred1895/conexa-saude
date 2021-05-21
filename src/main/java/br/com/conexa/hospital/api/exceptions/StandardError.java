package br.com.conexa.hospital.api.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class StandardError implements Serializable {

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("status-code")
    private Integer statusCode;

    @JsonProperty("httpstatus")
    private String httpStatus;

    @JsonProperty("message")
    private String msg;

}
