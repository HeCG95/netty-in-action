package nia.chapter1;

import org.openjdk.jol.vm.VM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kerr.
 *
 * Listing 1.1 Blocking I/O example
 */
public class BlockingIoExample {

    public static void main(String[] args) throws Exception {

        // nc -lp 7777
        // nc -v 127.0.0.1 7777
        BlockingIoExample demo = new BlockingIoExample();
        demo.serve(7777);

    }

    /**
     * Listing 1.1 Blocking I/O example
     * */
    public void serve(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);

        System.out.println("serverSocket.accept before ...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("serverSocket.accept after ...");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        // java.net.SocketOutputStream@2ff4f00f
        System.out.println("clientSocket.getOutputStream(): "+clientSocket.getOutputStream());
        System.out.println("The memory address is " + VM.current().addressOf(clientSocket.getOutputStream()));

        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        System.out.println("prepare while loop ..."+System.currentTimeMillis());

        // BufferedReader.readLine 最终是 SocketInputStream.read - 阻塞方法
        while ((request = in.readLine()) != null) {
            System.out.println("enter while loop ..."+System.currentTimeMillis());
            if ("Done".equals(request)) {
                break;
            }
            response = processRequest(request);
            out.println(response);
            System.out.println("leave while loop ..."+System.currentTimeMillis());
        }
        System.out.println("finished while loop ..."+System.currentTimeMillis());
    }

    private String processRequest(String request){
        System.out.println("request: "+request);
        return "Processed";
    }
}
