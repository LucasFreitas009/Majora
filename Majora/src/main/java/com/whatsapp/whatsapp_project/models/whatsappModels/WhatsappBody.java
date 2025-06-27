package com.whatsapp.whatsapp_project.models.whatsappModels;


import org.springframework.http.HttpHeaders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WhatsappBody {
    private WhatsappResponsePayload whatsappResponsePayload;
    private HttpHeaders headers;

    public WhatsappBody(WhatsappResponsePayload payload, HttpHeaders headers) {
        this.whatsappResponsePayload = payload;
        this.headers = headers;
    }
}
