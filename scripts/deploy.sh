#!/usr/bin/env bash

echo "Hello World!"

ssh-keygen -f id_rsa -t rsa -N ''

ssh-copy-id root@$PROD_HOST_IP

ssh -o "StrictHostKeyChecking no" root@$PROD_HOST_IP