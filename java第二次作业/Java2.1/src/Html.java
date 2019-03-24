public class Html {

    public String Response(){
        String responseMessage = "HTTP/1.1 200 OK";
        String responseBody = "<!DOCTYPE html>"+"<html lang=\"en\">"+"<head>"+"<meta charset=\"UTF-8\">"
                +"<title>Title</title>"+"</head>"+"<body>"+

                "\n" +
                "         <div >Hello!!!! </button></div>\n" +
                "\n" +
                "     </div>"+"</body>"+"</html>";
        return  responseMessage + "\n\n" + responseBody;
    }
}
