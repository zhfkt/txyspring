#!/bin/bash

set -v on

service tomcat7 stop

cd /root/develop/txyspring/install
mysql -uroot -ppassword -v -t < install.sql 

service tomcat7 start
/usr/share/tomcat7/bin/setenv.sh

#sleep 10

cd /root/develop/txyspring/
git pull
mvn tomcat7:redeploy


