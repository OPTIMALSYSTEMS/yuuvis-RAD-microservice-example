
package com.os.services.demo.dao;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

public interface DmsUploadClient {

    @RequestLine("POST /rest-ws/service/dms/{id}/contents")
    @Headers("Content-Type: multipart/form-data")
    Map<String, Object> createItemContent(@Param("id") String id,
                                          @Param("file") byte[] file);

}
