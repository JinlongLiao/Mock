@echo off
set MY_CLASSPATH=.
set MY_CLASSPATH=%MY_CLASSPATH%;./lib/*
set cfg_path=%~dp0
set cfg_name=config.properties
echo %cfg_path%%cfg_name%
rem 发送UDP日志IP、 发送UDP日志端口 、日志量 、日志内容开始时间、与结束时间、日志类型(middle 中间件 ssl 网关)、每条日志延迟间隔
rem example java -cp "%MY_CLASSPATH%" kl.logtest.UdpSend 10.0.41.30 514 10 2018-09-12 2018-10-29 ssl 2000
java -DConfig=%cfg_path%%cfg_name% -cp "%MY_CLASSPATH%" kl.logtest.UdpSend 