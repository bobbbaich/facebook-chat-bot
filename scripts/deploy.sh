#!/usr/bin/env bash

echo "Hello World!"


ssh-keygen -t rsa
ssh -o "StrictHostKeyChecking no" root@$PROD_HOST_IP


