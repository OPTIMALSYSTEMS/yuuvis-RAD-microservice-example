
package com.os.services.demo.web.error;

import lombok.Data;

@Data
public class ErrorDetail
{
    private int status;
    private String title;
    private String detail;
    private long timeStamp;
    private Develop develop;

    @Data
    public static class Develop
    {
        private Exception exception;
    }
}
