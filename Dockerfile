# Base Image
FROM java:8

# Maintainer
MAINTAINER Andre Luiz <andremirandarosa@gmail.com>

# Add the fatjar in the image
COPY target/HubFintech-0.0.1-SNAPSHOT.jar /

# Default command
CMD java -jar /HubFintech-0.0.1-SNAPSHOT.jar
