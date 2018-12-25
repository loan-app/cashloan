#!/usr/bin/env bash
cd ..
#mvn clean package install
scp cashloan-manage/target/cashloan-manage-1.0.4.war root@47.110.61.233:/usr/local/apache-tomcat-8.0.53/manage-war
ssh root@47.110.61.233  << EOF
    #sh /root/manage-war/deploy.sh
    exit;
EOF
