package top.mineor.six;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mineor on 2016/12/21.
 */
public class CommonUtils {



    public static String receiveMessage(Socket socket){
        try {
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 输入读入一字符串
            String result = bufferedReader.readLine();
            //System.out.println(result);
           // bufferedReader.close();
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void sendMessage(Socket socket,String message){
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
