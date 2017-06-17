# architecture

## Pré-Requisitos

- PostgreSQL 9.6
  - Uma base de dados "limpa" chamada `architecture`

- Sevidor de Aplicação Wildfly 10.1
  - Servidor de e-mail configurado¹

- Maven 3.5

## Destaques

- Organização do Código
  - Classes abstratas do tipo `DAO` e `View` com métodos básicos para gerenciar 
  a persistência de dados e o controle de telas, respectivamente.
  
  - Maior parte da regra de negócio concentrada no EJB `VotacaoBusiness`
  
- Criação e fechamento das votações são agendados e executados via Quartz
  - ver pacote `quartz`, especialmente a classe `ScheduleManager`

## Como funciona

### Para o sistema
1. Executar `mvn clean package` no diretório do projeto
2. Descompactar o arquivo `${Diretório}/target/architecture.war` para uma pasta `architecture.war` dentro da diretório `standalone/deployments/` do servidor
3. Executar o script `standalone.sh` na pasta `/bin` do servidor
4. Uma nova votação é criada automaticamente todos os dias às 06 a.m. e encerrada às 11:30 a.m.
5. Um e-mail é enviado para todos os usuários cadastrados no sistema .

### Para o Usuário:
1. Entrar na página inicial, em geral <http://localhost:8080/architecture/index.xhtml> 
e logar com um dos usuários disponíveis.
- Inicialmente há quatro usuários² disponíveis: `admin`, `gabriel`, `jonas`, `wendell`
- As senhas são iguais aos usernames
2. Já no `home` da aplicação o usuário verá se há uma votação em aberto, 
caso haja ele pode votar e/ou alterar seu voto até que ela seja encerrada.
- Usuários com o perfil de adminstrador podem cadastrar novos restaurantes e usuários.
3. Ao encerrar a votação, o usuário ser notificado via e-mail cadastrado no sistema. 
Além disso, ao entrar na home, o sistema também exibe qual o restaurante vencedor do dia.

## Problemas com testes
Para essa aplicação, baseada em EJB e CDI faz muito pouco sentido usar testes unitários, a 
melhor alternativa para garantir a cobertura do sistema seriam testes integração/sistema.

Entretanto, eu não consegui configurar o framework de testes (arquillian) a tempo, faz algum tempo desde a
útima vez que trabalhei com java de fato.

## O que poderia ser feito
- Outras formas de notificação ao usuário, por exemplo via sms ou `bots` do telegram, skype e afins.
- Outras formas de votar, por exemplo um aplicativo próprio.
- Permitir a adição e visualização de mais informaçes sobre os restaurantes no próprio sistema,
além de estatísticas sobre os mesmos.
- Adotar algum critério de justiça para quando houver empates na votação.
- Tratamento de erros mais amigável quando o usuário tentar acessar páginas internas 
sem estar logado ou sem possuir permisso.
- Permitir votações por grupos de pessoas, para casos em que nem todos podem ir almoçar juntos ao memos tempo.
- Permitir o agendamento das votações de forma dinâmica, cadastradas por algum usuário, ao invés de *hard-coded*.
Também permitir "pular" o agendamento dessas votações em finais de semana e/ou feriados.
- Permitir que os usuários possam decidir se querem ou não ser notificados e/ou notificar somente aqueles que votarem.
- Melhorias na interface em geral.

------

¹ Um servidor pré-configurado encontra-se disponível em 

² Esses usuários e alguns outros dados são gerados sempre que a aplicação é iniciada. Atualmente ela se encontra configurada para recriar o banco sempre que o servidor subir.
