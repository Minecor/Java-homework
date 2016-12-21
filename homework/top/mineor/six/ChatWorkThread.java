package top.mineor.six;

import java.net.Socket;

/**
 * Created by mineor on 2016/12/21.
 */
public class ChatWorkThread implements Runnable{
    private Socket socket;
    public ChatWorkThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        while(true){
            String line = CommonUtils.getMessageFromClient(socket);
            try {
                Thread.sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            String[] lineContent = line.split(":");
            if(lineContent.length != 2)
                CommonUtils.sendMessageToClient(socket,"消息格式错误!");
            else{
                DataCenter.putMessage(line);
                System.out.println("已接收到客户端"+lineContent[0]+"发送的消息:"+lineContent[1]);
            }

        }
    }
}
