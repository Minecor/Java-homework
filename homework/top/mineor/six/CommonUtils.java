package top.mineor.six;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mineor on 2016/12/21.
 */
public class CommonUtils {

    public static String getMessageFromClient(Socket socket){
        try {
            /** 获取客户端传来的信息 */
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 获取从客户端读入的字符串
            String result = bufferedReader.readLine();
            //bufferedReader.close();
            System.out.println("Client say : " + result);
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void sendMessageToClient(Socket socket,String message){
        try{
            /** 发送服务端准备传输的 */
            // 由Socket对象得到输出流，并构造PrintWriter对象
            PrintWriter printWriter =new PrintWriter(socket.getOutputStream());
            printWriter.println(message);
            printWriter.flush();
            /** 关闭Socket*/
            //printWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getMessageFromServer(Socket socket){
        try {
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 输入读入一字符串
            String result = bufferedReader.readLine();
            System.out.println("Server say : " + result);
           // bufferedReader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void sendMessageToServer(Socket socket,String message){
        try{
            PrintWriter printWriter =new PrintWriter(socket.getOutputStream(),true);
            // 将输入读入的字符串输出到Server
            printWriter.println(message);
            // 刷新输出流，使Server马上收到该字符串
            printWriter.flush();
            /** 关闭Socket*/
           // printWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
