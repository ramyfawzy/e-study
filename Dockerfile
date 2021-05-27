FROM adoptopenjdk/openjdk16:x86_64-alpine-jre16u-nightly
MAINTAINER Ramy Ibrahim <eng.ramyibrahim@gmail.com> (@Ramy__Ibrahim)
RUN apk -v update
RUN apk -v add bash bash-completion sudo curl vim
RUN java -version
RUN echo $JAVA_HOME
EXPOSE 8088
WORKDIR /app
COPY target/e-study-0.0.1-SNAPSHOT.jar .
COPY ./docker-entrypoint.sh .
ENTRYPOINT [ "/app/docker-entrypoint.sh" ]