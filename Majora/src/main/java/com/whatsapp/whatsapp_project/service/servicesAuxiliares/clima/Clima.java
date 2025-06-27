package com.whatsapp.whatsapp_project.service.servicesAuxiliares.clima;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.whatsapp.whatsapp_project.service.serviceRest.RestService;

@Service
public class Clima {
    private final String url = "https://api.weatherapi.com/v1/current.json?key=6f7949fb43484659bd7174450252505&q=RIo%20de%20Janeiro&aqi=yes";
    RestService restTemplate = new RestService();


    @SuppressWarnings("unchecked")
    public Map<String, Map<String, String>> climaAtual() {
        Map<String, Map<String, String>> jsonClima = restTemplate.rest.getForObject(url, Map.class);
        
        return jsonClima;
    }
}
