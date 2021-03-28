package chattingapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Client extends JFrame implements ActionListener {
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Boolean typing;
    
    Client(){
        
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        add(p1);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/arrow.png.jpg")); //get image from directory
        Image i2= i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); // to set the size of image
        ImageIcon i3=new ImageIcon(i2);
        JLabel l1= new JLabel(i3);
        l1.setBounds(5,5,30,30);
        p1.add(l1);
        
        l1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
                
            }
        
    });
        
        ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/Shizuka.jpg"));
        Image i5= i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel l2= new JLabel(i6);
        l2.setBounds(40,5,60,60);
        p1.add(l2);
        
         
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/telephone.png"));
        Image i8= i7.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel l5= new JLabel(i9);
        l5.setBounds(250,10,40,40);
        p1.add(l5);
        
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/image.jpg"));
        Image i11= i10.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel l6= new JLabel(i12);
        l6.setBounds(300,10,40,40);
        p1.add(l6);
        
        /*ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/3icon.png.jpg"));
        Image i14= i13.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel l7= new JLabel(i15);
        l7.setBounds(350,20,13,25);
        p1.add(l7);*/
        
        JLabel l3=new JLabel("SHIZUKA");
        l3.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110,10,100,20);
        p1.add(l3);
        
        
        JLabel l4=new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110,35,70,20);
        p1.add(l4);
        
         Timer t = new Timer(1, new ActionListener(){
            
            public void actionPerformed(ActionEvent ae){
      
                if(!typing){
                    l4.setText("Active Now");
                }
            }
            
        });
        
        t.setInitialDelay(2000);
        
        t1=new JTextField();
        t1.setBounds(5,500,310,35);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        add(t1);
        
         t1.addKeyListener(new KeyAdapter(){
        public void keyPressed(KeyEvent ke){
            l4.setText("typing...");
            
            t.stop();
            
            typing = true;
        }
        
        public void keyReleased(KeyEvent ke){
            typing = false;
            
            if(!t.isRunning()){
                t.start();
            }
        }
        
        });
        
        b1=new JButton("Send");
        b1.setBounds(320, 500, 70, 33);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        b1.addActionListener(this);
        add(b1);
        
        a1=new JTextArea();
        a1.setBounds(5,72,390,420);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,15));
        a1.setEditable(false); //so that textarea couldn't be edited
        a1.setLineWrap(true);  //to get the string in next line
        a1.setWrapStyleWord(true);// to get the sti=ring in next line
        add(a1);
        
        setLayout(null);
        setSize(400,550);
        setLocation(800,100);
        setUndecorated(true);
        setVisible(true);
    }
    
    public static void main(String args[]){
      
        new Client().setVisible(true);
        
        String msginput = "";
        
        try {
            
            s = new Socket("127.0.0.1", 6010);
            din = new DataInputStream(s.getInputStream()); //data comes through socket from client
           dout = new DataOutputStream(s.getOutputStream()); //data that we send to client
           
           msginput = din.readUTF();
            a1.setText(a1.getText()+"\n"+msginput);
            
        }catch(Exception e){
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        try{
            String s1=t1.getText();
        a1.setText(a1.getText()+"\n\t\t\t"+s1);  //in this we are appending .To add data of textarea previous data + current data
        dout.writeUTF(s1);
        t1.setText("");
        
        }catch(Exception e){
            
        }
    }
}
