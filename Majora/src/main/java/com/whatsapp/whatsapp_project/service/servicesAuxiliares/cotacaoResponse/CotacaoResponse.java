package com.whatsapp.whatsapp_project.service.servicesAuxiliares.cotacaoResponse;

import org.springframework.stereotype.Service;

import com.whatsapp.whatsapp_project.service.serviceRest.RestService;

import java.util.Map;

@Service
public class CotacaoResponse {

    private final String url = "https://economia.awesomeapi.com.br/json/last/USD-BRL";
    private final RestService restTemplate = new RestService();

    @SuppressWarnings("unchecked")
    public String cotacaoDolar() {
        try {
            Map<String, Map<String, String>> response = restTemplate.rest.getForObject(url, Map.class);

            @SuppressWarnings("null")
            String bid = response.get("USDBRL").get("bid");

            return bid;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao obter cotação";
        }
    }
}
