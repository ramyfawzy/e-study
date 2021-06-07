FROM quay.io/keycloak/keycloak:13.0.1
MAINTAINER Ramy Ibrahim <eng.ramyibrahim@gmail.com> (@Ramy__Ibrahim)
#RUN rpm -v update
#RUN rpm -v add bash bash-completion sudo curl vim
RUN java -version
RUN echo $JAVA_HOME
EXPOSE 8180
WORKDIR /app
COPY ./keycloak_export.json .
COPY ./docker-entrypoint.sh .
ENTRYPOINT [ "/app/docker-entrypoint.sh" ]