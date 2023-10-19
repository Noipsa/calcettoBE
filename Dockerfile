FROM adoptopenjdk:11-jre-hotspot

COPY target/FantaCalcetto.jar /app/app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar"]