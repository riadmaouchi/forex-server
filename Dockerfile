# Dockerfile

FROM  mbe1224/debian-oracle-java

USER root

# SERVER PORT
EXPOSE 7070

# GET FOREX server
COPY ./target/forex-server.tar.gz /tmp/

RUN rm -rf /tmp/FOREX-SERVER && mkdir -p /tmp/FOREX-SERVER && cd /tmp/FOREX-SERVER && tar -xvf /tmp/forex-server.tar.gz

RUN whoami && ls -l /tmp/FOREX-SERVER/startup.sh && chmod +x /tmp/FOREX-SERVER/startup.sh

# EXECUTE ENTRY POINT LAUNCHING THE SERVER
ENTRYPOINT [ "/tmp/FOREX-SERVER/startup.sh" ]