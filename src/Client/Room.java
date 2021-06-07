package Client;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.*;

import Server.Server;

public class Room extends javax.swing.JFrame {
    Socket client;
    String myname;
    
    public void Append_Room_chat(String s){
        TextArea_chat.append(s);
    }
    
	public Room(Socket client, String name) {
    	this.client = client;
    	this.myname = name;
        initComponents();
    }
	
	public void subject(String sub) {
		Label_subject.setText(sub);
	}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_screen = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Scroll_chat = new javax.swing.JScrollPane();
        TextArea_chat = new javax.swing.JTextArea();
        Layered_mychat = new javax.swing.JLayeredPane();
        TextField_mychat = new javax.swing.JTextField();
        ScrollBar_mychat = new javax.swing.JScrollBar();
        Button_start = new javax.swing.JButton();
        Button_exit = new javax.swing.JButton();
        TextField_subject = new javax.swing.JTextField();
        Label_subject = new javax.swing.JLabel();
        Label_icon = new javax.swing.JLabel();
        Label_screen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Room");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(51, 51, 51));
        setIconImage(Base.Img("https://blog.kakaocdn.net/dn/qkIfs/btq5OSqdpf2/POri2X86VkiHrHDS2kRFo0/img.png"));
        setMaximumSize(null);
        setMinimumSize(null);
        setResizable(false);
        setSize(new java.awt.Dimension(830, 760));

        Panel_screen.setPreferredSize(new java.awt.Dimension(1020, 760));
        Panel_screen.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(590, 380));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        Panel_screen.add(jPanel1);
        jPanel1.setBounds(215, 40, 590, 370);

        Scroll_chat.setBackground(new java.awt.Color(255, 255, 255));
        Scroll_chat.setBorder(null);
        Scroll_chat.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        TextArea_chat.setEditable(false);
        TextArea_chat.setColumns(20);
        TextArea_chat.setFont(TextArea_chat.getFont());
        TextArea_chat.setLineWrap(true);
        TextArea_chat.setRows(5);
        TextArea_chat.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "채팅창", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("굴림", 0, 10))); // NOI18N
        Scroll_chat.setViewportView(TextArea_chat);

        Panel_screen.add(Scroll_chat);
        Scroll_chat.setBounds(215, 435, 590, 220);

        TextField_mychat.setBorder(null);
        TextField_mychat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_mychatActionPerformed(evt);
            }
        });

        ScrollBar_mychat.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        ScrollBar_mychat.setModel(TextField_mychat.getHorizontalVisibility());

        Layered_mychat.setLayer(TextField_mychat, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Layered_mychat.setLayer(ScrollBar_mychat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout Layered_mychatLayout = new javax.swing.GroupLayout(Layered_mychat);
        Layered_mychat.setLayout(Layered_mychatLayout);
        Layered_mychatLayout.setHorizontalGroup(
            Layered_mychatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Layered_mychatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layered_mychatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextField_mychat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ScrollBar_mychat, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                .addContainerGap())
        );
        Layered_mychatLayout.setVerticalGroup(
            Layered_mychatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Layered_mychatLayout.createSequentialGroup()
                .addComponent(TextField_mychat, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollBar_mychat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Panel_screen.add(Layered_mychat);
        Layered_mychat.setBounds(210, 670, 600, 59);

        Button_start.setFont(Button_start.getFont().deriveFont(Button_start.getFont().getSize()+2f));
        Button_start.setText("게임 시작");
        Button_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_startActionPerformed(evt);
            }
        });
        Panel_screen.add(Button_start);
        Button_start.setBounds(30, 650, 150, 30);

        Button_exit.setFont(Button_exit.getFont().deriveFont(Button_exit.getFont().getSize()+2f));
        Button_exit.setText("종료");
        Button_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_exitActionPerformed(evt);
            }
        });
        Panel_screen.add(Button_exit);
        Button_exit.setBounds(30, 700, 150, 30);

        TextField_subject.setEditable(false);
        TextField_subject.setBackground(new java.awt.Color(255, 255, 255));
        TextField_subject.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextField_subject.setText("제시어");
        TextField_subject.setToolTipText("");
        TextField_subject.setBorder(null);
        TextField_subject.setEnabled(false);
        Panel_screen.add(TextField_subject);
        TextField_subject.setBounds(35, 40, 140, 20);
        
        Label_subject.setBackground(new java.awt.Color(255, 255, 255));
        Label_subject.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N
        Label_subject.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_subject.setText("");
        Panel_screen.add(Label_subject);
        Label_subject.setBounds(35, 60, 140, 230);

        Label_icon.setIcon(new ImageIcon(Base.Img("https://blog.kakaocdn.net/dn/u0VTA/btq6L5OL4KO/IU2eWUt0zn77XZxFCqNYG1/img.png")));
        Panel_screen.add(Label_icon);
        Label_icon.setBounds(0, 0, 830, 760);

        Label_screen.setIcon(new ImageIcon(Base.Img("https://blog.kakaocdn.net/dn/W2o8k/btq6EUOJN4O/AeihG2gaWPFznKNYcSvpX1/img.jpg"))
        );
        Panel_screen.add(Label_screen);
        Label_screen.setBounds(0, 0, 830, 760);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_screen, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_screen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    //채팅 입력
    private void TextField_mychatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_mychatActionPerformed
        // TODO add your handling code here:
        String message = TextField_mychat.getText();
        TextField_mychat.setText("");
        try {
            OutputStream out = client.getOutputStream();
            out.write(message.getBytes());
            out.flush();
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_TextField_mychatActionPerformed
    
    //게임 시작
    private void Button_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_startActionPerformed

        //서버에서 게임 시작 
    }//GEN-LAST:event_Button_startActionPerformed

    //접속 종료, 창 끄기
    private void Button_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_exitActionPerformed
        System.exit(0);
        
    }//GEN-LAST:event_Button_exitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_exit;
    private javax.swing.JButton Button_start;
    private javax.swing.JLabel Label_icon;
    private javax.swing.JLabel Label_screen;
    private javax.swing.JLabel Label_subject;
    private javax.swing.JLayeredPane Layered_mychat;
    private javax.swing.JPanel Panel_screen;
    private javax.swing.JScrollBar ScrollBar_mychat;
    private javax.swing.JScrollPane Scroll_chat;
    private javax.swing.JTextArea TextArea_chat;
    private javax.swing.JTextField TextField_mychat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField TextField_subject;
    // End of variables declaration//GEN-END:variables
}
