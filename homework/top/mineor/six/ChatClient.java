package top.mineor.six;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by mineor on 2016/12/21.
 * 命令行客户端
 */
public class ChatClient {

    private String clientName;

    public ChatClient(String clientName){
        this.clientName = clientName;
    }

    public void chatintWithOther(){
        try {

            Socket socket = new Socket("127.0.0.1",2016);
            CommonUtils.sendMessage(socket,clientName);
            String status = CommonUtils.receiveMessage(socket);
            System.out.println(status);
            Thread.sleep(1000);
            new MonitorThread(socket).start();
            BufferedReader sysBuff =new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String message = sysBuff.readLine();
                CommonUtils.sendMessage(socket,clientName + ":" + message);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    class MonitorThread extends Thread{
        private Socket socket;
        public MonitorThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            while(true){
                String message = CommonUtils.receiveMessage(socket);
                System.out.println(message);
            }
        }
    }

    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        System.out.println("请输入名字:");
        String name = input.nextLine();
        new ChatClient(name).chatintWithOther();
    }

}
