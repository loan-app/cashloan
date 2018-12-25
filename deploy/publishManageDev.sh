#!/usr/bin/env bash
cd ..
#mvn clean package install
scp cashloan-manage/target/cashloan-manage-1.0.4.war root@10.247.31.142:/var/www/tomcat/apache-tomcat-8.0.53/deployWar
ssh root@10.247.31.142  << EOF
    sh /root/manage-war/deploy.sh
    exit;
EOF
