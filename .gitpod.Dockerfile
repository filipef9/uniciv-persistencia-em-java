FROM gitpod/workspace-postgres

USER root

RUN mkdir -p /var/lib/pgadmin && \
    mkdir -p /var/log/pgadmin && \
    chown gitpod /var/lib/pgadmin && \
    chown gitpod /var/log/pgadmin

RUN apt-get update && \
    apt-get install -y build-essential libssl-dev libffi-dev libgmp3-dev python3-virtualenv libpq-dev python3-dev netcat && \
    apt-get clean

RUN pip install pgadmin4

RUN pwd && ls -liah
RUN chmod +x pgadmin4/setup-web-unattended.sh 

USER gitpod
