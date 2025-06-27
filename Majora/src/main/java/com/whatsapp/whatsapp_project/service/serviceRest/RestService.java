package com.whatsapp.whatsapp_project.service.serviceRest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    @Value("${app.openai.key}")
    public String openaiToken;
    public String openaiTextUrl = "https://api.openai.com/v1/chat/completions";
    public String openaiImageUrl = "https://api.openai.com/v1/images/generations";
    @Value("${app.whatsapp.key}")
    public String whatsappToken;
    public String whatsappUrl = "URL do Whatsapp aqui!";
    public final RestTemplate rest = new RestTemplate();
}
