<h1>Sistema de sessão de votos</h1>

<h3>Como funciona</h3>
<p>O sistema faz uma varredura em todos registros no banco que ainda estão em aberto e vencidos a cada 5 segundos, definido pelo Scheduler </p>
<p>Quando encontra um registro aberto e expirado, calcula os resultados atualiza os registros e notifica ao RabbitMQ</p>

<h3>Excutando o sistema</h3>
<p>Tenha o docker instalado na máquina ou siga instalação conforme <a target="_blank" href="https://www.docker.com/products/docker-desktop/">Docker</a></p>
<p>Na raiz do projeto execute o comando: <b>docker-compose up -d --build</b></p>
<h3>Documentação da API</h3>
<p>Swagger: http://localhost:8080/swagger-ui/index.html#/ </p>

<h3>Testes unitários</h3>
<p>Foram adicionados somente alguns testes para verificação de conhecimento</p>

<h3>Explicação dos campos</h3>
<p>Para criar uma nova sessão eleitoral acesse o método POST/api/v1/session-voting</p>
<ul>
    <li>description = descrição para a sessão eleitoral</li>
    <li>duration = duração em minutos para a sessão eleitoral</li>
</ul>

<p>Para ver o andamento de uma sessão eleitoral acesse o método GET/api/v1/session-voting/{id}</p>
<ul>
    <li>id = retornado na criação da sessão eleitoral</li>
</ul>

<p>Para votar utilize POST/api/v1/vote/{id}</p>
<ul>
    <li>PARAM/ id = retornado na criação da sessão eleitoral</li>
    <li>BODY/ taxId = cpf do eleitor</li>
    <li>BODY/ cadidate = voto do eleitor (use true para sim e false para não)</li>
</ul>

<h3>Acompanhamento de logs</h3>
<p>Os logs deverão ser acompanhados pelo console do docker desktop</p>

<h3>Banco de dados</h3>
<p>Para facilitar a conexão e o uso externo sem deploy foi utilizado um mongodb no atlas e pode ser acessado pelo mongoDbCompass</p>
<p>String de conexão: mongodb+srv://southsystem:teste@southsystem-application.bvk8x.mongodb.net/?retryWrites=true&w=majority </p>

<h3>Filas e mensageria</h3>
<p>Durante a subida da aplicação via docker-compose é disponibilizado um RabbitMQ</p>
<p>Acesso: localhost:15672, usuário: guest, senha: guest</p>
<p>Pode ser monitorado junto a aplicação via terminal docker</p>