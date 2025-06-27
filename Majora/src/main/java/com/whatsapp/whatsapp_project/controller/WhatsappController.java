package com.whatsapp.whatsapp_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappRequestBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.whatsappStatuses.WhatsappStatusesBody;
import com.whatsapp.whatsapp_project.service.WhatsappService;



@RestController
@RequestMapping("/api")
public class WhatsappController {
    @Autowired
    WhatsappService service;
    
    @GetMapping("/webhook")
    public ResponseEntity<String> verifyToken(@RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String token, @RequestParam("hub.challenge") String challenge) {
        if("subscribe".equals(mode) && "1234".equals(token)) {
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("token inválido");
    }

    @PostMapping("/webhook")
    public ResponseEntity<WhatsappBody> enviarPayload(@RequestBody JsonNode whatsappRequest) {
        JsonNode value = whatsappRequest.path("entry").get(0).path("changes").get(0).path("value");
        ObjectMapper mapper = new ObjectMapper();

        if(value.has("messages")) {
            WhatsappRequestBody whatsappRequestBody = null;

            try{
                whatsappRequestBody = mapper.treeToValue(whatsappRequest, WhatsappRequestBody.class);
                
            } catch(JsonProcessingException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().body(service.payload(whatsappRequestBody));

        }else {
            try {
                @SuppressWarnings("unused")
                WhatsappStatusesBody whatsappStatusesBody = mapper.treeToValue(whatsappRequest, WhatsappStatusesBody.class);
                
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().build();
        }
    }
}
