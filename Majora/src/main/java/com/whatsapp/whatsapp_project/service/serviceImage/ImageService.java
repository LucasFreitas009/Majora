package com.whatsapp.whatsapp_project.service.serviceImage;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptImage.MajoraResponseImage;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappRequestBody;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappResponsePayload;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappResponsePayload.Image;
import com.whatsapp.whatsapp_project.models.whatsappModels.WhatsappResponsePayload.Text;
import com.whatsapp.whatsapp_project.service.serviceRest.RestService;

@Service
public class ImageService {
    @Autowired
    MajoraImage majoraImage;
    @Autowired
    RestService restService;

    private Map<String, List<Instant>> historicoPermissao = new HashMap<>();
    private Map<String, List<Instant>> listaDeEspera = new HashMap<>();

    public WhatsappBody payloadImage(WhatsappRequestBody whatsappRequestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + restService.whatsappToken);

        String wa_id = whatsappRequestBody.getEntry().get(0).getChanges().get(0).getValue().getContacts().get(0).getWa_id();
        String content = whatsappRequestBody.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getText().getBody();
        System.out.println(wa_id + " imagem solicitada!");

        List<Instant> listaHistorico = historicoPermissao.computeIfAbsent(wa_id, k -> new ArrayList<>());
        List<Instant> listaEspera = listaDeEspera.computeIfAbsent(wa_id, k -> new ArrayList<>());

        if (verify(wa_id, listaHistorico, listaEspera)) {
            MajoraResponseImage chatgptImage = majoraImage.responseImage(content);

            WhatsappResponsePayload payload = new WhatsappResponsePayload();
            payload.setImage(new Image());
            payload.setTo(wa_id);
            payload.setType("image");
            payload.getImage().setLink(chatgptImage.getData().get(0).getUrl());

            HttpEntity<WhatsappResponsePayload> httpEntity = new HttpEntity<>(payload, headers);
            ResponseEntity<WhatsappResponsePayload> response = restService.rest.postForEntity(restService.whatsappUrl, httpEntity, WhatsappResponsePayload.class);

            return new WhatsappBody(response.getBody(), response.getHeaders());
            
        } else {
            // Cálculo do tempo restante usando Instant
            Duration duration = Duration.between(Instant.now(), listaDeEspera.get(wa_id).get(0).plus(Duration.ofHours(5)));
            long seconds = Math.max(duration.getSeconds(), 0);

            long hours = seconds / 3600;
            long minutes = (seconds % 3600) / 60;
            long remainingSeconds = seconds % 60;

            String tempoTotal = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);

            WhatsappResponsePayload payload = new WhatsappResponsePayload();
            payload.setText(new Text());
            payload.setTo(wa_id);
            payload.setType("text");
            payload.getText().setBody("Aguarde (" + tempoTotal + ") para gerar imagens!");

            HttpEntity<WhatsappResponsePayload> httpEntity = new HttpEntity<>(payload, headers);
            ResponseEntity<WhatsappResponsePayload> response = restService.rest.postForEntity(restService.whatsappUrl, httpEntity, WhatsappResponsePayload.class);

            return new WhatsappBody(response.getBody(), response.getHeaders());
        }
    }

    public boolean verify(String wa_id, List<Instant> listaHistorico, List<Instant> listaEspera) {
        if (listaHistorico.size() < 2) {
            listaHistorico.add(Instant.now());

            return true;
        }

        Duration duration = Duration.between(listaHistorico.getFirst(), listaHistorico.getLast());

        if (duration.getSeconds() < 60 && listaEspera.isEmpty()) {
            listaEspera.add(Instant.now());
            listaDeEspera.put(wa_id, listaEspera);

            return false;
        } else if (Instant.now().isAfter(listaEspera.getFirst().plus(Duration.ofHours(5)))) {
            listaHistorico.clear();
            listaEspera.clear();
            
            historicoPermissao.put(wa_id, listaHistorico);
            listaDeEspera.put(wa_id, listaEspera);

            return true;
        }

        return false;
    }
}