import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSocket {

    public static void main(String[] args) throws UnknownHostException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Scanner scanner = new Scanner(System.in);

            try{
                //establish socket connection to server
                socket = new Socket(host.getHostName(), 9876);
                //write to socket using ObjectOutputStream
                oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Java client");
                System.out.print("Digite uma mensagem:");
                String msgSend = scanner.next();
                System.out.println("A mensagem enviada eh: "+msgSend);
                oos.writeObject(msgSend);
                System.out.println("Aguardando a resposta");
                //read the server response message
                ois = new ObjectInputStream(socket.getInputStream());
                String msgRec = (String) ois.readObject();
                System.out.println("Resposta recebida:" + msgRec);
                //close resources
                System.out.println("Desconectado");
                ois.close();
                oos.close();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

