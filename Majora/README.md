# Majora
Documentação da API do Majora.

Devido a utilização de outras duas APIs de terceiros, o Majora não funciona de forma independente, necessitando do processamento da *OPENAI API* e da utilização obrigatória do *Whatsapp* para seu funcionamento. Caso queira fazer um projeto baseado no Majora, será necessário a criação de contas na [*OPENAI PLATFORM*](https://platform.openai.com) e [*Meta for developers*](https://developers.facebook.com), além da administração de dinheiro para utilizar as devidas APIs. 

## Pasta controller
A pasta controller é a primeira encontrada em todo o sistema de diretórios criados para o funcionamento da API. 

Na pasta controller está armazenado o arquivo [WhatsappController.java](src/main/java/com/whatsapp/whatsapp_project/controller/WhatsappController.java). Neste arquivo existem dois endpoints que compartilham dois nomes em comum e que são importantes para o funcionamento do fluxo da API. Esses endpoints denominados [*webhook*](https://pt.m.wikipedia.org/wiki/Webhook) seram utilizados para receber e enviar dados para a [Graph API](https://developers.facebook.com/docs/graph-api/) da Meta, mas o Majora utiliza especificamente a Graph API para comunicação direta com o Whatsapp.

### Funcionamento do Get controller
Como você talvez já tenha visto, existem dois endpoints com o mesmo nome, porém eles possuem propostas diferentes.

A documentação oficial da Graph API requer que o utilizador confirme que a url com o endpoint da sua API de fato é sua. Dessa forma a primeiro função é utilizada para receber os parâmetros passados pela Graph API, verificá-los e retornar um valor padrão para a Graph API.

Como o processo da primeira função é apenas de veifição, essa função será utilizada uma vez em todo o funcionamento da API do Majora. Mas, de toda forma, ela é necessária e obrigatória para utilizar a Graph API e conseguir manter a conexão com o Whatsapp.

### Funcionamento do Post controller
A segunda função do controller é utilizada para receber e processar conversas com o usuário via Whatsapp. Essa função recebe como parâmetro um json da Graph API contendo todas as informações necessárias para o Majora conseguir processar os dados e retornar uma resposta ao usuário.

Essa função recebe dois jsons distintos, são eles os *statuses* e o *Body padrão*:

- Os statuses são dados jsons que dizem se o usuário visualizou, apagou, editou e etc as mensagens, isso faz com que você receba muitos erros de processamento caso você não possua meios de receber esses dados. E devido a isso, o Majora apenas processa esses dados e retorna um código 200 para a Graph API, sinalizando que o Majora já sabe o que ocorreu na conversa.

- O Body padrão é basicamente as mensagens que normalmente o usuário envia. Como você pode ter notado, existe uma verificação dentro da função para saber se existe um valor "messages", isso é para separar o json statuses do body padrão de mensagens do whatsapp. Dessa forma, é evitado encontros indesejados entre os dados.

## Pasta models
A pasta models contém os arquivos com seus respectivos modelos POJOs, utilizados para receber e armazenar os dados jsons recebidos pela API da Meta, OPENAI e aweasomeapi. Esse diretório contém todos os POJOs necessários para seu funcionamento atual, sinta-se livre para adicionar e modificar os POJOs.

## Pasta service
A pasta service contém toda a lógica por trás do funcionamento do Majora, aqui está localizado todo o sistema de processamento, armazenamento e resposta de mensagens. São múltiplos diretórios que mexem com APIs externas de clima, cotação e interligam todo o sistema do Majora.

### Pasta serviceImage