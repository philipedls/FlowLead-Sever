package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main (String[] args){

        /*

        try {

            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host, 7979);
            System.out.println("Client ONLINE: " + socket.toString());

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            String reciveUser = null;
            Scanner scanner = new Scanner(System.in);

            while (true){
                reciveUser = scanner.nextLine();

                objectOutputStream.writeObject(reciveUser);
                System.out.println("Mesagem do Servidor: " + objectInputStream.readObject());

                if (reciveUser.equalsIgnoreCase("shoot")) break;

            }

            List<Email> emails = new LinkedList<>();
            emails.add(new Email("philipe.luna@dce.ufpb.br"));
            emails.add(new Email("philipesoares@cc.ci.ufpb.br"));
            emails.add(new Email("andre.nobregamaster@gmail.com"));
            emails.add(new Email("andre.maximumpowerdepoimento@gmail.com"));
            emails.add(new Email("carlos.souzaprofile@gmail.com"));


            System.out.println("Email: " + emails.get(0).getAdress());

            System.out.println("Enviando a lista...");
            objectOutputStream.writeObject(emails);

            Thread.sleep(2000);
            reciveUser = (String) objectInputStream.readObject();
            System.out.println(reciveUser);
            System.out.println("Lista enviada!!... Cliente Disconnetiong...");

            objectInputStream.close();
            objectOutputStream.close();
            scanner.close();
            socket.close();

        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

         */

    }
}
