
# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Information around who maintains the image
LABEL "org.opencontainers.image.authors"="eazybytes.com"

# Add the participants's jar to the image
COPY target/accounts-1.0.jar accounts-1.0.jar

# Exceute the application
ENTRYPOINT ["java", "-jar", "accounts-1.0.jar"]