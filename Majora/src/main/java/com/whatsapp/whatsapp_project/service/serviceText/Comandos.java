package com.whatsapp.whatsapp_project.service.serviceText;

public class Comandos {
    public static String tabela = "*Comandos*\n" +
    "*!ajuda* *->* mostra a tabela de comandos.\n" +
    "*!sobre* *->* mostra informações sobre o bot e seu desenvolvedor.\n" +
    "*!image* *->* cria imagens baseada em descrições do usuário. Limite de duas imagens a cada 5 horas!\n" +
    "*!refletir* *->* retorna uma resposta mais elaborada e inteligente utilizando o modelo gpt-4o" +
    "*!new* *->* mostra atualizações feitas recentemente.";

    public static String sobre = "O Majora é um bot open-source criado para interação via WhatsApp. Utiliza o modelo GPT-4o para gerar respostas em texto e o modelo DALL·E 3 para criação de imagens. O objetivo é proporcionar uma comunicação prática, inteligente e segura para os usuários." + 
    "Foi desenvolvido utilizando java e spring boot. Ainda está em fase e testes e, caso tenha interesse em saber como tratamos seus dados, acesse o nosso site -> https://www.majora.dev.br/\n" +
    "Caso esteja interessado em como o bot foi feito, acesse nosso github -> https://github.com/LucasFreitas009/Majora\n" +
    "*É provável que tenha frequentes quedas de desempenho (ou queda de servidor) do Majora em detrimento de instabilidades de conexão.*\n\n" +
    "Versão do sistema: v1.0.0.1";

    public static String novo = "*Adição de sistema de cotação do dólar em tempo real* - 10/05/2025\n" +
    "*Aumento do histórico de conversa* - 10/05/2025\n" +
    "*Bugs de spam corrigidos!* - 10/05/2025\n" +
    "*Informações sobre o clima de hoje* - 25/05/2025" +
    "*Comando !refletir adicionado para respostas mais elaboradas* - 11/06/2025";

    public static String personalidade = "Você é o Majora e foi criada no Brasil. Você é um assistente virtual simpático, inteligente e sempre disposto a ajudar. " +
    "Responda às perguntas com um tom amigável, natural e positivo. Use emojis de forma moderada e evite usar emojis quando o assunto for mais técnico ou sério. " +
    "Seja objetivo, mas converse de forma leve, como se estivesse batendo um papo com a pessoa. " +
    "Sempre que possível, elogie boas perguntas ou incetive a pessoa ('ótima pergunta!', 'mandou bem em perguntar isso!'). " +
    "Em momentos de dúvida ou dificuldade. mostre empatia ('não se preocupe, vamos resolver juntos', 'é normal ficar confuso ás vezes'). " +
    "Evite ser muito formal ou robótico. Prefira expressões mais humanas como 'vamos lá', 'show', 'tô por aqui', 'bora nessa'. " +
    "Se for explicar algo técnico, simplifique ao máximo e ofereça ajuda extra se perceber que a pessoa pode ter dificuldades. Seja mais sério conforme for ficando mais técnico. " +
    "Seu objetivo é fazer com que cada interação pareça leve, acolhedora e inesquecível. " +
    "Caso a pessoa queira que você seja mais séria ou engraçada, seja.";
}
