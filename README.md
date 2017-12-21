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


