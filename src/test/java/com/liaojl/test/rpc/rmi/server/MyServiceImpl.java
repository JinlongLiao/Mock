package com.liaojl.test.rpc.rmi.server;

import com.liaojl.test.rpc.rmi.MyService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyServiceImpl extends UnicastRemoteObject implements MyService {

    protected MyServiceImpl() throws RemoteException {
    }

    public String say(String someOne) throws RemoteException {
        return "偷塔我最六\t" + someOne;
    }
}