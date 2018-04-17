#!/usr/bin/env bash
echo "Stop containers"
docker stop $(docker ps -a -q)
echo "Remove containers"
docker rm $(docker ps -a -q)
echo "Remove images"
docker rmi $(docker images -a -q)

echo "Get git repository HEAD revision"
git reset --hard HEAD
git pull

echo "Docker compose running..."
cd ..
cd docker
docker-compose up


#echo "Hello World!"
#
#ssh-keygen -f ~/.ssh/id_rsa -t rsa -N ''
#chmod 700 ~/.ssh
#ssh-copy-id -i ~/.ssh/id_rsa.pub -o StrictHostKeyChecking=no root@$PROD_HOST_IP
#cat ~/.ssh/id_rsa.pub | cat >> ~/.ssh/authorized_keys
#
#ssh -o PasswordAuthentication=no -o StrictHostKeyChecking=no root@$PROD_HOST_IP
#cat ~/.ssh/id_rsa.pub