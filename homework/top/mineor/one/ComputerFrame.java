package top.mineor.one;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mineor on 2016/12/20.
 */
public class ComputerFrame extends JFrame {
    public ComputerFrame() throws Exception{
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width/6,screenSize.height/8);
        this.setLocation(screenSize.width/8*3,screenSize.height/16*7);
        this.setTitle("期末测试");
        addcomponent();
        this.setVisible(true);
    }

    public void addcomponent(){
        JPanel up = new JPanel();
        JPanel down = new JPanel();
        this.setLayout(new BorderLayout());
        this.add(up,BorderLayout.CENTER);
        this.add(down,BorderLayout.SOUTH);
        JButton questionButton = new JButton("获取题目");
        JButton submitButton = new JButton("确认答案");
        JLabel addLabel = new JLabel("+");
        JLabel equalLabel = new JLabel("=");
        final JTextField num1TextField = new JTextField(3);
        final JTextField num2TextField = new JTextField(3);
        final JTextField resultTextFiled = new JTextField(4);
        questionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Question question = Teacher.getQuestion();
                num1TextField.setText(question.getNum1()+"");
                num2TextField.setText(question.getNum2()+"");
                num1TextField.setEditable(false);
                num2TextField.setEditable(false);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = resultTextFiled.getText();
                Question question = Teacher.question;
                System.out.println(result);
                System.out.println(question.getNum1());
                System.out.println(question.getNum2());
                if(result.isEmpty()){
                    JOptionPane.showMessageDialog(null,"答案不能为空!");
                }
                else{
                    if(result.equals(question.getResult()+""))
                        JOptionPane.showMessageDialog(null,"回答正确!");
                    else
                        JOptionPane.showMessageDialog(null,"回答错误!");
                }
            }
        });
        up.add(questionButton);
        up.add(num1TextField);
        up.add(addLabel);
        up.add(num2TextField);
        up.add(equalLabel);
        up.add(resultTextFiled);
        down.add(submitButton);


    }

}
