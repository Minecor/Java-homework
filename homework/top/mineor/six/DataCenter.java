package top.mineor.six;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by mineor on 2016/12/21.
 */
public class DataCenter {

    private static Map<String,Socket> clientList = new HashMap<>(); //维持连接的客户端

    private static LinkedBlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(); //消息队列

    public static void putMessage(String message){
        try {
            messageQueue.put(message);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getMessage(){
        try {
            return messageQueue.take();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ""; //如果消息异常，则返回空消息
    }

    public static void addNewClient(String name,Socket socket){
        clientList.put(name,socket);
    }

    public static Socket getSocket(String name){
        if(clientList.containsKey(name))
            return clientList.get(name);
        else
            return null;
    }

    public static boolean isUserExist(String name){
        return clientList.containsKey(name);
    }

    public static Map<String,Socket> getClientList(){
        return clientList;
    }

}
