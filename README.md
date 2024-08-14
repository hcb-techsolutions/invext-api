### Pré-Requisitos

Docker e Docker Compose Instalado
Postman ou Insomnia instalado para testar a api


### Build e Deploy o projeto em container Docker

Na pasta raiz do projeto, realizar os seguintes passos:


Fazer o Build do projeto com o comando abaixo  

```
docker compose build

```



Fazer o Deploy do projeto com o comando abaixo 

```
docker compose up -d

```


### Testar a API

Executar um POST em: localhost:8080/api/v1/solicitacao para testar a api


Exemblos de Body para Testes:


* Cartão

```
{
    "tipoSolicitacao": "CARTAO",
    "mensagem": "teste cartoes"
}

```

* Emprestimo

```
{
    "tipoSolicitacao": "EMPRESTIMO",
    "mensagem": "teste emprestimos"
}

```

* Outro Assunto

```
{
    "tipoSolicitacao": "OUTRO",
    "mensagem": "teste outros assuntos"
}

```

### Configuração dos Times

O projeto esta config com Time de:

- <b> Cartoes: </b> 2 atendentes que finaliza suas solicitações cada 2min
- <b> Emprestimos: </b> 1 atendente  que finaliza suas solicitações cada 1min
- <b> Outros: </b> 2 atendentes  que finaliza suas solicitações cada 1min e 30s

