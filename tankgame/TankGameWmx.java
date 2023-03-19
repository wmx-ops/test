package tankgame;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author 汪明熙
 * @version 1.0
 */
public class TankGameWmx extends JFrame {
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        TankGameWmx tankGameWmx = new TankGameWmx();

    }
    public TankGameWmx() {
        System.out.println("请输入选择 1： 新游戏 2： 继续上局");
        String key = scanner.next();
        mp = new MyPanel(key);
        //将mp 放入到Thread,并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//将面板放入到框中
        this.setSize(1300,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(mp);//让JFrame 监听mp的键盘事件
        //关闭窗口处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }

}
