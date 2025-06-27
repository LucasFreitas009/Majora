package com.whatsapp.whatsapp_project.models.whatsappModels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatsappRequestBody {
    private String object;
    private List<Entry> entry;

    //Classe Entry
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Entry {
        private String id;
        private List<Changes> changes;

        //Classe Changes
        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Changes {
            private Value value;
            private String field;
            
            //Classe Value
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Value {
                private String messaging_product;
                private Metadata metadata;
                private List<Contacts> contacts;
                private List<Messages> messages;

                //Classe Metadata
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Metadata {
                    private String display_phone_number;
                    private String phone_number_id;
                }

                //Classe Contacts
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Contacts {
                    private Profile profile;
                    private String wa_id;
    
    
                    //Classe Profile
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class Profile {
                        private String name;
                    }
                }

                //Classe Messages
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Messages {
                private String from;
                private String id;
                private String timestamp;
                private Text text;
                private String type;

                    //Classe Text
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class Text {
                    private String body;
                    }
                }
            }
        }
    }
}