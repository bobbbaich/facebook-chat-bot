#!/usr/bin/env bash

echo "Hello World!"

ssh-keygen -f ~/.ssh/id_rsa -t rsa -N ''
chmod 700 ~/.ssh
ssh-copy-id -i ~/.ssh/id_rsa.pub root@$PROD_HOST_IP

ssh -oStrictHostKeyChecking=no -oUserKnownHostsFile=/dev/null root@$PROD_HOST_IP