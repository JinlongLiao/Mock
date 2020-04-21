#!/bin/sh
export MY_CLASSPATH=.
MY_CLASSPATH=$MY_CLASSPATH:./lib/*
export cfg_path=$0
export cfg_name=config.properties
echo $cfg_path$cfg_name
# 发送UDP日志IP、 发送UDP日志端口 、日志量 、日志内容开始时间 与结束时间 日志类型（middle 中间件 ssl 网关）延时
java -DConfig=$cfg_path$cfg_name  -cp $MY_CLASSPATH kl.logtest.UdpSend 127.0.0.1 3000 1000 2017-08-12 2018-09-12 ssl 200
© 2020 GitHub, Inc.