<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servlet 3.0 example</title>
        <script src="http://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>

        <script>
            $(document).ready(function () {
                function getData() {
                    $.ajax({
                        url: "async",
                        type: "GET",
                        dataType: "xml",
                        context: document.body,
                        success: function (data) {
                            var id = $(data).find('id').text();
                            var text = $(data).find('text').text();
                            $('#chat_msgs').html(id + " - " + text + "\n");
                            getData();
                        },
                        timeout: 10000,
                        complete: function () {
                            $('#sendMsg').show();
                            $('#msg').hide();
                        }
                    });
                }

                $("#sendMsg").click(function (event) {
                    $.post("async", $("#msgForm").serialize());
                    $('#query').val('');
                    $('#msg').show();
                    $('#sendMsg').hide();
                    $('#chat_msgs').html('');
                });
                getData();
            });
        </script>
    </head>
    <body>
        <h1>Dictionary Service</h1>
       
        <h4>Project for Distributed Systems - 2017</h4>
        <h5>By Vaida Abelkyte - G00328909</h5>
        
        <div id="form">
            <form id="msgForm" name="msgForm">
                <td>Your query:</td>
                <td><input type="text" id="query" name="query"/></td>
            </form>
            <br/>
            <input type="submit" id="sendMsg" name="sendMsg" value="Send message"/>
        </div>
        <h2 id="msg" style="display:none;">Waiting for response ...</h2>
        <h2 id="response"> Request ID: <span id="chat_msgs" name="chat_msgs"></span></h2>

    </body>
</html>