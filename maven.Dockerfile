FROM maven:3.8.5-openjdk-17

WORKDIR /app

# Copiar el código del proyecto
COPY ./apiproductos /app

# Construir el proyecto usando Maven
RUN mvn clean package
