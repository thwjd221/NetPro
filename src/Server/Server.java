package Server;

import java.util.Vector;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

class ServerThread extends Thread {
	Socket server;
	String[] subject = new String[] { "cat", "school", "sandwich", "friend" };
	
	int i = 0;
	//byte[] b; // 정답문자열
	String answer;
	String pass = "correct!";
	String fail = "no!";
	String ready = "users login!";
	
	// string -> byte 변환
	//byte[] transstr = subject[i].getBytes();
	//byte[] tpass = pass.getBytes();
	//byte[] tfail = fail.getBytes();
	//byte[] tready = ready.getBytes();  
	
	public ServerThread(Socket server) {
		this.server = server;
	}
        //로그인
    void login(){
    	try {
             String name;
             BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream()));
             name = br.readLine();

             Server.info.put(server, new User_info(name, 0));
             Server.sl.setUserlist();
             Server.sl.toLog("[" + name + "]" + " 님이 입장하셨습니다.");
    	} catch (IOException e) {
	         e.printStackTrace();
    	}
    }
    
	public void run() {
        login();
       
        //OutputStream out = server.getOutputStream(); 
        //Vector<Point> draw_panel;
		InputStream is = null;
		
		boolean flag = false;
		
		/* 1.
		 * 4명 접속 기다림
		 * */
		while(Server.total_socket.size() != 4);

		for (i = 0; i < 4; i++) {
			flag = true;
			// string -> byte 변환
			//byte[] transstr = subject[i].getBytes();
			
			Socket givesub = (Socket) Server.total_socket.get(i);
			String draw_user = Server.info.get(givesub).name;
			String str;
			answer = subject[i];
			//byte[] tdraw_user = draw_user.getBytes();
			
			
			/* 2.
			 * 현재 그리는 유저 이름 송신
			 * */
			Server.sl.toLog("[" + draw_user + "]" + " 님이 그림을 그립니다.");
			for (int k = 0; k < Server.total_socket.size(); k++) {
				Socket temp = (Socket) Server.total_socket.get(k);
				try {
					PrintStream ps = new PrintStream(temp.getOutputStream());
	                ps.println(draw_user);
	                ps.flush();
					//OutputStream os = temp.getOutputStream();
					//os.write(tdraw_user);
					//os.flush();
				} catch (Exception e) {
					System.out.println("ready trans fail");
				}
			}
			
			
			/* 3.
			 * 주제 송신
			 * */
			Server.sl.toLog("[" + draw_user + "]" + " 님의 주제: " + subject[i]);
			try {
            	PrintStream ps = new PrintStream(givesub.getOutputStream());
				ps.println(subject[i]);
                ps.flush();
				//OutputStream os = givesub.getOutputStream();
				//os.write(transstr);
				//os.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			/* 
			 * 그림 수신
			 * */
			try {
				ObjectInputStream ois = new ObjectInputStream(givesub.getInputStream());
				Vector<Point> draw_panel = (Vector<Point>) ois.readObject();
				if(draw_panel== null) {
					Server.sl.toLog(" 님이 퇴장하셨습니다.");
				}
				Server.sl.toLog(" 님이 ");

				/* 4.
				 * 그림 송신
				 * */
				synchronized (Server.total_socket) {
					// 수신한 사진 전체 클라이언트에 보내기
					for (int k = 0; k < Server.total_socket.size(); k++) {
						Socket temp = (Socket) Server.total_socket.get(k);
						try {
							ObjectOutputStream oos = new ObjectOutputStream(temp.getOutputStream());
							oos.writeObject(draw_panel);
							oos.flush();
							oos.reset();
						} catch (Exception e1) {
							System.out.println("사진 송신 오류");
							Server.total_socket.remove(k);
						}
					}
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			while (flag) {
				//b = new byte[256];
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream()));
					str = br.readLine();
					//is.read(b);
					// 정답 비교
					if (str.equals(answer)) { // 정답임
						synchronized (Server.total_socket) {
							for (int k = 0; k < Server.total_socket.size(); k++) {
								Socket temp = (Socket) Server.total_socket.get(k);
								
								try {
									//ObjectOutputStream oos = new ObjectOutputStream(temp.getOutputStream());
				                	//oos.writeObject(pass);
				                	//oos.flush();
									PrintStream ps = new PrintStream(temp.getOutputStream());
									ps.println(pass);
									ps.flush();
								} catch (Exception e) {
									System.out.println("송신 오류");
									Server.total_socket.remove(k);
								}
							}
						}
						flag = false;	//현재 turn 종료
					} else { // 정답아님
						synchronized (Server.total_socket) {
							for (int k = 0; k < Server.total_socket.size(); k++) {
								Socket temp = (Socket) Server.total_socket.get(k);

								try {
									//ObjectOutputStream oos = new ObjectOutputStream(temp.getOutputStream());
				                	//oos.writeObject(fail);
				                	//oos.flush();
									PrintStream ps = new PrintStream(temp.getOutputStream());
									ps.println(pass);
									ps.flush();
								} catch (Exception e) {
									System.out.println("송신 오류");
									Server.total_socket.remove(k);
									
									//name 얻어오기
									String name;
									name = Server.info.get(temp).name;
									Server.info.remove(temp);	//user_info 지우기
									Server.sl.setUserlist();
									Server.sl.toLog("[" + name + "]" + " 님이 퇴장하셨습니다.");
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println("정답 수신 오류");
					e.printStackTrace();
					flag = false;
				}
			}	//while
		}	//for		
	}	//run
}

public class Server {
	static ArrayList total_socket = new ArrayList();
    static Server_log sl = new Server_log();
    //static HashMap<String, Integer> info = new HashMap<String, Integer>();
    static HashMap<Socket, User_info> info = new HashMap<Socket, User_info>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss;
		ss = new ServerSocket(8888);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    sl.setVisible(true);
                    sl.toLog("[Server]: 서버가 시작되었습니다.");
                }
            });
		while (true) {
			Socket server = ss.accept(); // accept()메소드는 (1) + (2)번 기능 둘다 수행			
			total_socket.add(server);
			ServerThread thread = new ServerThread(server);
			
			//ServerThread thread = new ServerThread(server);
			thread.start();
			
            if(thread.isInterrupted() == true) {
            	total_socket.remove(server);
            	//name 얻어오기
				String name;
				name = Server.info.get(server).name;
				Server.info.remove(server);	//user_info 지우기
				Server.sl.setUserlist();
				Server.sl.toLog("[" + name + "]" + " 님이 퇴장하셨습니다.");
            }
		}
	}
}
