#!/usr/bin/env bash
cd ../cashloan-manage/src/main/
npm run deploy
cd ../../../

mvn clean package install
#iphost=10.247.31.142
iphost=47.110.124.131
dest=cashloan-manage/target/cashloan-manage-1.0.4.war
targetDist=/usr/local/manage/apache-tomcat-8.0.53/manage
rootDist=/root/manage-war
scp $dest root@$iphost:$targetDist
scp deploy/start.sh root@$iphost:$rootDist
ssh root@$iphost  << EOF
    sh $rootDist/start.sh
    exit;
EOF

