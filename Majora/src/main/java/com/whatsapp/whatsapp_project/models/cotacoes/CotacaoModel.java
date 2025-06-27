package com.whatsapp.whatsapp_project.models.cotacoes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CotacaoModel {
    private UsdBrl usdbrl;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UsdBrl {
        private String code;
        private String codein;
        private String name;
        private String high;
        private String low;
        private String varBid;
        private String pctChange;
        private String bid;
        private String ask;
        private String timestamp;
        private String create_date;
    }
}