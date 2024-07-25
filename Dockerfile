# imagen modelo
FROM eclipse-temurin:17-jdk

# informacion del puerto del contenedor
EXPOSE 8080

# DEFINIR EL DIRECTORIO RAIZ
WORKDIR /root

#COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#Descargar dependedencias
RUN ./mvnw dependency:go-offline

#copiar el codigo fuente dentro del contededor
COPY ./src /root/src

#construir la aplicacion
RUN ./mvnw clean install -DskipTests

# levantar el servicion cuando inicie el contenedor
ENTRYPOINT ["java","-jar","/root/target/hotel-0.0.1-SNAPSHOT.jar"]