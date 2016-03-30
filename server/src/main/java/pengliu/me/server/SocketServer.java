package pengliu.me.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * filename:SocketServer.java
 * author:martin
 * comment:socketserver
 */

/**
 * @author martin
 * 
 */
public class SocketServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket server = null;
		ExecutorService executor = Executors.newCachedThreadPool();
		try {
			// new a socket server
			server = new ServerSocket(39998);
			
			//just accept 10 request for testing.
			for (int counter = 0; counter < 2; counter++) {
                System.out.println("调用server.accept，开始监听");

				// start to listen, this step will be blocked
				Socket request = server.accept();

                System.out.println("监听到连接，开始处理");

				// when getting a request, server will start a thread to handle the request.
				// and then keep going to listen.
				executor.execute(new HandleDataThread(request, counter));
			}

            System.out.println("关闭socket");
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
