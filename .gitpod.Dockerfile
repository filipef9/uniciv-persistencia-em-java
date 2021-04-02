FROM gitpod/workspace-postgres

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
             && sdk install java 8.0.282.j9-adpt \
             && sdk use java 11.0.10.fx-zulu" 
