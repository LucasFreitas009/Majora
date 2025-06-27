package com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptImage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajoraRequestImage {
    private String model = "dall-e-3";
    private String prompt;
    private int n = 1;
    private String size = "1024x1024";
}
