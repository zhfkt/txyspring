#!/bin/bash

set -v on

cd /root/develop/txyspring
git pull

service tomcat7 stop
service tomcat stop

cd /root/develop/txyspring/install

#mysql -uroot -ppassword -v -t < install.sql 

service tomcat7 start
service tomcat start 

#sleep 10

cd /root/develop/txyspring/

mvn tomcat7:redeploy


