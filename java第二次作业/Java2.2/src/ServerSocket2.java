import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket2 {

    static String responseMessage = "HTTP/1.1 200 OK";
    static String responseBody="";

    public static void Connection() {
        try {
            ServerSocket server = new ServerSocket(8086);
            System.out.println("服务器开启，等待连接....");
            Socket socket = server.accept();
            InputStreamReader r = new InputStreamReader(socket.getInputStream());
            System.out.println("浏览器请求成功!");
            BufferedReader br = new BufferedReader(r);
            String readLine = br.readLine();
            System.out.println(readLine);
            String[] requestMessage = readLine.split(" ");
            String queryUrl = requestMessage[1];
            System.out.println(queryUrl);
            Class clazz = Class.forName(queryUrl.replace("/", ""));
            //获取本类中所有方法
            Method[] methods = clazz.getDeclaredMethods();
            //通过类对象的getConstructor()或getDeclaredConstructor()方法获得构造器（Constructor）对象并调用其newInstance()方法创建对象，适用于无参和有参构造方法。

            responseBody = methods[0].invoke(clazz.getDeclaredConstructor().newInstance()).toString();

            String response = responseMessage + "\n\n" + responseBody;
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(response.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}








