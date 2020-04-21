package com.liaojl.log.udplogsend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liaojl
 *
 */
public class LogModel {
	public static Map<String, List<String>> modelsOfType;
	public static Map<String, List<String>> modelsOfName;
	private List<String> returnModes;
	private static List<String> data1 = new ArrayList<String>();
	private static List<String> data2 = new ArrayList<String>();
	private static List<String> data3 = new ArrayList<String>();

	static {

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

		data3.add(
				"<14>Oct 18 15:17:35 KSSLHA-MAIN SSL-HRP[3401]: logflag=\"TBSGS\" userip=\"112.65.48.10\" accessurl=\"tcp://10.0.5.85:9900\" orgcode=\"-\" username=\"潘路\" identity=\"-\" accessreturn=\"Y\" reason=\"成功\" tbsgip=\"10.0.5.88\" proxycn=\"-\" terminalid=\"-\" time=\"2018-10-28 15:17:35\" bytes=\"197\" upbytes=\"132\" serviceid=\"3\"");
		modelsOfName.put(LogModelType.SSL.getName(), data2);
		modelsOfName.put(LogModelType.MIDDLE.getName(), data1);
		modelsOfType.put(LogModelType.SSL.getType(), data2);
		modelsOfType.put(LogModelType.MIDDLE.getType(), data1);
	}

	public static List<String> getmodelByType(String type) {
		return modelsOfType.get(type);
	}

	public static List<String> getmodelByName(String name) {
		return modelsOfType.get(name);
	}
}