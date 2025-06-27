package com.whatsapp.whatsapp_project.service.serviceText;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptBody.MajoraRequestBody;
import com.whatsapp.whatsapp_project.models.chatgptModels.ChatgptBody.MajoraResponseBody;
import com.whatsapp.whatsapp_project.service.serviceRest.RestService;
import com.whatsapp.whatsapp_project.service.servicesAuxiliares.clima.Clima;
import com.whatsapp.whatsapp_project.service.servicesAuxiliares.cotacaoResponse.CotacaoResponse;


@Service
public class MajoraResponseText {
    @Autowired
    RestService restService;
    @Autowired
    CotacaoResponse cotacao;
    @Autowired
    Clima clima;
    private final Map<String, List<MajoraRequestBody.Messages>> historico = new HashMap<>();

     //Resposta do chatgpt por texto
    @SuppressWarnings("null")
    public MajoraResponseBody responseText(String content, String wa_id) {
        System.out.println(wa_id + " resposta solicitada!");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + restService.openaiToken);

        MajoraRequestBody request = new MajoraRequestBody();

        if(content.startsWith("!refletir")) {
            request.setModel("gpt-4o");
        }

        MajoraRequestBody.Messages user = new MajoraRequestBody.Messages();

        List<MajoraRequestBody.Messages> chatMessages = historico.computeIfAbsent(wa_id, k -> new ArrayList<>());

        
        if(chatMessages.isEmpty() || !chatMessages.get(0).getRole().equalsIgnoreCase("system")) {
            MajoraRequestBody.Messages system = new MajoraRequestBody.Messages();
            system.setRole("system");
            system.setContent(Comandos.personalidade + ". Você pode consultar as mensagens anteriores para entender o contexto. Caso o usuário tente criar imagens ou saber mais informações, diga a ele o comando *!ajuda* para informações.\n" + 
            "Caso o usuário tenha usado o comando *!ajuda*, mostre para ele está tabela: " + Comandos.tabela + " " + "Caso ele tenha utilizado o comando *!sobre*, mostre para ele essa mensagem: " + Comandos.sobre + " não formate o link, deixe sem nenhum caractere especial próximo a ele.\n" +
            "Caso ele queira a cotação atual do dolar, mostre esse valor formatado com vírgula e apenas 2 casas decimais: " + cotacao.cotacaoDolar() + ". Essa cotação está sendo atualizada em tempo real! Outras cotações não estão disponíveis.\n" + 
            "Caso o usuário tenha utilizado o comando *!new*, mostre a ele essa mensagem: " + Comandos.novo + ". Caso o usuário pergunte sobre o clima atual ou sobre a previsão do clima nos próximos 6 dias contando com hoje, envie as informações do clima do Rio de Janeiro para ele, você deve enviar valores de temperatura em celsius: " + clima.climaAtual());
            chatMessages.add(0, system);
        }
        
        MajoraRequestBody.Messages systemWithDate = chatMessages.getFirst();
        String date = systemWithDate.getContent() + ". esse é o horário de brasília e data de hoje: " + LocalDateTime.now();
        systemWithDate.setContent(date);
        chatMessages.add(0, systemWithDate);

        user.setRole("user");
        user.setContent(content);

        
        chatMessages.add(user);
        request.setMessages(new ArrayList<>(chatMessages));
        
        HttpEntity<MajoraRequestBody> httpEntity = new HttpEntity<>(request, headers);
        
        ResponseEntity<MajoraResponseBody> majoraResponse = restService.rest.postForEntity(restService.openaiTextUrl, httpEntity, MajoraResponseBody.class);
        chatMessages.add(majoraResponse.getBody().getChoices().get(0).getMessage());
        adicionarConversa(wa_id, chatMessages);


        //  System.out.println("wa_id: " + wa_id);
        //  System.out.println("Contém a chave wa_id ? " + historico.containsKey(wa_id));
        //  System.out.println("tamanho do historico: " + historico.getOrDefault(wa_id, new ArrayList<>()).size());
        //  try{
 
        //      System.out.println(new ObjectMapper().writeValueAsString(request));
        //  }catch(Exception e) {
        //      e.printStackTrace();
        //  }
         

        return majoraResponse.getBody();
    }

    public void adicionarConversa(String wa_id, List<MajoraRequestBody.Messages> messages) {
        while (messages.size() > 101) {
            
            messages.remove(1);
            messages.remove(1); 
        }
    
        historico.put(wa_id, messages);
    }
}
