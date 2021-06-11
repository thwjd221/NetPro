package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Room extends JFrame implements Runnable {
    Socket client;
    String myname;
    ObjectOutputStream oos;
    
	public Room(Socket client, String name, ObjectOutputStream oos) {
    	this.client = client;
    	this.myname = name;
    	this.oos = oos;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     void initComponents() {
        Panel_screen = new JPanel();
        Draw_panel = new Draw();
        Scroll_chat = new JScrollPane();
        TextArea_chat = new JTextArea();
        Layered_mychat = new JLayeredPane();
        TextField_mychat = new JTextField();
        TextField_name = new JTextField();;
        ScrollBar_mychat = new JScrollBar();
        Button_start = new JButton();
        Button_exit = new JButton();
        TextField_subject = new JTextField();
        Label_subject = new JLabel();
        Label_icon = new JLabel();
        Label_screen = new JLabel();
        
        //Prame 설정
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Room");
        setAutoRequestFocus(false);
        setIconImage(Base.Img("https://blog.kakaocdn.net/dn/qkIfs/btq5OSqdpf2/POri2X86VkiHrHDS2kRFo0/img.png"));
        setResizable(false);
        setSize(new Dimension(830, 760));
        
        //Prame 배경 역할
        Panel_screen.setPreferredSize(new Dimension(1020, 760));
        Panel_screen.setLayout(null);
        
        //Draw 위한 Panel
        Draw_panel.setBackground(new Color(255, 255, 255));
        Draw_panel.setPreferredSize(new Dimension(590, 380));
        Draw_panel.setEnabled(false);
        
        //레이아웃 설정
        GroupLayout jPanel1Layout = new GroupLayout(Draw_panel);
        Draw_panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        
        Panel_screen.add(Draw_panel);
        Draw_panel.setBounds(215, 40, 590, 370);
        
        //채팅창 위한 ScrollPanel 설정
        Scroll_chat.setBackground(new Color(255, 255, 255));
        Scroll_chat.setBorder(null);
        Scroll_chat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        //채팅창 설정
        TextArea_chat.setEditable(false);
        TextArea_chat.setColumns(20);
        TextArea_chat.setFont(TextArea_chat.getFont());
        TextArea_chat.setLineWrap(true);
        TextArea_chat.setRows(5);
        TextArea_chat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "채팅창", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("굴림", 0, 10))); // NOI18N
        Scroll_chat.setViewportView(TextArea_chat);
        
        Panel_screen.add(Scroll_chat);
        Scroll_chat.setBounds(215, 435, 590, 220);
        
        //채팅 입력 설정
        TextField_mychat.setBorder(null);
        TextField_mychat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TextField_mychatActionPerformed(evt);
           }
        });
        
        //채팅 입력 스크롤바 설정
        ScrollBar_mychat.setOrientation(JScrollBar.HORIZONTAL);
        ScrollBar_mychat.setModel(TextField_mychat.getHorizontalVisibility());

        Layered_mychat.setLayer(TextField_mychat, JLayeredPane.DEFAULT_LAYER);
        Layered_mychat.setLayer(ScrollBar_mychat, JLayeredPane.DEFAULT_LAYER);
        
        //채팅 입력 레이아웃 설정
        GroupLayout Layered_mychatLayout = new GroupLayout(Layered_mychat);
        Layered_mychat.setLayout(Layered_mychatLayout);
        Layered_mychatLayout.setHorizontalGroup(
            Layered_mychatLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Layered_mychatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layered_mychatLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(TextField_mychat, GroupLayout.Alignment.TRAILING)
                    .addComponent(ScrollBar_mychat, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                .addContainerGap())
        );
        Layered_mychatLayout.setVerticalGroup(
            Layered_mychatLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, Layered_mychatLayout.createSequentialGroup()
                .addComponent(TextField_mychat, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollBar_mychat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        
        Panel_screen.add(Layered_mychat);
        Layered_mychat.setBounds(210, 670, 600, 59);
        
        //그림 전송 버튼 설정
        Button_start.setFont(Button_start.getFont().deriveFont(Button_start.getFont().getSize()+2f));
        Button_start.setText("그림 전송");
        Button_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Button_startActionPerformed(evt);
            }
        });
        Panel_screen.add(Button_start);
        Button_start.setBounds(30, 650, 150, 30);
        Button_start.setEnabled(true);
        
        //종료 버튼 설정
        Button_exit.setFont(Button_exit.getFont().deriveFont(Button_exit.getFont().getSize()+2f));
        Button_exit.setText("종료");
        Button_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Button_exitActionPerformed(evt);
            }
        });
        Panel_screen.add(Button_exit);
        Button_exit.setBounds(30, 700, 150, 30);
        
        //"제시어" TextField 설정
        TextField_subject.setEditable(false);
        TextField_subject.setBackground(new Color(255, 255, 255));
        TextField_subject.setHorizontalAlignment(JTextField.CENTER);
        TextField_subject.setText("제시어");
        TextField_subject.setToolTipText("");
        TextField_subject.setBorder(null);
        TextField_subject.setEnabled(false);
        Panel_screen.add(TextField_subject);
        TextField_subject.setBounds(35, 60, 140, 20);
        
        //User 닉네임 표시하는 TextField 설정
        TextField_name.setEditable(false);
        TextField_name.setBackground(new Color(255, 255, 255));
        TextField_name.setFont(new Font("굴림", 1, 11)); // NOI18N
        TextField_name.setHorizontalAlignment(JTextField.CENTER);
        TextField_name.setText(myname);
        TextField_name.setToolTipText("");
        TextField_name.setBorder(null);
        Panel_screen.add(TextField_name);
        TextField_name.setBounds(35, 40, 140, 20);
        
        //제시어 표시하는 라벨 설정
        Label_subject.setBackground(new Color(255, 255, 255));
        Label_subject.setFont(new Font("굴림", 1, 12)); // NOI18N
        Label_subject.setHorizontalAlignment(SwingConstants.CENTER);
        Label_subject.setText("");
        Panel_screen.add(Label_subject);
        Label_subject.setBounds(35, 80, 140, 210);
        
        //Prame 배경 틀 설정 위한 라벨
        Label_icon.setIcon(new ImageIcon(Base.Img("https://blog.kakaocdn.net/dn/u0VTA/btq6L5OL4KO/IU2eWUt0zn77XZxFCqNYG1/img.png")));
        Panel_screen.add(Label_icon);
        Label_icon.setBounds(0, 0, 830, 760);
        
        //Prame 배경 이미지 설정 위한 라벨
        Label_screen.setIcon(new ImageIcon(Base.Img("https://blog.kakaocdn.net/dn/W2o8k/btq6EUOJN4O/AeihG2gaWPFznKNYcSvpX1/img.jpg"))
        );
        Panel_screen.add(Label_screen);
        Label_screen.setBounds(0, 0, 830, 760);
        
        //레이아웃 설정
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Panel_screen, GroupLayout.PREFERRED_SIZE, 830, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Panel_screen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }
    
    //채팅 입력
    private void TextField_mychatActionPerformed(ActionEvent evt) {
        String message = TextField_mychat.getText();
        try {
			oos.writeObject(message);
			oos.flush();
   		 	oos.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pw = new PrintWriter(client.getOutputStream());
		//pw.println(message);
		//pw.flush();
		TextField_mychat.setText("");
    }
    
    //그림 전송
    private void Button_startActionPerformed(ActionEvent evt) {
    	 try {
    		 //ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			 oos.writeObject(Draw_panel.xy);
    		 oos.flush();
    		 oos.reset();
    		 Append_Room_chat("그림 송신 완료");
    		 setDraw(false);
         } catch (IOException ex) {
             
         }
    }

    //접속 종료, 창 끄기
    private void Button_exitActionPerformed(ActionEvent evt) {
    	try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 System.exit(0);
    }
    
    //채팅창에 message 붙이기
    void Append_Room_chat(String s){
    	TextArea_chat.append(s + "\n");
    }
 	
    //현재 게임 주제 설정
 	void subject(String sub) {
 		Label_subject.setText(sub);
 	}
 	
 	//그림 보낼 수 있게 or 없게 버튼 설정
 	void setDraw(boolean flag) {
 		Button_start.setEnabled(flag);
 		Draw_panel.setEnabled(flag);
 	}
    
 	//채팅할 수 있게 or 없게  설정
 	void setChat(boolean flag) {
 		TextField_mychat.setEnabled(flag);
 	}

    // Variables declaration//GEN-BEGIN:variables
     JButton Button_exit;			//종료 버튼
     JButton Button_start;			//그림 전송 버튼
     JLabel Label_icon;				//prame 배경 틀 
     JLabel Label_screen;			//prame 배경 이미지
     JLabel Label_subject;			//"제시어" 라벨
     JLayeredPane Layered_mychat;	//채팅 입력 레이아웃
     JPanel Panel_screen;			//prame 배경
     JScrollBar ScrollBar_mychat;	//채팅 입력 스크롤바
     JScrollPane Scroll_chat;		//채팅창
     JTextArea TextArea_chat;		//채팅창 표시
     JTextField TextField_mychat;	//채팅 입력
     JTextField TextField_name;		//User 닉네임 표시
     Draw Draw_panel;
     JTextField TextField_subject;
    // End of variables declaration//GEN-END:variables

	@Override
	public void run() {

	}
}