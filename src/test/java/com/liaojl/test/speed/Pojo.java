package com.liaojl.test.speed;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Pojo implements Serializable {
    String a = "111";
    String bd = "ccc";
    String ccasd = "e323223";
    String de = "dada";

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getCcasd() {
        return ccasd;
    }

    public void setCcasd(String ccasd) {
        this.ccasd = ccasd;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }
}
	
