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
            String line = CommonUtils.receiveMessage(socket);

            String[] lineContent = line.split(":");
            if(lineContent.length != 2)
                CommonUtils.sendMessage(socket,"消息格式错误!");
            else if(lineContent[1].equals("bye!")){
                DataCenter.putMessage(lineContent[1]+"已退出聊天室!");
                CommonUtils.sendMessage(socket,lineContent[0]+",您已退出聊天室!");
                DataCenter.getClientList().remove(lineContent[0]);
            }
            else{
                DataCenter.putMessage(line);
                System.out.println("已接收到客户端"+lineContent[0]+"发送的消息:"+lineContent[1]);
            }

        }
    }
}
