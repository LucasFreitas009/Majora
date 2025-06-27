package com.whatsapp.whatsapp_project.models.whatsappModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhatsappResponsePayload {
    private String messaging_product = "whatsapp";
    private String recipient_type = "individual";
    private String to;
    private String type;
    private Text text;
    private Image Image;

    //Classe Text
    @Getter
    @Setter
    public static class Text {
        private boolean preview_url = false;
        private String body;
    }

    @Getter
    @Setter
    public static class Image {
        private String link;
        private String caption;
    }
}