package com.liaojl.test.rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyService extends Remote {

    String say(String someOne) throws RemoteException;

}