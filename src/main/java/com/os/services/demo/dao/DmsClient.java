
package com.os.services.demo.dao;

import com.os.services.demo.model.QDoc;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient("dms")
public interface DmsClient {


    @RequestMapping(method = GET, value = "/rest-ws/service/dms/{id}", produces = "application/json", consumes = "application/json")
    Map<String, Object> getItem(@PathVariable("id") String id);

    @RequestMapping(method = POST, value = "/rest-ws/service/dms/create/{childtype}", produces = "application/json", consumes = "application/json")
    Map<String, Object> createItem(@PathVariable("childtype") String childtype,
                                   @RequestParam("usercomment") String comment,
                                   @RequestParam("parentid") String parentId,
                                   @RequestBody QDoc qdoc);

    @RequestMapping(method = PUT, value = "/rest-ws/service/dms/{id}", produces = "application/json", consumes = "application/json")
    Map<String, Object> updateItem(@PathVariable("id") String id,
                                   @RequestParam("usercomment") String comment,
                                   @RequestBody QDoc qdoc);


    @RequestMapping(method = DELETE, value = "/rest-ws/service/dms/{id}", produces = "application/json", consumes = "application/json")
    Map<String, Object> deleteItem(@PathVariable("id") String id);

}
