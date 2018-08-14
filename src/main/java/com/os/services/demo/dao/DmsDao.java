
package com.os.services.demo.dao;

import com.os.services.demo.model.QDoc;
import feign.Client;
import feign.form.FormEncoder;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Map;

@Repository
@Import(FeignClientsConfiguration.class)
public class DmsDao {
    @Autowired
    DmsClient dmsClient;

    private DmsUploadClient dmsUploadClient;

    @Autowired
    public DmsDao(Client client) {

        this.dmsUploadClient = HystrixFeign.builder()
                .client(client)
                .decoder(new JacksonDecoder())
                .encoder(new FormEncoder())
                .target(DmsUploadClient.class, "http://dms");

    }

    public Map<String, Object> getItem(String id) {

        return this.dmsClient.getItem(id);
    }

    public Map<String, Object> createItem(QDoc qdoc, String parentId) {
        return dmsClient.createItem("qdoc", "Test qdoc" + ZonedDateTime.now().toString(), parentId, qdoc);
    }

    public Map<String, Object> createItemContent(String id, MultipartFile file) throws IOException {
        return dmsUploadClient.createItemContent(id, file.getBytes());

    }

    public Map<String, Object> updateItem(QDoc qdoc, String id) {
        return dmsClient.updateItem(id, "Test qdoc" + ZonedDateTime.now().toString(), qdoc);
    }

    public Map<String, Object> deleteItem(String id) {
        return dmsClient.deleteItem(id);
    }


}
