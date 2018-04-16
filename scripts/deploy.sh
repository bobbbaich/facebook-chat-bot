#!/usr/bin/env bash

apt-get install sshpass

echo "Hello World!"

ssh-keygen -f ~/.ssh/id_rsa -t rsa -N ''
chmod 700 ~/.ssh
ssh-copy-id -i ~/.ssh/id_rsa.pub root@$PROD_HOST_IP

ssh -o "UserKnownHostsFile=/dev/null" -o "StrictHostKeyChecking no"
sshpass -p $PROD_PASSWORD ssh -o StrictHostKeyChecking=no root@$PROD_HOST_IP