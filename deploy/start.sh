#!/bin/bash
ps -ef|grep "apache-tomcat-8.0.53"|awk '{print $2}'|xargs kill -9 {}
#tomocat=/var/www/tomcat/apache-tomcat-8.0.53
tomocat=/usr/local/apache-tomcat-8.0.53
sleep 3
sh ${tomocat}/bin/startup.sh