FROM java:8
EXPOSE 8082
ARG JAR_FILE=build/libs/*.jar
RUN echo "${JAR_FILE}"
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]