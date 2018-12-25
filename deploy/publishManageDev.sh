#!/usr/bin/env bash
cd ..
#mvn clean package install
iphost=10.247.31.142
targetDist=/var/www/tomcat/apache-tomcat-8.0.53/deployWar
rootDist=/root/manage-war
scp cashloan-manage/target/cashloan-manage-1.0.4.war root@$iphost:$targetDist
scp deploy/start.sh root@$iphost:$rootDist
ssh root@$iphost  << EOF
    sh $rootDist/start.sh
    exit;
EOF
