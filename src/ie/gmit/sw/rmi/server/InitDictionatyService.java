package ie.gmit.sw.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import ie.gmit.sw.rmi.client.DictionaryService;

public class InitDictionatyService {
    private static final  Logger logger = Logger.getLogger(InitDictionatyService.class.getName());


    private static boolean isRegistered = false;
    private static DictionaryService service;

    public InitDictionatyService() {
        if (!isRegistered){
            service = new DictionaryServiceImpl();
            try {
                final DictionaryService stub = (DictionaryService) UnicastRemoteObject.exportObject(service, 0);
                Registry registry = LocateRegistry.createRegistry(8050);
                registry.rebind("DictionaryService", stub);
                 isRegistered =false;
                 logger.info("DictionaryService bound");
            } catch (RemoteException e) {
                logger.info("Exception InitDictionatyService " + e.getMessage());
            }


        }

    }



}
