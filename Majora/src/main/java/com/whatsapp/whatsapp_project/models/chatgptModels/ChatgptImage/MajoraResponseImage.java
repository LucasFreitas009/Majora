package com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptImage;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajoraResponseImage {
    private long created;
    private List<Data> data;

    @Getter
    @Setter
    public static class Data {
        private String url;
    }
}
