package com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptBody;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajoraResponseBody {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choices> choices;
    private Usage usage;

    @Getter
    @Setter
    public static class Choices {
        private int index;
        private MajoraRequestBody.Messages message;
        private String finish_reason;
    }

    @Getter
    @Setter
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }
}
