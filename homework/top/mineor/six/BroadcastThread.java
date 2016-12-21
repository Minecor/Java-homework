package top.mineor.six;

import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by mineor on 2016/12/21.
 */
public class BroadcastThread implements Runnable{
    @Override
    public void run() {
        while(true){
            String message = DataCenter.getMessage();
            Map<String,Socket> client = DataCenter.getClientList();
            Iterator<String> users = client.keySet().iterator();
            while(users.hasNext()){
                String name = users.next();
                Socket socket = client.get(name);
                CommonUtils.sendMessageToClient(socket,message);
                System.out.println("已向"+name+"发送消息:"+message);
            }
        }
    }
}
