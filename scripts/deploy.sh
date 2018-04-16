#!/usr/bin/env bash

echo "Hello World!"

ssh-keygen -f ~/.ssh/id_rsa -t rsa -N ''
chmod 700 ~/.ssh
#ssh-copy-id -i ~/.ssh/id_rsa.pub -o StrictHostKeyChecking=no root@$PROD_HOST_IP
cat ~/.ssh/id_rsa.pub |  mkdir -p ~/.ssh && chmod 700 ~/.ssh && cat >> ~/.ssh/authorized_keys

ssh root@$PROD_HOST_IP
# -o PasswordAuthentication=no -o StrictHostKeyChecking=no
#cat ~/.ssh/id_rsa.pub