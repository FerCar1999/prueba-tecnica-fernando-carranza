FROM jboss/wildfly:latest

# Copiar el archivo WAR en el directorio de despliegue
COPY apiproductos/target/apiproductos-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/
