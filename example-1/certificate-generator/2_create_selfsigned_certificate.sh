#!/bin/bash

openssl pkcs12 -export -in ../cert.pem -inkey ../key.pem -out ../certificate.p12 -name "certificate"
