package ie.gmit.sw.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.entity.Entity;

import java.io.IOException;
import java.util.Queue;

@WebServlet(name = "async", urlPatterns = "/", asyncSupported = true, loadOnStartup = 1)
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext aCtx = req.startAsync(req, resp);
        aCtx.setTimeout(15000); //5 sec timeout
        ServletContext servletContext = req.getServletContext();
        ((Queue<AsyncContext>)servletContext.getAttribute("contexts")).add(aCtx);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext aCtx = req.startAsync(req, resp);
        ServletContext servletContext = req.getServletContext();
        String message = req.getParameter("query");
        Queue<Entity> msgQueue = (Queue<Entity>) servletContext.getAttribute("inQueue");
        msgQueue.add(new Entity(message));
        aCtx.complete();
    }

}
