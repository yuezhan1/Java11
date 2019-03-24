import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    private static int port = 8888;
    private static Socket accept;
    private static ServerSocket socket;
    private static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        socket = new ServerSocket(port);
        System.out.println("服务器开启，等待连接....");
        while (true){
            accept = socket.accept();
            InputStreamReader r = new InputStreamReader(accept.getInputStream());
            System.out.println("浏览器请求成功!");
            BufferedReader br = new BufferedReader(r);
            String readLine = br.readLine();

            //打印请求消息                                                                     
            String filePath="src";
            int i=0;
            while(readLine != null && !readLine.equals("")){
                System.out.println(readLine);
                if (i==0){
                    String[] split = readLine.split(" ");
                    if (split[1].endsWith("html")) {
                        filePath += split[1];
                    }
                }
                i++;
                readLine=br.readLine();
            }

            System.out.println(filePath);
            writeHtml(filePath);
        }
    }

    public static void writeHtml(String filePath) throws Exception{
        if (!"src/Html1.html".equals(filePath)){
            filePath="src/Html1.html";
        }
        FileInputStream fis = new FileInputStream(filePath);
        int len=0;
        byte[] b = new byte[1024];
        StringBuilder sb = new StringBuilder();
        //拼装http响应的数据格式                                                                  
        sb.append("http/1.1 200 ok").append("\n\n");
        while ((len=fis.read(b))!=-1){
            sb.append(new String(b,0,len));
        }
        bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}                                                                                        