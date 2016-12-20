package top.mineor.three;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by mineor on 2016/12/20.
 */
public class MainClass {
    public static void main(String[] args){
        //Scanner input = new Scanner(System.in);
        String urlAddress = "http://www.infoq.com/cn/news/2016/11/Micro-service-java-EE?utm_source=news_about_java&utm_medium=link&utm_campaign=java";
        try{
            URL url = new URL(urlAddress);
            System.out.println("该URL信息如下:");
            printInfo("协议",url.getProtocol());
            printInfo("主机名",url.getHost());
            printInfo("端口号",url.getPort()+"");
            printInfo("文件路径",url.getPath());
            URLConnection connection = url.openConnection();//关于输入输出和乱码问题一直是个人比较头痛的,这里只能读utf-8编码的网页,待完善
            InputStreamReader input = new InputStreamReader(connection.getInputStream(),"utf-8");
            BufferedReader reader = new BufferedReader(input);
            String line = null;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void printInfo(String message,String param){
        System.out.println(message+"-->"+param);
    }
}
