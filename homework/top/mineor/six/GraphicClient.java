package top.mineor.six;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by mineor on 2016/12/26.
 * 图形化客户端
 */
public class GraphicClient extends JFrame {
    private String clientName;
    private Socket socket;
    private JTextField inputField;
    private JTextArea recordArea;

    public GraphicClient(String name) throws Exception{
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        this.clientName = name;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width/4,screenSize.height/2);
        this.setLocation(screenSize.width/8*3,screenSize.height/4);
        this.setTitle("聊天室客户端");
        this.setVisible(true);
        addComponent();
        initChatSetting();
    }

    public void initChatSetting(){
        try {
            Socket socket = new Socket("127.0.0.1",2016);
            this.socket = socket;
            CommonUtils.sendMessage(socket,clientName);
            new MonitorThread(socket).start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addComponent(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        recordArea = new JTextArea(30,40);
        recordArea.setLineWrap(true);
        recordArea.setWrapStyleWord(true);
        panel.add(recordArea);
        panel.add(recordArea);
        this.add(panel,BorderLayout.CENTER);
        inputField = new JTextField(screenSize.width/60);
        JButton enterButton = new JButton("发送");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputField.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"发送消息不能为空!");
                else{
                    CommonUtils.sendMessage(socket,clientName+":"+inputField.getText());
                    recordArea.append(inputField.getText()+"\n");
                    inputField.setText("");
                }
            }
        });
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if(e.getKeyCode() == 10){
                    CommonUtils.sendMessage(socket,clientName+":"+inputField.getText());
                    recordArea.append(inputField.getText()+"\n");
                    inputField.setText("");
                }
            }
        });
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(enterButton);
        this.add(inputPanel,BorderLayout.SOUTH);
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
                recordArea.append(message+"\n");
            }
        }
    }


    public static void main(String[] args) {

        Scanner input  = new Scanner(System.in);
        System.out.println("请输入名字:");
        String name = input.nextLine();

        try {
            new GraphicClient(name);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
