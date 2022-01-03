FROM gitpod/workspace-full

RUN bash -c "$(curl -fsSL https://raw.githubusercontent.com/ohmybash/oh-my-bash/master/tools/install.sh)"

RUN sudo apt-get update &&\
    sudo apt-get install -y \
        fonts-firacode &&\
    sudo rm -rf /var/lib/apt/lists/*

RUN \
  cd /tmp &&\
  wget https://download.jetbrains.com/fonts/JetBrainsMono-2.225.zip &&\
  unzip JetBrainsMono-2.225.zip &&\
  sudo cp -Rf fonts/* /usr/share/fonts &&\
  fc-cache -f -v

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && sdk install java 8.0.292.j9-adpt && sdk default java 11.0.13.fx-zulu"