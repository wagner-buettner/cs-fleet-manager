FROM openjdk:17-slim
EXPOSE 8080

ADD build/libs/fleet-manager-*.jar /fleet-manager.jar

CMD ["java", "-jar", "/fleet-manager.jar"]
