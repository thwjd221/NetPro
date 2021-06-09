package Client;

import java.awt.Point;
import java.io.*;
import java.net.Socket;
import java.util.Vector;

import Server.User_info;

class ClientThread extends Thread{
	Socket client;
    String myname;
	Room room;
	//BufferedReader br;
	
	public ClientThread(Socket client) {
		this.client = client;
	}

    void login(){
        Login lg = new Login();
        lg.setVisible(true);     

        while(true){
            if(!lg.isDisplayable()){    //아이디 입력 성공, 로그인 창 꺼짐
                myname = lg.name();
                try {
                    PrintStream ps = new PrintStream(client.getOutputStream());
                    ps.println(myname); //서버한테 name 보내줌
                    ps.flush();
                    room = new Room(client, myname);
                    room.setVisible(true);
                } catch (IOException ex) {
        
                }
                break;
            }
        }
    }

	@SuppressWarnings("deprecation")
	public void run() {
        login();
		boolean flag = false;
		String str = null;
		BufferedReader br = null;
		
		room.setDraw(false); //채팅 금지
		room.setChat(false);
		
		for(int i = 0; i < 4; i++) {
			
			/* 2.
			 * 현재 그리는 유저 이름 수신
			 * */		
	        try {
	        	br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        	room.Draw_panel.clear();
	        	str = br.readLine();
				//in = client.getInputStream();
				//byte[] b = new byte[256];
				//in.read(b);
				//str = new String(b);
	        	
				if(myname.equals(str)) {
					flag = true;
				}
				else {
					flag = false;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        room.Append_Room_chat("[" + str + "]" + " 님이 그림을 그립니다.");
	        
	        
			/* 3.
			 * 주제 수신
			 * */
			if(flag) {
	        	room.setDraw(true);
	            try {
	            	br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		        	str = br.readLine();
	            	//java.lang.NullPointerException 오류 생김...
	    			//byte[] b = new byte[256];
	    			//in.read(b);
	    			//str = new String(b);
	    			room.subject(str);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        }
			/* 4.
			 * 그림 수신
			 * */
			else{
				try {
					ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
					Vector<Point> draw = (Vector<Point>) ois.readObject();
					if(draw == null) {
						//room.Draw_panel.clear();
						System.out.println("사진 수신 오류");
					}
					room.Draw_panel.recvDraw(draw);
					room.Append_Room_chat("그림 수신 완료");
					room.setChat(true);
				} catch(IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/* 5.
			 * 정답 수신
			 * */
	        try {
	        	
	        	//in = client.getInputStream();
				//byte[] b = new byte[256];
				while((str = br.readLine()) != null) {
					room.Append_Room_chat(str);
					if(str.equals("correct!")) {
						break;
					}
					//if(in.read() > 0 && str != "correct!" && str != "no!") 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
		}
	}
}

public class Client {
	User_info myinfo;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {			
			Socket socket;
			socket = new Socket("localhost",8888);	//소켓 서버 접속
                        
			ClientThread t1 = new ClientThread(socket);
			t1.start();
		}
		catch( IOException e) {
			e.printStackTrace();	//예외
		}
	}

}
