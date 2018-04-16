#!/usr/bin/env bash

echo "Hello World!"

ssh-keygen -f '~/.ssh/id_rsa.pub' -t rsa -N ''

chmod 700 ~/.ssh

ssh-copy-id -i '~/.ssh/id_rsa.pub' root@$PROD_HOST_IP

ssh -o "StrictHostKeyChecking no" root@$PROD_HOST_IP