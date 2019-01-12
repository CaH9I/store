#!/bin/sh
cd /webapps/store/store-web
sudo nohup java -jar target/store-web-1.1.jar > $HOME/store.log 2>&1 &