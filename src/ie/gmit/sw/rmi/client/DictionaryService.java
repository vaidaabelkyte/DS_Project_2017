package ie.gmit.sw.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictionaryService extends Remote {
    public String lookup(String s) throws RemoteException;
}
