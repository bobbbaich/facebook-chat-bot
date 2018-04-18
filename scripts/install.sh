#!/usr/bin/env bash

profile=$1
echo "Stop containers"
docker stop $(docker ps -a -q)
echo "Remove containers"
docker rm $(docker ps -a -q)
echo "Remove twitter-stream-service image"
docker rmi bobbbaich/twitter-stream-analysis

echo "Get git repository HEAD revision"
git reset --hard HEAD
git pull

chmod +x start.sh stop.sh install.sh

echo "Docker compose running..."
cd ..
cd docker

if [ "$profile" = "kafka" ]; then
    echo "Run with profile: kafka"
    docker-compose -f docker-compose-without-web.yml up
else
    echo "Run with profile: default."
    docker-compose up
fi


#echo "Hello World!"
#
#ssh-keygen -f ~/.ssh/id_rsa -t rsa -N ''
#chmod 700 ~/.ssh
#ssh-copy-id -i ~/.ssh/id_rsa.pub -o StrictHostKeyChecking=no root@$PROD_HOST_IP
#cat ~/.ssh/id_rsa.pub | cat >> ~/.ssh/authorized_keys
#
#ssh -o PasswordAuthentication=no -o StrictHostKeyChecking=no root@$PROD_HOST_IP
#cat ~/.ssh/id_rsa.pub