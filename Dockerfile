FROM openjdk:8

ARG ADDITIONAL_OPTS

ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/hospital

COPY /target/hospital-0.0.1-SNAPSHOT.jar*.jar hospital-0.0.1-SNAPSHOT.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080
EXPOSE 5005

CMD java ${ADDITIONAL_OPTS} -jar hospital-0.0.1-SNAPSHOT.jar
