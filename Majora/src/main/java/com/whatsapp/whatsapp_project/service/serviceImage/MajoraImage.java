package com.whatsapp.whatsapp_project.service.serviceImage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptImage.MajoraRequestImage;
import com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptImage.MajoraResponseImage;
import com.whatsapp.whatsapp_project.service.serviceRest.RestService;


@Service
public class MajoraImage {
   @Autowired
    private RestService restService;
    
     public MajoraResponseImage responseImage(String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + restService.openaiToken);

        String prompt = content.toLowerCase();
            
        MajoraRequestImage chatgptRequestImage = new MajoraRequestImage();
        chatgptRequestImage.setPrompt(prompt);
    
        HttpEntity<MajoraRequestImage> httpEntity = new HttpEntity<>(chatgptRequestImage, headers);
    
        ResponseEntity<MajoraResponseImage> majoraResponseImage = restService.rest.postForEntity(restService.openaiImageUrl, httpEntity, MajoraResponseImage.class);

        return majoraResponseImage.getBody();
    }
}
