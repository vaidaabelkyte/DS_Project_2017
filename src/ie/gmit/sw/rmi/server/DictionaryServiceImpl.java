package ie.gmit.sw.rmi.server;

import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ie.gmit.sw.rmi.client.DictionaryService;


public class DictionaryServiceImpl  implements DictionaryService {
    
    private static final  List<String> dictionaryList = new CopyOnWriteArrayList<String>();
    static {
       dictionaryList.add("java");
       dictionaryList.add("jar");
       dictionaryList.add("lamp");
       dictionaryList.add("table");
       dictionaryList.add("pay");
       dictionaryList.add("client");
       dictionaryList.add("lunch");
       dictionaryList.add("server");
       dictionaryList.add("sad");
       dictionaryList.add("bill");
       dictionaryList.add("comb");
       dictionaryList.add("dictionary");
       dictionaryList.add("cucumber");
       dictionaryList.add("apple");
       dictionaryList.add("cherry");
       dictionaryList.add("cup");
       dictionaryList.add("bottle");
       dictionaryList.add("pen");
       dictionaryList.add("pencil");
       dictionaryList.add("marker");
       dictionaryList.add("friend");
    }

   
    public String lookup(String s) throws RemoteException {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (dictionaryList.contains(s.toLowerCase())){
            return s;
        }
        return "String not found";
    }
}
