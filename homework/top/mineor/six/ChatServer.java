package top.mineor.six;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mineor on 2016/12/21.
 */
public class ChatServer{

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try {
            /** 创建ServerSocket*/
            // 创建一个ServerSocket在端口2016监听客户请求
            ServerSocket serverSocket =new ServerSocket(2016);
            cachedThreadPool.execute(new BroadcastThread());
            while (true) {
                // 侦听并接受到此Socket的连接,请求到来则产生一个Socket对象，并继续执行
                Socket socket = serverSocket.accept();
                String name = CommonUtils.receiveMessage(socket);
                System.out.println(name);
                DataCenter.addNewClient(name,socket);
                CommonUtils.sendMessage(socket,"Hello,"+name+"!");
                cachedThreadPool.execute(new ChatWorkThread(socket));

            }
        }catch (Exception e) {
            System.out.println("Exception:" + e);
        }finally{
//          serverSocket.close();
        }
    }






}
