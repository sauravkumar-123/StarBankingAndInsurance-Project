package com.smartpay.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private boolean processStatus;
    private String message;
    private Object datasource;

    @Override
    public String toString() {
        return "Response{" + "processStatus=" + processStatus + ", message=" + message + ", datasource=" + datasource + '}';
    }

}
