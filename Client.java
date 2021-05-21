import java.io.IOException;
import java.net.Socket;
	//test
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {							
			Socket socket = new Socket("",1234);	//家南 辑滚 立加
			
			ReceiveThread t1 = new ReceiveThread(socket);
			SendThread t2 = new SendThread(socket);
			t1.start();
			t2.start();
		}
		catch( IOException e) {
			e.printStackTrace();	//抗寇
		}
	}

}
