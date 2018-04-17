#!/usr/bin/env bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

git reset --hard HEAD
git pull

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