package top.mineor.six;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by mineor on 2016/12/21.
 */
public class ChatClient {

    private String clientName;

    public ChatClient(String clientName){
        this.clientName = clientName;
    }

    public void chatintWithOther(){
        try {

            Socket socket = new Socket("127.0.0.1",2016);
            CommonUtils.sendMessageToServer(socket,clientName);
            //String status = CommonUtils.getMessageFromServer(socket);
            //System.out.println(status);
            while(true){
                System.out.println("now");
                Thread.sleep(10000);
                String status = CommonUtils.getMessageFromServer(socket);
                System.out.println(status);
                Thread.sleep(1000);
                if(status.equals("OK"))
                    break;
            }
            BufferedReader sysBuff =new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String message = sysBuff.readLine();
                CommonUtils.sendMessageToServer(socket,clientName+":"+message);
                String serverMessage = CommonUtils.getMessageFromServer(socket);
                System.out.println("从服务器收到消息"+serverMessage);
                if(message.equals("quit"))
                    break;
                CommonUtils.getMessageFromServer(socket);
            }
            while(true){
                String status = CommonUtils.getMessageFromServer(socket);
                if(status.equals("OK"))
                    break;
            }
            System.out.println("此客户端已退出聊天!");
            socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient("麦和").chatintWithOther();
    }





}
