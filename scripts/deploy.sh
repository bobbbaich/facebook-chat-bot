#!/usr/bin/env bash

echo "Hello World!"

ssh-keygen -f ~/.ssh/id_rsa -t rsa -N ''
chmod 700 ~/.ssh
ssh-copy-id -i ~/.ssh/id_rsa.pub -o StrictHostKeyChecking=no root@$PROD_HOST_IP
cat ~/.ssh/id_rsa.pub | cat >> ~/.ssh/authorized_keys

ssh -o PasswordAuthentication=no -o StrictHostKeyChecking=no root@$PROD_HOST_IP
#cat ~/.ssh/id_rsa.pub