package com.whatsapp.whatsapp_project.models.whatsappModels.whatsappStatuses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhatsappRead {
    private String messaging_product = "whatsapp";
    private String status = "read";
    private String message_id;
}
