@echo off
set MY_CLASSPATH=.
set MY_CLASSPATH=%MY_CLASSPATH%;./lib/*
set cfg_path=%~dp0
set cfg_name=config.properties
echo %cfg_path%%cfg_name%
rem ����UDP��־IP�� ����UDP��־�˿� ����־�� ����־���ݿ�ʼʱ�䡢�����ʱ�䡢��־����(middle �м�� ssl ����)��ÿ����־�ӳټ��
rem example java -cp "%MY_CLASSPATH%" kl.logtest.UdpSend 10.0.41.30 514 10 2018-09-12 2018-10-29 ssl 2000
java -DConfig=%cfg_path%%cfg_name% -cp "%MY_CLASSPATH%" kl.logtest.UdpSend 