
package Client;

import javax.swing.*;

public class Login extends JFrame {
    String ID;
    Room room;
    
    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_screen = new JPanel();
        Panel_name = new JPanel();
        Label_name = new JLabel();
        TextField_name = new JTextField();
        Button_login = new JButton();
        Label_screen = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("로그인");
        setAutoRequestFocus(false);
        setIconImage(Base.Img("https://blog.kakaocdn.net/dn/qkIfs/btq5OSqdpf2/POri2X86VkiHrHDS2kRFo0/img.png"));
        setResizable(false);
        setSize(new java.awt.Dimension(600, 450));

        Panel_screen.setMaximumSize(null);
        Panel_screen.setPreferredSize(new java.awt.Dimension(600, 450));
        Panel_screen.setLayout(null);

        Panel_name.setBackground(new java.awt.Color(255, 255, 255));
        Panel_name.setAlignmentX(0.0F);
        Panel_name.setAlignmentY(0.0F);
        Panel_name.setMinimumSize(new java.awt.Dimension(200, 100));
        Panel_name.setPreferredSize(new java.awt.Dimension(200, 100));
        Panel_name.setLayout(null);

        Label_name.setFont(Label_name.getFont().deriveFont(Label_name.getFont().getSize()-1f));
        Label_name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Label_name.setText("닉네임");
        //Label_name.setToolTipText("");
        Panel_name.add(Label_name);
        Label_name.setBounds(10, 10, 40, 20);

        TextField_name.setFont(TextField_name.getFont().deriveFont(TextField_name.getFont().getSize()-1f));
        TextField_name.setNextFocusableComponent(Button_login);
        TextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        Panel_name.add(TextField_name);
        TextField_name.setBounds(60, 10, 110, 24);

        Button_login.setFont(Button_login.getFont().deriveFont(Button_login.getFont().getSize()-1f));
        Button_login.setText("로그인");
        Button_login.setPreferredSize(new java.awt.Dimension(70, 21));
        Button_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        Panel_name.add(Button_login);
        Button_login.setBounds(90, 40, 80, 21);

        Panel_screen.add(Panel_name);
        Panel_name.setBounds(200, 300, 190, 70);

        Label_screen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_screen.setIcon(new ImageIcon(Base.Img("https://blog.kakaocdn.net/dn/bbxCGe/btq5Q6gUzU6/E9pkCraFe75pW2VniXd801/img.jpg")));
        Label_screen.setAlignmentX(0.5F);
        Panel_screen.add(Label_screen);
        Label_screen.setBounds(0, 0, 600, 450);

       GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Panel_screen,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_screen,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("login");

        pack();
        setLocationRelativeTo(null);
    }

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {
        ID = TextField_name.getText();
        if(ID.isEmpty()){
        	JOptionPane.showMessageDialog(null, new JLabel("닉네임을 입력해주세요.", JLabel.CENTER), "메세지", JOptionPane.WARNING_MESSAGE);
        }
        else{ //Room 접속
        	TextField_name.setText("");
        	dispose();
        }
    }
    
    String name(){
        return ID;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    JButton Button_login;
    JLabel Label_name;
    JLabel Label_screen;
    JPanel Panel_name;
    JPanel Panel_screen;
    JTextField TextField_name;
    // End of variables declaration//GEN-END:variables
}
