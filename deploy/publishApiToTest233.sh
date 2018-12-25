#!/usr/bin/env bash
cd ..
mvn clean package install
#iphost=10.247.31.142
iphost=47.110.61.233
dest=cashloan-api/target/cashloan-api-1.0.4.war
targetDist=/usr/local/apache-tomcat-8.0.53/api-war
rootDist=/root/manage-war
scp $dest root@$iphost:$targetDist
scp deploy/start.sh root@$iphost:$rootDist
ssh root@$iphost  << EOF
    sh $rootDist/start.sh
    exit;
EOF
