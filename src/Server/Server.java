package Server;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

class ServerThread extends Thread {
	Socket server;
	String[] subject = new String[] { "cat", "school", "sandwich", "friend" };

	int i = 0;
	byte[] b; // �젙�떟臾몄옄�뿴
	String pass = "correct!";
	String fail = "no!";
	String ready = "users login!";
	// string -> byte 蹂��솚
	byte[] transstr = subject[i].getBytes();
	byte[] tpass = pass.getBytes();
	byte[] tfail = fail.getBytes();
    byte[] tready = ready.getBytes();   
	
	public ServerThread(Socket server) {
		this.server = server;
	}
        //濡쒓렇�씤
    void login(){
    	try {
             String name;
             BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream()));
             name = br.readLine();

             Server.info.put(name, 0);
             Server.sl.setUserlist();
             Server.sl.toLog("[" + name + "]" + " �떂�씠 �엯�옣�븯�뀲�뒿�땲�떎.");
    	} catch (IOException e) {
	         e.printStackTrace();
	    } 
    }
    
	public void run() {
        login();

		Image img = null;
		InputStream is = null;
		boolean flag = true;
		//all user login
		while(total_socket.size()<5) {
			if(total_socket.size() == 4) {
				for (int k = 0; k < Server.total_socket.size(); k++) {
					Socket temp = (Socket) Server.total_socket.get(k);

					try {
						temp.getOutputStream().write(tready);
					} catch (Exception e) {
						System.out.println("ready trans fail");
					}
				}
			}	
		}
				
		for (i = 0; i < 4; i++) {
			// string -> byte 蹂��솚
			byte[] transstr = subject[i].getBytes();
			// 二쇱젣 �쟾�떖
			Socket givesub = (Socket) Server.total_socket.get(i);
			try {
				givesub.getOutputStream().write(transstr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 洹몃┝諛쏄린
			try {
				ObjectInputStream ois = new ObjectInputStream(givesub.getInputStream());
				img = (Image) ois.readObject();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			synchronized (Server.total_socket) {
				// �닔�떊�븳 �궗吏� �쟾泥� �겢�씪�씠�뼵�듃�뿉 蹂대궡湲�
				for (int k = 0; k < Server.total_socket.size(); k++) {
					Socket temp = (Socket) Server.total_socket.get(k);

					try {
						ObjectOutputStream oos = new ObjectOutputStream(temp.getOutputStream());
						oos.writeObject(img);
						// temp.getOutputStream().write(b);
					} catch (Exception e) {
						System.out.println("�궗吏� �넚�떊 �삤瑜�");
						Server.total_socket.remove(k);
					}
				}
			}
			// �떟 諛쏆븘�삤湲�
			try {
				is = server.getInputStream();
			} catch (Exception e) {
				System.out.println(e);
			}

			while (flag) {
				b = new byte[256];
				try {
					is.read(b);
					// �젙�떟 鍮꾧탳
					if (transstr.equals(b)) { // �젙�떟�엫
						synchronized (Server.total_socket) {
							for (int k = 0; k < Server.total_socket.size(); k++) {
								Socket temp = (Socket) Server.total_socket.get(k);

								try {
									temp.getOutputStream().write(tpass);
									i++;
									continue;
								} catch (Exception e) {
									System.out.println("�넚�떊 �삤瑜�");
									Server.total_socket.remove(k);
								}
							}
						}
					} else { // �젙�떟�븘�떂
						synchronized (Server.total_socket) {
							for (int k = 0; k < Server.total_socket.size(); k++) {
								Socket temp = (Socket) Server.total_socket.get(k);

								try {
									temp.getOutputStream().write(tfail);
								} catch (Exception e) {
									System.out.println("�넚�떊 �삤瑜�");
									Server.total_socket.remove(k);
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println("�젙�떟 �닔�떊 �삤瑜�");
					e.printStackTrace();
					flag = false;
				}
			}
		}
		
	}
	
}

public class Server {
	static ArrayList total_socket = new ArrayList();
    static Server_log sl = new Server_log();
    static HashMap<String, Integer> info = new HashMap<String, Integer>();
        
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss;
		ss = new ServerSocket(8888);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    sl.setVisible(true);
                    sl.toLog("[Server]: �꽌踰꾧� �떆�옉�릺�뿀�뒿�땲�떎.");
                }
            });
		while (true) {
			Socket server = ss.accept(); // accept()硫붿냼�뱶�뒗 (1) + (2)踰� 湲곕뒫 �몮�떎 �닔�뻾			
			total_socket.add(server);
			
			new ServerThread(server).start();
		}
	}
}
