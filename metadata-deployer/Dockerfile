FROM openjdk:17-oracle
COPY target/metadata-deployer-0.1-SNAPSHOT-fat.jar /usr/lib/contexts/metadata-deployer.jar
CMD java ${JAVA_OPTS} \
         -Dserver.port=${DEPLOYER_PORT} \
         -Dspring.datasource.driver-class-name=${DB_DRIVER} \
         -Dspring.datasource.url=${DB_URL} \
         -Dspring.datasource.username=${DB_USERNAME} \
         -Dspring.datasource.password=${DB_PASSWORD} \
         -jar /usr/lib/contexts/metadata-deployer.jar