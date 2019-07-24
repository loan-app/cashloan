#!/bin/bash
ps -ef|grep "local/apache-tomcat-8.0.53"|awk '{print $2}'|xargs kill -9 {}
tomcat=/usr/local/apache-tomcat-8.0.53
sleep 3s
sh ${tomcat}/bin/startup.sh