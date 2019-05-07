package server_application;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class StreamRunnable implements Runnable{

    private Socket socket;
    private final String statusExit = "Disconnecting";
    private ShootRunnable runnable_impar;
    private ShootRunnable runnable_par;

    public StreamRunnable(Socket socket){
       this.socket = socket;
    }

    private void shootEmail(List<Email> list){

        runnable_impar = new ShootRunnable(list, "IMPAR");
        runnable_par = new ShootRunnable(list, "PAR");
    }

    @Override
    public void run(){
        try {

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String input = null;

            while (true){
                if (socket.isConnected()){
                    input = (String) inputStream.readObject();
                    System.out.println("Input Message: " + input);
                    //outputStream.writeObject(input);

                    if (ProjectProtocolServer.processLine(input) != null){
                        if (ProjectProtocolServer.processLine(input).equalsIgnoreCase("exit")) break;

                        else if (ProjectProtocolServer.processLine(input).equalsIgnoreCase("shoot")){
                            outputStream.writeObject("request_emails");

                            System.out.println("Recebendo a lista..");
                            LinkedList<Email> list = (LinkedList<Email>) inputStream.readObject();

                            System.out.println("Lista recebida. Elemento1: " + list.get(0).getAdress());
                            System.out.println("Lista recebida. Elemento2: " + list.get(1).getAdress());

                            System.out.println("Eviando Emails");

                            shootEmail(list);
                            runnable_impar.getInsideThread().join();
                            runnable_par.getInsideThread().join();
                            Thread.sleep(1000);

                            System.out.println("emails enviados. Verificar na respectivas contas");
                            outputStream.writeObject("Sent Emails");
                        }else if (ProjectProtocolServer.processLine(input).equalsIgnoreCase("true"))
                            outputStream.writeObject("client_connected");
                    }
                } else {
                    break;
                }
            }

            outputStream.close();
            inputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
