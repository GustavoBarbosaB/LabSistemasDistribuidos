package socket;

import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class DataServer {
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        Scanner scanner = new Scanner(System.in);

            System.out.println("Java server");
            System.out.println("Aguardando conexão");
            System.out.println("Esperando mensagem");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Mensagem recebida: " + message);
            System.out.print("Digite uma resposta: ");
            String response = scanner.next();
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject(response);
            //close resources
            ois.close();
            oos.close();
            socket.close();
            System.out.println("Mensagem enviada");

        System.out.println("Desconectado!!");
        server.close();
    }

}
