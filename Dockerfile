FROM openjdk:17-slim
EXPOSE 8080

ADD build/libs/fleet-manager-*.jar /fleet-manager.jar
RUN addgroup --system clevershuttle && adduser --system --group clevershuttle
USER clevershuttle:clevershuttle

CMD ["java", "-jar", "/fleet-manager.jar"]
