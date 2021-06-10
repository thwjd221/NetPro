package Server;

import java.util.Vector;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
    
    void remove(Socket temp) {
    	String name;
		name = Server.info.get(temp).name;
		
    	Server.total_socket.remove(temp);

		Server.info.remove(temp);	//user_info 지우기
		Server.sl.setUserlist();
		Server.sl.toLog("[" + name + "]" + " 님이 퇴장하셨습니다.");
    }
    
    void sendAll(String msg, String err) {
    	//synchronized (Server.total_socket) {
			for (int k = 0; k < Server.total_socket.size(); k++) {
				Socket temp = (Socket) Server.total_socket.get(k);
				
				try {
					//ObjectOutputStream oos = new ObjectOutputStream(temp.getOutputStream());
	            	//oos.writeObject(pass);
	            	//oos.flush();
					PrintWriter pw = new PrintWriter(temp.getOutputStream());
	            	//PrintStream ps = new PrintStream(temp.getOutputStream());
					pw.println(msg);
	                pw.flush();
				} catch (Exception e) {
					System.out.println(err);
					remove(temp);
				}
			}
    	//}
    }
    
    boolean recv(boolean flag) {
    	BufferedReader br = null;

		PrintWriter pw = null;
		try {
			pw = new PrintWriter(server.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		try {
			br = new BufferedReader(new InputStreamReader(server.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String str;
		while (flag) {
			//b = new byte[256];
			try {
				Server.sl.toLog("뭐야");
				while((str = br.readLine()) != null) {
					String msg = "[" + Server.info.get(server).name + "]: " + str;
					Server.sl.toLog(msg);

					sendAll(msg, "송신 오류");
					//is.read(b);
					
					// 정답 비교
					if (str.equals(answer)) { // 정답임
						sendAll(pass, "송신 오류");
						flag = false;	//현재 turn 종료
						break;
					} else { // 정답아님
						sendAll(fail, "송신 오류");
					}
	        	}
			} catch (Exception e) {
				System.out.println("정답 수신 오류");
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
    }
    
	public void run() {
        login();
       
        //OutputStream out = server.getOutputStream(); 
        //Vector<Point> draw_panel;
		//InputStream is = null;
		
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
					PrintWriter pw = new PrintWriter(temp.getOutputStream());
					//PrintStream ps = new PrintStream(temp.getOutputStream());
	                pw.println(draw_user);
	                pw.flush();
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
				PrintWriter pw = new PrintWriter(givesub.getOutputStream());
				pw.println(subject[i]);
				pw.flush();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			//PrintStream ps = new PrintStream(givesub.getOutputStream());

			//OutputStream os = givesub.getOutputStream();
			//os.write(transstr);
			//os.flush();
			
			
			
			try {
				/* 
				 * 그림 수신
				 * */
				ObjectInputStream ois = new ObjectInputStream(givesub.getInputStream());
				Vector<Point> draw_panel = (Vector<Point>) ois.readObject();

				/* 4.
				 * 그림 송신
				 * */
				//synchronized (Server.total_socket) {
					// 수신한 사진 전체 클라이언트에 보내기
					for (int k = 0; k < Server.total_socket.size(); k++) {
						if(k == i)
							continue;
						
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
				//}
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/* 5. 
			 * 
			 */
			Server.sl.toLog("[" + draw_user + "]" + " 님의 그림 수신 완료");
			flag = recv(flag);

		}			
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
			Socket server = ss.accept();		
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
