package com.liaojl.log.udplogsend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class SendLog
{
  private static DatagramSocket ds;
  private static String ip = "";
  private static String path = "";
  private static String url = "";
  private int port = 0;
  private static int thread = 1;
  private static int sleep = 100;
  
  public static void main(String[] args)
    throws IOException
  {
    init(args);
    List<?> modes = getModes();
  }
  
  private static void init(String[] args) {}
  
  private static List<String> getModes()
    throws IOException
  {
    List<String> lists = new ArrayList();
    InputStream is = SendLog.class
      .getResourceAsStream("../../../logModel.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    for (;;)
    {
      String str = br.readLine();
      if (str == null) {
        break;
      }
      lists.add(str);
    }
    br.close();
    return lists;
  }
  
  private void sendData(String data)
    throws Exception
  {
    byte[] buf = data.getBytes();
    DatagramPacket dp = new DatagramPacket(buf, buf.length, 
      InetAddress.getByName(url), this.port);
    

    ds.send(dp);
    

    ds.close();
  }
}