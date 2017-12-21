package ie.gmit.sw.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

public class InitDictionaryServiceClient {
   private static final Logger logger = Logger.getLogger(InitDictionaryServiceClient.class.getName());

    private DictionaryService service;

    public InitDictionaryServiceClient() {
        try {
//            Registry registry = LocateRegistry.getRegistry(HOST, 9345);
            Registry registry = LocateRegistry.getRegistry(null, 8050);

            service = (DictionaryService) registry.lookup("DictionaryService");
            logger.info("InitDictionaryServiceClient init");
        } catch (Exception e) {
            logger.info("InitDictionaryServiceClient exception " + e.getMessage());
        }

    }

    public DictionaryService getService() {
        return service;
    }
}
