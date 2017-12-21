<h1>Project-for-Distributed-Systems-2017</h1>
<h3><i>An Asynchronous RMI Dictionary Service</i></h3>
<h4>Distributed Systems Module<br>
Student - Vaida Abelkyte<br>
Year - 4</h4>
<hr/>

<h3><i>Project Overview</h3>
<p><h6>
I was required to use the Servlet/JSP and Java RMI frameworks to develop a remote, asynchronous dictionary lookup service.
A JSP page should provide users with the ability to specify a string which will be checked against the dictionary.
The HTML form information should be dispatched to a servlet that adds the client request to an in-queue and then returns a job ID to the 
web client. The web client should poll the web server periodically (every 10 seconds) and query if the request has been processed.
Client requests in the inQueue should be periodically removed and processed (every 10 seconds)
</p></h6>

<p><h6>The processing of a client request will require a RMI method invocation to a remote object which implements an interface called 
DictionaryService. The remote object which implements DictionaryService should check if the string received exists in the dictionary, 
and return the dictionary definition of the string if it does exist in the dictionary, or “String not found” if it does not exist in 
the dictionary. Once the result of the dictionary lookup has been computed by the remote object, the returned response should be added 
to the outQueue on the Tomcat server and returned to the original web client when they next poll the server. 
The following diagram depicts the overall system architecture:
</p></h6>

![12 2](https://user-images.githubusercontent.com/15648433/34254524-70ba7aaa-e644-11e7-8ce0-d0f7d9767f86.png)

<h3><i>Achieved Requirements</h3>
<p>
  1. A web client request is placed in a message queue to await processing<br>
  2. Each request is allocated a job number<br>
  3. The job number is added to an inQueue (a Map) along with the request string<br>
  4. The servlet handler returns the job number to the client which in turn should poll the server every 10 seconds for a response.<br> 
  5. When a response is received with a completed task, the result of the dictionary lookup is displayed in the browser.<br>
  6.  An interface called DictionaryService exposes a remote method with the following signature:<br>
  <b><i>public String lookup(String s) throws RemoteException;</i></b><br>
   where s is the string to lookup in the dictionary, and the String returned is either the dictionary word of s or the text “String not found”.<br>
  7.  In the DictionaryServiceImpl, before looking up the query string in the dictionary the thread is put to sleep for a time, i.e. Thread.sleep(1000), to slow the service down and simulate a real asynchronous service.

  </p>
  
  <h3>Solution</h3>
  
  <p>
  I created interface<br>
  <i><b> DictionaryService extends Remote </b></i><br>
A remote object is an instance of a class that implements a remote interface.<br>
  A remote interface extends the interface <i><b>java.rmi.Remote</b></i> and declares a set of remote methods.<br>
  Each remote method must declare <i><b>java.rmi.RemoteException</i></b> (or a superclass ofRemoteException) in its throws clause, in addition to any application-specific exceptions.<br>

The package <b>ie.gmit.sw.rmi</b> contains two folders client and server. Client is implementation of remote <b>‘RMI’</b> and Server is implementation of a remote method call.<br>
For the implementation of the project I use asynchronous servlet :<br>
<i><b>@WebServlet(name = "async", urlPatterns = "/", asyncSupported = true, loadOnStartup = 1)</b></i><br>

On client side <b>(index.jsp)</b> I used AJAX. After the page has been loaded, Ajax is used to avoid page reloads after user clicks and so on.
  </p>
  <h5>References</h5>
<h6>
 <a href="https://www.javaworld.com/article/2077995/java-concurrency/java-concurrency-asynchronous-processing-support-in-servlet-3-0.html">Asynchronous processing support in Servlet 3.0</a>  <br>
<a href="http://awaxman11.github.io/blog/2013/07/21/checking-out-js/"> "How jQuery and AJAX Actually Work" </a> <br>
<a href="https://www.w3schools.com/PhP/php_ajax_intro.asp "> AJAX Introduction</a>

</h6>
  
  



