package com.whatsapp.whatsapp_project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappRequestBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.whatsappStatuses.WhatsappRead;
import com.whatsapp.whatsapp_project.service.serviceImage.ImageService;
import com.whatsapp.whatsapp_project.service.serviceRest.RestService;
import com.whatsapp.whatsapp_project.service.serviceText.TextService;


@Service
public class WhatsappService {
    @Autowired
    private TextService textService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private RestService restService;

    
    public WhatsappBody payload(WhatsappRequestBody whatsappRequestBody) {
        String content = whatsappRequestBody.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getText().getBody().toLowerCase();
        String message_id = whatsappRequestBody.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getId();

        if(!content.startsWith("!image")) {
            reader(message_id);
            return textService.payloadText(whatsappRequestBody);
        } else {
            reader(message_id);
            return imageService.payloadImage(whatsappRequestBody);
        }
    }

    public void reader(String message_id) {
        if(message_id != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + restService.whatsappToken);
    
            WhatsappRead payload = new WhatsappRead();
            payload.setMessage_id(message_id);
            
            HttpEntity<WhatsappRead> httpEntity = new HttpEntity<>(payload, headers);
    
            restService.rest.postForEntity(restService.whatsappUrl, httpEntity, WhatsappRead.class);
        }

        return;
    }
}
