import java.io.IOException;
import java.net.Socket;
	//변경사항저장 테스트
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {							
			Socket socket = new Socket("",1234);	//소켓 서버 접속
			
			ReceiveThread t1 = new ReceiveThread(socket);
			SendThread t2 = new SendThread(socket);
			t1.start();
			t2.start();
		}
		catch( IOException e) {
			e.printStackTrace();	//예외
		}
	}

}
