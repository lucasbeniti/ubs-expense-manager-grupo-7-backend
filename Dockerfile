# Estágio de Build (Compilação)
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copia os arquivos do Gradle primeiro para aproveitar o cache de camadas
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Dá permissão e baixa as dependências
RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

# Copia o código fonte e gera o JAR
COPY src src
RUN ./gradlew bootJar -x test --no-daemon

# Estágio de Runtime (Execução)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia apenas o JAR gerado no estágio anterior
COPY --from=build /app/build/libs/gestao_ubs-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]