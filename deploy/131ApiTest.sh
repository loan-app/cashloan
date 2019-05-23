#!/usr/bin/env bash
cd ..
mvn clean package install
#iphost=10.247.31.142
iphost=47.110.124.131
dest=cashloan-api/target/cashloan-api-1.0.4.war
targetDist=/usr/local/apache-tomcat-8.0.53/api-war
rootDist=/root/api-war
scp $dest root@$iphost:$targetDist
scp deploy/start.sh root@$iphost:$rootDist
ssh root@$iphost  << EOF
    sh $rootDist/start.sh
    exit;
EOF