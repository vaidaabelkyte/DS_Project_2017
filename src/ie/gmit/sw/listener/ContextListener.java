package ie.gmit.sw.listener;

import ie.gmit.sw.entity.Entity;
import ie.gmit.sw.rmi.client.DictionaryService;
import ie.gmit.sw.rmi.client.InitDictionaryServiceClient;
import ie.gmit.sw.rmi.server.InitDictionatyService;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@WebListener
public class ContextListener implements ServletContextListener {
    Logger logger = Logger.getLogger(ContextListener.class.getName());

    public void contextDestroyed(final ServletContextEvent sce) {
    }

    public void contextInitialized(final ServletContextEvent sce) {

        Thread t = new Thread(new Runnable() {

            public void run() {
                final Queue<AsyncContext> contexts = new ConcurrentLinkedQueue<AsyncContext>();
                sce.getServletContext().setAttribute("contexts", contexts);

                final Queue<Entity> inQueue = new ConcurrentLinkedQueue<Entity>();
                final Queue<Entity> outQueue = new ConcurrentLinkedQueue<Entity>();

                sce.getServletContext().setAttribute("inQueue", inQueue);

                Executor inExecutor = Executors.newCachedThreadPool();
                final Executor outExecutor = Executors.newCachedThreadPool();
                final Executor userExecutor = Executors.newCachedThreadPool();
                logger.info("ContextListener init");


                new InitDictionatyService();
                final DictionaryService service = new InitDictionaryServiceClient().getService();


                while (true) {
                    if (!inQueue.isEmpty()) {
                        final Entity entity = inQueue.poll();
                        inExecutor.execute(new Runnable() {
                            public void run() {
                                try {
                                    
                                    final String lookup = service.lookup(entity.getWorld());
                                    entity.setWorld(lookup);
                                    outQueue.add(entity);
                                    logger.info("ADD ENTITY TO OUT");
                                } catch (RemoteException e) {
                                     logger.info(e.getMessage());
                                }


                                if (!outQueue.isEmpty())
                                    outExecutor.execute(new Runnable() {

                                        public void run() {
                                            while (!contexts.isEmpty()) {
                                                final AsyncContext aCtx = contexts.poll();
                                                userExecutor.execute(new Runnable() {

                                                    public void run() {
                                                        try {
                                                            //send response
                                                            ServletResponse response = aCtx.getResponse();
                                                            response.setContentType("text/xml");
                                                            response.getWriter().write(messageAsXml(entity));
                                                            aCtx.complete();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }


                                                    }

                                                    private String messageAsXml(final Entity message) {
                                                        StringBuffer sb = new StringBuffer();
                                                        sb.append("<entity>")
                                                                .append("<id>")
                                                                .append(message.getId())
                                                                .append("</id>")
                                                                .append("<text>")
                                                                .append(message.getWorld())
                                                                .append("</text>")
                                                                .append("</entity>");
                                                        return sb.toString();
                                                    }
                                                });
                                            }
                                        }
                                    });
                            }
                        });

                    }
                             //sleep 5 sec
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        logger.info(e.getMessage());
                    }

                }
            }
        });

        t.start();
    }
}
