criação do projeto -> https://start.spring.io/

rodar a API -> .\gradlew.bat bootRun

metodos prontos (CRUD) -> JpaRepository

![alt text](image.png)

DIFICULDADES: Estou acostumado a criar op mer na mão, com SQL, já fiz o uso de Quarkus, para uma API, na faculdade. Utilizar uma freameWork é muito fera, facilita muito.
hoje descobri que a anotação @ManyToOne faz o gerenciamento das chaves estrangeiras, criando as na tabela.

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

esses caras me auxiliaram para ver a select no meu terminal