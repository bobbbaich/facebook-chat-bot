#!/usr/bin/env bash

echo "Hello World!"

ssh-keygen -f id_rsa -t rsa -N ''
ssh -o "StrictHostKeyChecking no" root@$PROD_HOST_IP