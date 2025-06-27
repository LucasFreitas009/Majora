package com.whatsapp.whatsapp_project.models.whatsappModels.whatsappStatuses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhatsappStatusesBody {
    
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
                private List<Statuses> statuses;
                private Metadata metadata;

                
                //Classe Statuses
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Statuses {
                    private String id;
                    private String status;
                    private String timestamp;
                    private String recipient_id;
                    private Conversation conversation;
                    private Pricing pricing;

                    //Classe Conversation
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class Conversation {
                        private String id;
                        private Origin origin;

                        //Classe Origin
                        @Getter
                        @Setter
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class Origin {
                            private String type;
                        }
                    }

                    //Classe Pricing
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class Pricing {
                        private boolean billable;
                        private String pricing_model;
                        private String category;
                    }
                }

                //Classe Metadata
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Metadata {
                    private String display_phone_number;
                    private String phone_number_id;
                }
            }     
        }
    }
}
