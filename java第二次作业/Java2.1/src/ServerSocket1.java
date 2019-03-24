import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket1 {

    public void Connection(){
        try {
            Html html=new Html();
            ServerSocket server = new ServerSocket(8085);
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

            String response = html.Response();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(response.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
