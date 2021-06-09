
package Server;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.Vector;

import javax.swing.*;

import Client.Base;

public class Server_log extends JFrame {
    public Server_log() {
        initComponents();
    }
    
    void toLog(String s){
        log.append(s + '\n');
    }
    
    void setUserlist() {
    	Vector userlist = new Vector();
    	Collection<User_info> obj;
    	if(Server.info != null) {
    		obj = Server.info.values();
    		obj.forEach(value -> userlist.add(value.name));
    	}
    	List_user.setListData(userlist);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	setIconImage(Base.Img("https://blog.kakaocdn.net/dn/qkIfs/btq5OSqdpf2/POri2X86VkiHrHDS2kRFo0/img.png"));
        Dialog_user = new JDialog();
        Panel_screen = new JPanel();
        Scroll_user = new JScrollPane();
        List_user = new JList<>();
        Button_user_exit = new JButton();
        Scroll_log = new JScrollPane();
        log = new JTextArea();
        Button_exit = new JButton();
        Button_user = new JButton();
        Button_clear = new JButton();
        
        Dialog_user.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_user.setTitle("유저");
        Dialog_user.setAlwaysOnTop(true);
        Dialog_user.setResizable(false);
        Dialog_user.setSize(new java.awt.Dimension(280, 210));
        Dialog_user.setType(java.awt.Window.Type.POPUP);

        Panel_screen.setLayout(null);

        Scroll_user.setFocusable(false);
        
        setUserlist();

        Scroll_user.setViewportView(List_user);

        Panel_screen.add(Scroll_user);
        Scroll_user.setBounds(30, 20, 210, 110);

        Button_user_exit.setText("닫기");
        Button_user_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_user_exitActionPerformed(evt);
            }
        });
        Panel_screen.add(Button_user_exit);
        Button_user_exit.setBounds(100, 140, 80, 23);

        GroupLayout Dialog_userLayout = new GroupLayout(Dialog_user.getContentPane());
        Dialog_user.getContentPane().setLayout(Dialog_userLayout);
        Dialog_userLayout.setHorizontalGroup(
            Dialog_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_screen, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
        );
        Dialog_userLayout.setVerticalGroup(
            Dialog_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_screen, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server log");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 300));

        Scroll_log.setBackground(new java.awt.Color(255, 255, 255));

        log.setEditable(false);
        log.setColumns(20);
        log.setFont(log.getFont());
        log.setLineWrap(true);
        log.setRows(5);
        log.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Scroll_log.setViewportView(log);

        Button_exit.setFont(Button_exit.getFont());
        Button_exit.setText("종료");
        Button_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_exitActionPerformed(evt);
            }
        });

        Button_user.setFont(Button_user.getFont());
        Button_user.setText("유저");
        Button_user.setToolTipText("");
        Button_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_userActionPerformed(evt);
            }
        });

        Button_clear.setFont(Button_clear.getFont());
        Button_clear.setText("clear");
        Button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_clearActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Scroll_log)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Button_user)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_clear, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 560, Short.MAX_VALUE)
                        .addComponent(Button_exit, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Scroll_log, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_exit)
                    .addComponent(Button_user)
                    .addComponent(Button_clear))
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    //서버 종료
    private void Button_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_exitActionPerformed
        System.exit(0);
    }
    
    //유저 정보
    private void Button_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_userActionPerformed
        Dialog_user.setLocationRelativeTo(Panel_screen);
        Dialog_user.setVisible(true);
    }
    
    //로그 clear
    private void Button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_clearActionPerformed
        log.setText("");
    }
    
    //유저 정보 창 끄기
    private void Button_user_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_user_exitActionPerformed
        Dialog_user.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    JButton Button_exit;
    JButton Button_user;
    JButton Button_clear;
    JButton Button_user_exit;
    JDialog Dialog_user;
    JList List_user;
    JPanel Panel_screen;
    JScrollPane Scroll_log;
    JScrollPane Scroll_user;
    JTextArea log;
    // End of variables declaration//GEN-END:variables

}

