
package com.os.services.demo.web.rest;

import com.os.services.demo.model.QDoc;
import com.os.services.demo.service.DmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/dms")
public class DmsController {
    
    @Autowired
    private DmsService dmsService;

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> getItem(@PathVariable String id) {
        return this.dmsService.getItem(id);
    }

    @RequestMapping(value = "/item/{parentid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Map<String, Object> createItem(@RequestBody QDoc qDoc, @PathVariable String parentid) {
        return dmsService.createItem(qDoc, parentid);
    }

    @RequestMapping(value = "/item/content/{id}", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> createItemContent(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {

        String ct = file.getContentType();

        return dmsService.createItemContent(id, file);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT, produces = "application/json")
    public Map<String, Object> updeateItem(@PathVariable String id, @RequestBody QDoc qDoc) {
        return this.dmsService.updateItem(qDoc, id);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Map<String, Object> updateItem(@PathVariable String id) {
        return this.dmsService.deleteItem(id);
    }

}
