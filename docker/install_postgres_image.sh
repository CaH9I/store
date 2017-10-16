#!/bin/sh
docker create --name shop-pg-app \
    -p 5432:5432 \
    -e POSTGRES_PASSWORD=admin \
    -e POSTGRES_DB=shop \
    -v shop-data:/var/lib/postgresql/data \
    postgres:9.6