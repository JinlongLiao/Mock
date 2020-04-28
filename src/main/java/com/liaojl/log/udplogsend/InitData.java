package com.liaojl.log.udplogsend;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author BinZhang
 * @date 2018年4月2日
 */
public class InitData {

    private static InitData instance = null;

    public static InitData getInstance() {
        if (null == instance) {
            instance = new InitData();
        }
        return instance;
    }

    /**
     * 获取身份证号
     *
     * @return
     */
    public static String getIdCard() {
        Random ran = new Random();
        int a = ran.nextInt(99999999);
        int b = ran.nextInt(99999999);
        long l = a * 10000000L + b;
        String num = String.valueOf(l);
        return num.trim();
    }

    /**
     * 随机获取姓名
     *
     * @return
     */
    public static String getName() {
        Random random = new Random();
        String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦",
                "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦",
                "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
                "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐",
                "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵",
                "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
                "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
        String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
        String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
        int index = random.nextInt(Surname.length - 1);
        String name = Surname[index]; // 获得一个随机的姓氏
        int i = random.nextInt(3);// 可以根据这个数设置产生的男女比例
        if (i == 2) {
            int j = random.nextInt(girl.length() - 2);
            if (j % 2 == 0) {
                name = "" + name + girl.substring(j, j + 2);
            } else {
                name = "" + name + girl.substring(j, j + 1);
            }
        } else {
            int j = random.nextInt(girl.length() - 2);
            if (j % 2 == 0) {
                name = "" + name + boy.substring(j, j + 2);
            } else {
                name = "" + name + boy.substring(j, j + 1);
            }
        }
        return name.trim();
    }

    /**
     * 获取CN项
     *
     * @return
     */
    public static String getCN() {
        return getName() + " " + getIdCard();
    }

