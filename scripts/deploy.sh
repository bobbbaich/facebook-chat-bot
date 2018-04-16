#!/usr/bin/env bash

echo "Hello World!"

ssh-keygen -f ~/.ssh/id_rsa -t rsa -N ''
chmod 700 ~/.ssh
ssh-copy-id -i -o StrictHostKeyChecking=no ~/.ssh/id_rsa.pub root@$PROD_HOST_IP

ssh -oStrictHostKeyChecking=no root@$PROD_HOST_IP