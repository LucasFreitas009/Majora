package com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptBody;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajoraRequestBody {
    private String model = "gpt-4o-mini";
    private List<Messages> messages = new ArrayList<>(); 
    private double temperature = 0.7;
    
    @Getter
    @Setter
    public static class Messages {
        private String role;
        private String content;
    }
}
