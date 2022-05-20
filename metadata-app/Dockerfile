FROM openjdk:17-oracle
COPY target/metadata-app-0.1-SNAPSHOT-fat.jar /usr/lib/contexts/metadata-app.jar
CMD java ${JAVA_OPTS} \
         -Dserver.port=${APP_PORT} \
         -Ddatasource.driverClassName=${DB_DRIVER} \
         -Ddatasource.url=${DB_URL} \
         -Ddatasource.username=${DB_USERNAME} \
         -Ddatasource.password=${DB_PASSWORD} \
         -jar /usr/lib/contexts/metadata-app.jar