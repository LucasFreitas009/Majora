package com.whatsapp.whatsapp_project.service.serviceText;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptBody.MajoraResponseBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappRequestBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappResponsePayload;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappResponsePayload.Text;
import com.whatsapp.whatsapp_project.service.serviceRest.RestService;

@Service
public class TextService {
    @Autowired
    MajoraResponseText majoraResponseText;
    @Autowired
    RestService restService;

    public WhatsappBody payloadText(WhatsappRequestBody whatsappRequestBody) {
        String wa_id = whatsappRequestBody.getEntry().get(0).getChanges().get(0).getValue().getContacts().get(0).getWa_id();
        String content = whatsappRequestBody.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getText().getBody();

        MajoraResponseBody majoraResponse = majoraResponseText.responseText(content, wa_id);
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + restService.whatsappToken);
    
        WhatsappResponsePayload payload = new WhatsappResponsePayload();
        payload.setText(new Text());
        payload.setTo(wa_id);
        payload.setType("text");
        payload.getText().setBody(majoraResponse.getChoices().get(0).getMessage().getContent());
    
        HttpEntity<WhatsappResponsePayload> httpEntity = new HttpEntity<>(payload, headers);
    
        ResponseEntity<WhatsappResponsePayload> response = restService.rest.postForEntity(restService.whatsappUrl, httpEntity, WhatsappResponsePayload.class);
        WhatsappBody body = new WhatsappBody(response.getBody(), response.getHeaders());
    
        return body;
    }
}
