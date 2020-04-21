package com.liaojl.log.logtest;

import com.liaojl.log.udplogsend.InitData;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class UdpSend {
    public static final DateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws Exception {
        String ip = InitProperties.TARGE_IP;
        String beg = ISO_DATE_FORMAT.format(InitProperties.TIME_START);
        String end = ISO_DATE_FORMAT.format(InitProperties.TIME_END);
        int port = InitProperties.TARGE_PORT;
        long count = InitProperties.LOG_COUNT;
        String type = InitProperties.LOG_TYPE;
        String charset = InitProperties.LOG_CHARSET;
        Long sleep = InitProperties.LOG_DELAY;
        DatagramSocket ds = new DatagramSocket(8888);

        List<String> data1 = new ArrayList<String>();
        List<String> data2 = new ArrayList<String>();

        data1.add(
                "Reg_ID=\"koalmiddle\" SN=\"ad6ca5359cf6482a\" User_Name=\"%s\" User_ID=\"%s\" Organization_ID=\"32010000\" Operate_Time=\"%s\" Terminal_ID=\"%s\" Operate_Type=\"0\" Operate_Result=\"1\" Error_Code=\"\" Fail_Reason=\"\" Version=\"KOAL_MID_JAVA_5.0\"");

        data1.add(
                "Reg_ID=\"koalmiddle\" SN=\"ad6ca5359cf6482a\" User_Name=\"%s\" User_ID=\"%s\" Organization_ID=\"32010000\" Operate_Time=\"%s\" Terminal_ID=\"%s\" Operate_Type=\"0\" Operate_Result=\"0\" Error_Code=\"3000\" Fail_Reason=\"\u8BC1\u4E66\u94FE\u9A8C\u8BC1\u672A\u901A\u8FC7\" Version=\"KOAL_MID_JAVA_5.0\"");
        data1.add(
                "Reg_ID=\"koalmiddle\" SN=\"ad6ca5359cf6482a\" User_Name=\"%s\" User_ID=\"%s\" Organization_ID=\"32010000\" Operate_Time=\"%s\" Terminal_ID=\"%s\" Operate_Type=\"0\" Operate_Result=\"0\" Error_Code=\"3000\" Fail_Reason=\"\u8BC1\u4E66\u94FE\u9A8C\u8BC1\u672A\u901A\u8FC7\" Version=\"KOAL_MID_JAVA_5.0\"");

        data2.add(
                "session_id=\"%s\" Reg_ID=\"ssl524\" SSL-HRP DATE=\"%s\" CN=\"%s\" SN=\"%s\" ST=\"32\" L=\"05\" L1=\"00\" O=\"22\" OU=\"06\" OU1=\"00\" ip=\"%s\" url=\"http://%s:11080/script/app\" result=\"\u672A\u4FEE\u6539\uFF0C\u65E0\u987B\u4E0B\u8F7D\" Version=\"KOAL_SSL_Sp4\"");
        data2.add(
                "session_id=\"%s\" Reg_ID=\"ssl524\" SSL-HRP DATE=\"%s\" CN=\"%s\" SN=\"%s\" ST=\"32\" L=\"05\" L1=\"00\" O=\"22\" OU=\"06\" OU1=\"00\" ip=\"%s\" url=\"http://%s:11080/script/app\" result=\"\u672A\u4FEE\u6539\uFF0C\u65E0\u987B\u4E0B\u8F7D\" Version=\"KOAL_SSL_Sp4\"");

        data2.add(
                "AuthLog[2659]: DATE=\"%s\" User_Name=\"%s\" User_ID=\"%s\" APPID=\"sslSSO\" GATE_IP=\"%s\" CLIENT_IP=\"%s\" CERT_SERIAL=\"571C6A453DED7E22386C60B1C880B0BC\" Organization_ID=\"120000061209\" Operate_Type=\"\u8BC1\u4E66\u8BA4\u8BC1\" Operate_Result=\"\u6210\u529F\" Error_Code=\"\"  Version=\"KOAL_SSL_SSO\"");

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String tem = "";
            if ("middle".equalsIgnoreCase(type)) {
                int ran = random.nextInt(data1.size());

                String str = (String) data1.get(ran);
                tem = String.format(str, new Object[]{InitData.getName(), UUID.randomUUID().toString(),
                        InitData.randomDate(beg, end), InitData.getRandomIp()});
            } else {
                int ran = random.nextInt(data2.size());
                String str = (String) data2.get(ran);
                if (str.contains("AuthLog")) {
                    tem = String.format(str, new Object[]{InitData.randomDate(beg, end), InitData.getName(),
                            UUID.randomUUID().toString(), InitData.getRandomIp(), InitData.getRandomIp()});
                } else {
                    tem = String.format(str,
                            new Object[]{UUID.randomUUID().toString() + UUID.randomUUID().toString(),
                                    InitData.randomDate(beg, end), InitData.getCN(), UUID.randomUUID().toString(),
                                    InitData.getRandomIp(), InitData.getRandomIp()});
                }
            }

            charset = charset == null ? "GBK" : charset;
            byte[] buf = new String(tem.getBytes()).getBytes(charset);
            System.out.println("字符集：" + charset + new String(buf, charset));

            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName(ip), port);
            Thread.sleep(sleep);
            ds.send(dp);
        }
        ds.close();
    }
}