#!/bin/bash

cd /data/wwwroot/47.111.255.20
#下载新包
wget http://icebar.oss-cn-shenzhen.aliyuncs.com/%E6%B0%94%E8%B1%A1/SugarlessGirl-main.jar
#备份
mv -f SugarlessGirl.jar SugarlessGirl.jar.bak

#命名新包
mv -f SugarlessGirl-main.jar SugarlessGirl.jar
#重启
./boot-run.sh restart SugarlessGirl.jar