    /**
     * 批量获取数据CN
     *
     * @param count
     * @return
     */
    public List<String> getCN(int count) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            result.add(getCN());
        }

        return result;

    }

    /**
     * 随机生成ip
     */
    public static String getRandomIp() {
        // ip范围
        int[][] range = {{607649792, 608174079}, // 36.56.0.0-36.63.255.255
                {1038614528, 1039007743}, // 61.232.0.0-61.237.255.255
                {1783627776, 1784676351}, // 106.80.0.0-106.95.255.255
                {2035023872, 2035154943}, // 121.76.0.0-121.77.255.255
                {2078801920, 2079064063}, // 123.232.0.0-123.235.255.255
                {-1950089216, -1948778497}, // 139.196.0.0-139.215.255.255
                {-1425539072, -1425014785}, // 171.8.0.0-171.15.255.255
                {-1236271104, -1235419137}, // 182.80.0.0-182.92.255.255
                {-770113536, -768606209}, // 210.25.0.0-210.47.255.255
                {-569376768, -564133889}, // 222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "."
                + Integer.toString(b[3]);
        return x;
    }

    /**
     * 随机生成10.开头的url
     */
    public String getUrl() {

        String ip = getRandomIp();
        String replace = ip.substring(0, ip.indexOf("."));
        String url = ip.replace(replace, "10");
        return url;

    }

    /**
     * 随机生成制定范围内的日期
     *
     * @param args
     */

    /**
     * 获取随机日期
     *
     * @param beginDate 起始日期，格式为：yyyy-MM-dd
     * @param endDate   结束日期，格式为：yyyy-MM-dd
     * @return
     */
    public static String randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() > end.getTime()) {
                return null;
            } else if (start.getTime() == end.getTime()) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            }
            long date = random(start.getTime(), end.getTime());
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    /**
     * 获取指定的CN数量
     *
     * @return
     */
    public List<String> getCNs(int size) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            String CN = getCN();
            if (list.size() < size && !list.contains(CN)) {
                list.add(CN);
            }
        }

        return list;
    }

    /**
     * 指定url数量(应用数量)
     */
    public List<String> getApps(int size) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            String url = getUrl();
            if (list.size() < size && !list.contains(url)) {
                list.add(url);
            }
        }
        return list;
    }

    /**
     * 指定url数量(应用数量)
     */
    public List<String> getIps(int size) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String ip = getRandomIp();
            if (list.size() < size && !list.contains(ip)) {
                list.add(ip);
            }
        }
        return list;
    }

    /**
     * 获取一条随机日志
     *
     * @param cn
     * @param app
     * @param date
     * @return
     */
    public String getLogs(String cn, String app, String date) {
        Random random = new Random();
        List<String> list = logContents(cn, app, date);
        String log = list.get(random.nextInt(list.size()));
        return log;
    }

    /**
     * 生成日志集合
     *
     * @param cn
     * @param app
     * @param date
     * @return
     */
    public List<String> logContents(String cn, String app, String date) {
        List<String> contentList = new ArrayList<String>();
        // RA操作日志
        String raLog = "<14>date=\"%s\" operationid=\"reapply_accept\" actiontype=\"execute\" operator=\"C=CN,O=koal,OU=koal,E=RABusiOper@koal.com,CN=RABusiOper\" userdn=\"O=格尔软件,E=ktu3@koal.com,CN=%s\" result=\"true\" errormsg=\"null\" senderip=\"%s\"";
        String raContent = String.format(raLog, date, cn, app);

        // 网关日志
        String wglog = "<150>NRPd[29888]:  DATE=\"%s\" ip=\"%s\" CN=\"%s\" GN=\"\" RESULT=\"200\" OUTBYTES=\"135\" INBYTES=\"455\" DURATION=\"2\" url=\"http://%s/javascript/jquery.min.?3.1.4\" ";
        String wgContent = String.format(wglog, date, app, cn, app);

        // KMAIL邮件日志--管理员登陆
        String kmailLoginLog = "<174>senderId=\"kmail\",logType=\"2\",logTime=\"%s\",subjectId=\"kmail安全保密管理员\",opName =\"登录\",opContent=\"\",opResult=\"1\",opDesc=\"成功\",subjectIp=\"%s\",timestamp=\"\",signature=\"\"";
        String kmailLoginContent = String.format(kmailLoginLog, date, app);

        // KMAIL邮件日志--用户发信
        String kmailSendMail = "<174>senderId=\"kmail\",logType=\"1\",logTime=\"%s\",subjectId=\"testUser1[user1@koa.com][测试机构]\",opName =\"发送邮件\",opContent=\"<邮件日志> [发件人=user1[user1@koa.com][测试], 收件人=【收件人:user1<user1@koa.com>】, 主题= 邮件主题Test, 附件信息=setup.conf[本地][非密](166 B), 加密=否, 签名=否, 备注=动作=发送邮件;包含附件=是【setup.conf[本地][非密](166 B)】;结果=发送成功;]\",opResult=\"1\",opDesc=\"成功\",subjectIp=\"%s\",timestamp=\"\",signature=\"\"";
        String kmailSendMailContent = String.format(kmailSendMail, date, app);

        // KMAIL邮件日志--管理员删除组邮箱
        String kmailDeleteMail = "<174>senderId=\"kmail\",logType=\"2\",logTime=\"%s\",subjectId=\"系统管理员\",opName =\"管理员删除\",opContent=\"组邮箱 [名称=123, 邮箱=123@koa.com, 类型=不公开, 备注=, 添加时间=2018-06-14 17:27:13]\",opResult=\"1\",opDesc=\"成功\",subjectIp=\"%s\",timestamp=\"\",signature=\"\"";
        String kmailDeleteMailContent = String.format(kmailDeleteMail, date, app);
        // 河南公A日志
        String hngaLog = "<11>Feb 24 22:51:02 KSSL-MAIN SSL-HRP[9503]: date=%s dn=\"/C=CN/ST=41/L=03/L=27/O=81/OU=00/OU=00/CN=%s\" ip=%s url=http://192.168.1.8 optType=- optResult=0";
        String hngaLogContent = String.format(hngaLog, date, cn, app);

        // 湖南信息所网关日志
        String hnxxsLog = "<143>SSL-HRP[3234]:   date=\"%s\" id=\"test\" ip=\"%s\" url=\"http://taobao.ccc/javascript/jquery.min?3.1.4\" result=\"成功\"";
        String hnxxsLogContent = String.format(hnxxsLog, date, app);

        // SAMP日志
        String sampLog = "<143>PMS: DATE=\"%s\" PU=\"CN=%s;G=张三;OU=A0255;CIP=%s\" PL=\"rr\" url=\"http://192.168.1.60:80/javascript/jquery.min?3.1.4\" result=\"成功\"";
        String sampLogContent = String.format(sampLog, date, cn, app);

        // 公司网关
        String gswgLog = "<150>NRPd[29969]:  DATE=\"%s\" protocol=\"TLSv1\" ip=\"%s\" CN=\"%s\" GN=\"\" RESULT=\"200\" OUTBYTES=\"15223\" INBYTES=\"882\" DURATION=\"514\" url=\"http://192.168.199.100:80/ref.list.do?\"";
        String gswgLogContent = String.format(gswgLog, date, app, cn);

        // 公司VPN
        String gsVPNLog = "2017-03-23 15:38:04 [INFO ] service.LogProcesser (run:73) - ----process--<150>KOALVPN-AUDIT:  date=\"%s\" CN=\"%s\" SN=\"1E3D2CEA54E525C9B8DFEAC9\" destination_ip=\"%s\" destination_port=\"53\" source_ip=\"%s\" source_port=\"59997\" protocol=\"vpn\"";
        String gsVPNLogContent = String.format(gsVPNLog, date, cn, app, app);

        // 网络保险箱
        String wlbxxLog = "<15>May 3 12:56:56 KUB kofferd[19319]: lt=kof-login;timedone=%s;user=ren_operation;op=登录;ip=%s;client=客户端;reason=用户正常登录;result=成功;dept_code=1.10001";
        String wlbxxLogContent = String.format(wlbxxLog, date, app);

        // 山东省公安厅网关日志
        String sdgawgLog = "<150>NRPd[29888]:  DATE=\"%s\" ip=\"%s\" CN=\"%s\" URL=\"/app/r/page?34-1.I BehaviorListener.0-wBody-panel-conent&rendom=0.76875657\" RESULT=\"200\" url=\"http://192.168.1.60:80/javascript/jquery.min.?3.1.4\" ";
        String sdgawgLogContent = String.format(sdgawgLog, date, app, cn);

        // 5.2.4 sp4
        String sp4Log = "DATE=\"2017-08-17 15:53:52\" CN=\"SSLTest5 111111111111111111\" GN=\"-\" O=\"koal\" OU=\"koal\" L=\"sh\" ST=\"sh\" ip=\"192.168.35.1\" url=\"http://192.168.190.7/login.php\" result=\"成功\"";
        String sp4LogContent = String.format(sp4Log, date, app, cn);

        // 5.2.4 sp5
        String sp5Log = "PID=\"4501\" DATE=\"%s\" CN=\"%s\" GN=\"-\" O=\"组织test1\" OU=\"机构test1\" L=\"上海市\" ST=\"上海\" ip=\"%s\" url=\"http://192.168.30.42:8080/favicon\" result=\"成功\" downflow=\"21630\" upflow=\"0\" duration=\"8\" ";
        String sp5LogContent = String.format(sp5Log, date, cn, app);
        // 5.2.4 sp6
        String sp6Log = "PID=\"11233\" DATE=\"%s\" CN=\"%s\" GN=\"-\" O=\"组织test1\" OU=\"机构test1\" L=\"上海市\" ST=\"上海\" ip=\"%s\" url=\"http://192.168.30.42:8080/favicon\" result=\"成功\" downflow=\"21630\" upflow=\"0\" duration=\"10\" User-agent=\"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729)\"";
        String sp6LogContent = String.format(sp6Log, date, cn, app);
        // 6.1.4 sp1
        String sp1Log = "DATE=\"%s\" ip=\"%s\" CN=\"%s\" O=\"组织test1\" OU=\"机构test1\" Email=\"test1@koal.com\" GN=\"-\" L=\"上海市\" ST=\"上海\" URL=\"/\" RESULT=\"200\" OUTBYTES=\"8628\" INBYTES=\"441\" DURATION=\"12\" SERVICE_INFO=\"192.168.100.73:443\" url=\"http://192.168.30.42:8080/\" ";
        String sp1LogContent = String.format(sp1Log, date, app, cn);
        // 6.1.4 sp2
        String sp2Log = "DATE=\"%s\" ip=\"%s\" CN=\"%s\" O=\"组织test1\" OU=\"机构test1\" Email=\"test1@koal.com\" GN=\"-\" L=\"上海市\" ST=\"上海\" URL=\"/\" RESULT=\"200\" OUTBYTES=\"8628\" INBYTES=\"395\" DURATION=\"9\" SERVICE_INFO=\"192.168.100.73:443\" url=\"http://192.168.30.42:8080/\" User-Agent=\"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729)\" ";
        String sp2LogLogContent = String.format(sp2Log, date, app, cn);
        // 公安中间件日志
        String gazjjLog1 = "Reg_ID=\"testAppCode\" SN=\"7aa4000000000039eb0ef07f\" User_Name=\"test2017051702\" User_ID=\"201705170200000000\" Organization_ID=\"320100000000\" Operate_Time=\"%s\" Terminal_ID=\"%s\" Operate_Type=\"0\" Operate_Result=\"0\" Error_Code=\"3000\" Fail_Reason=\"证书链验证未通过\"";
        String gazjjLog1Content = String.format(gazjjLog1, date, app);
        String gazjjLog2 = "Reg_ID=\"testAppCode\" SN=\"7aa4000000000039eb0ef07f\" User_Name=\"test2017051702\" User_ID=\"201705170200000000\" Organization_ID=\"320100000000\" Operate_Time=\"%s\" Terminal_ID=\"%s\" Operate_Type=\"0\" Operate_Result=\"1\" Error_Code=\"\" Fail_Reason=\"\"";
        String gazjjLog2Content = String.format(gazjjLog2, date, app);
        // 公安集中认证网关日志
        String gajzrzwgLog = "DATE=\"%s\" User_Name=\"张三\" User_ID=\"012345679845140079\" APPID=\"testappid\"  GATE_ID=\"192.168.190.7\" CLIENT_IP=\"%s\" CERT_SERIAL=\"15CCF0FF2BE97AAF38FF63D8\" Organization_ID=\"FJRootCA\" Operate_Type=\"证书认证\" Operate_Result=\"成功\" Error_Code=\"1000\"";
        String d = date.replace("-", "/");
        String gajzrzwgLogContent = String.format(gajzrzwgLog, d, app);
        // RA操作日志
        String raczLog1 = "<14>date=\"%s\" operationid=\"keyupdate_apply\" actiontype=\"execute\" operator=\"C=CN,O=koal,OU=koal,E=RABusiOper@koal.com,CN=RABusiOper\" userdn=\"C=CN,O=测试园区,CN=%s\" result=\"true\" errormsg=\"null\" senderip=\"%s\"";
        String raczLog1Content = String.format(raczLog1, date, cn, app);
        String raczLog2 = "<14>date=\"%s\" operationid=\"reapply_accept\" actiontype=\"execute\" operator=\"C=CN,O=koal,OU=koal,E=RABusiOper@koal.com,CN=RABusiOper\" userdn=\"O=格尔软件,E=ktu3@koal.com,CN=%s\" result=\"true\" errormsg=\"null\" senderip=\"null\"";
        String raczLog2Content = String.format(raczLog2, date, cn);
        // RA服务日志
        String rafwLog1 = "<14>date=\"%s\" operationid=\"revoke_cert@notodo\" actiontype=\"execute\" operator=\"C=CN,O=koal,OU=koal,E=RABusiOper@koal.com,CN=RABusiOper\" userdn=\"O=格尔软件,E=ktu3@koal.com,CN=%s\" result=\"true\" errormsg=\"null\" senderip=\"null\"";
        String rafwLog1Content = String.format(rafwLog1, date, cn);
        String rafwLog2 = "<14>date=\"%s\" operationid=\"reissue\" actiontype=\"execute\" operator=\"C=CN,O=koal,OU=koal,E=RABusiOper@koal.com,CN=RABusiOper\" userdn=\"O=格尔软件,E=ktu3@koal.com,CN=%s\" result=\"true\" errormsg=\"null\" senderip=\"null\"";
        String rafwLog2Content = String.format(rafwLog2, date, cn);
        // CA操作日志
        String caczLog1 = "<14>time=\"%s\" system=\"CAAdmin\" operator=\" \" oper_action=\"管理员登录\" oper_object=\"管理员登录\" result=\"0\" result_msg=\"成功\"";
        String caczLog1Content = String.format(caczLog1, date);
        String caczLog2 = "<14>time=\"%s\" system=\"CAAdmin\" operator=\" \" oper_action=\"写日志配置\" oper_object=\"写日志配置\" result=\"0\" result_msg=\"成功\"";
        String caczLog2Content = String.format(caczLog2, date, app, cn);
        // CA服务日志
        String cafwLog1 = "<14>time=\"%s\" system=\"CertService\" service_asker=\"www.koalra.com\" service_type=\"证书废除服务\" subject=\"CN=%s, E=ktu3@koal.com, O=格尔软件\" result=\"0\" result_msg=\"success\" issuer=\"CA_RSA\" senderip=\" \"";
        String cafwLog1Content = String.format(cafwLog1, date, cn);
        String cafwLog2 = "<14>time=\"%s\" system=\"CertService\" service_asker=\"www.koalra.com\" service_type=\"签发证书服务\" subject=\"CN=%s, O=测试园区, C=CN\" result=\"0\" result_msg=\"success\" issuer=\"CA_RSA\" senderip=\" \"";
        String cafwLog2Content = String.format(cafwLog2, date, app, cn);
        String cafwLog3 = "<14>time=\"%s\" system=\"CertService\" service_asker=\"www.koalra.com\" service_type=\"证书更新服务\" subject=\"CN=%s, E=ktu3@koal.com, O=格尔软件\" result=\"0\" result_msg=\"success\" issuer=\"CA_RSA\" senderip=\" \"";
        String cafwLog3Content = String.format(cafwLog3, date, cn);
        // KM操作日志
        String kmczLog = "<14>timedone=%s;admin=KMBusiOper;op=更新日志配置;obj=logfilename=D:\\CA6.6.0\\SYT0901-KOALKM\\kmc.log, logFileMaxSize=10000KB, logFileMaxIndex=5, syslogHost=192.168.136.1, syslogFileOut=true, syslogConsoleOut=true, sysloglevel=一般, servicelogFileOut=true, servicelogConsoleOut=true, serviceloglevel=调试;result=成功 time=\"%s\"";
        String kmczLogContent = String.format(kmczLog, date, date);
        // KM服务日志
        String kmfwLog = "CA_ID=100, CLIENT_IP='%s', ACTION='密钥分发', DESC='完成密钥分发,KeyIndex=G/EXPQ4RYqwizvhrjtaA3M4+qBQ=', encSN='26892554857327426002406262019', RESULT='成功' time=\"%s\"";
        String kmfwLogContent = String.format(kmfwLog, app, date, cn);

        // 添加到队列中
        contentList.add(raContent);
        contentList.add(wgContent);
        contentList.add(kmailLoginContent);
        contentList.add(kmailSendMailContent);
        contentList.add(kmailDeleteMailContent);
        contentList.add(hngaLogContent);
        contentList.add(hnxxsLogContent);
        contentList.add(sampLogContent);
        contentList.add(gswgLogContent);
        contentList.add(gsVPNLogContent);
        contentList.add(wlbxxLogContent);
        contentList.add(sdgawgLogContent);
        contentList.add(sp4LogContent);
        contentList.add(sp5LogContent);
        contentList.add(sp6LogContent);
        contentList.add(sp1LogContent);
        contentList.add(sp2LogLogContent);
        contentList.add(gazjjLog1Content);
        contentList.add(gazjjLog2Content);
        contentList.add(gajzrzwgLogContent);
        contentList.add(raczLog1Content);
        contentList.add(raczLog2Content);
        contentList.add(rafwLog1Content);
        contentList.add(rafwLog2Content);
        contentList.add(caczLog1Content);
        contentList.add(caczLog2Content);
        contentList.add(cafwLog1Content);
        contentList.add(cafwLog2Content);
        contentList.add(cafwLog3Content);
        contentList.add(kmczLogContent);
        contentList.add(kmfwLogContent);

        return contentList;

    }

    public static String getID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        return sdf.format(new Date(System.currentTimeMillis())) + new Random().nextInt();
    }

    public static void main(String[] args) throws InterruptedException, SQLException {
//        String userSql = "select USER_ID,USER_NAME,ORG_ID,ID_CARD_NO,ORG_PATH FROM VW_USER limit 30000";
//        String appSql = "select APP_ID,APP_NAME FROM Tb_APP ";
//        List<User> userList = UserJDBC.getInstance().query(userSql);
//        List<App> appList = AppJDBC.getInstance().query(appSql);
//        List<String> ipLists = InitData.getInstance().getApps(10);
//        for (int i = 0; i < 2; i++) {
//            Log log = InitData.getInstance().getLogs(userList, appList, ipLists, "2017-03-01", "2018-03-01");
//            log.info(log.toString());
//        }
    }
}