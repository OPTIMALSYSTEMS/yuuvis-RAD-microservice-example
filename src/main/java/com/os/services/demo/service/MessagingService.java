package com.os.services.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 11.04.2017.
 */
@Service
public class MessagingService {

    @Autowired
    private DmsService dmsService;

    private final static Logger LOGGER = LoggerFactory.getLogger(MessagingService.class);

    @JmsListener(destination = "documentChanged")
    public void readMessageDocumentChanged(String messageText) {
        LOGGER.info("documentChanged event");
        extractMessage(messageText);
    }

    @JmsListener(destination = "indexDataChanged")
    public void readMessageIndexDataChanged(String messageText) {
        LOGGER.info( "indexDataChanged event");
        extractMessage(messageText);
    }

    @JmsListener(destination = "documentDeleted")
    public void readMessageDocumentDeleted(String messageText) {
        LOGGER.info( "documentDeleted event");
        extractMessage(messageText);
    }

    private void extractMessage(String messageText) {
        try {
            Object object = new ObjectMapper().readValue(messageText, Object.class);
            if (object != null && object instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> messagesBody = (Map<String, Object>) object;
                if (messagesBody.get("messages") != null && messagesBody.get("messages") instanceof List) {
                    List<?> messages = (List<?>) messagesBody.get("messages");
                    for (Object message : messages) {
                        Map<?, ?> messageBody = (Map<?, ?>) message;
                        this.processMessage(messageBody);
                    }
                } else {
                    this.processMessage(messagesBody);
                }
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

    private void processMessage(Map<?, ?> messageBody) {

        LOGGER.info("Message : " + messageBody.toString());

        String itemid = (String) messageBody.get("itemid");

        Map<String, Object> itemData = dmsService.getItem(itemid);

        LOGGER.info("Item : " + itemData.toString());

    }

}
