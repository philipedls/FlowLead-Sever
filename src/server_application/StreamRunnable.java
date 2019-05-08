package server_application;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class StreamRunnable implements Runnable {

    private Socket socket;
    private ShootRunnable runnable_impar;
    private ShootRunnable runnable_par;
    private String subject;
    private String body_email;

    public StreamRunnable(Socket socket) {
        this.socket = socket;
    }

    private void shootEmail(List<String> list, String subject, String body_email) {

        runnable_impar = new ShootRunnable(list, "IMPAR", subject, body_email);
        runnable_par = new ShootRunnable(list, "PAR", subject, body_email);
    }

    @Override
    public void run() {
        try {

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String input = null;

            while (true) {
                if (socket.isConnected()) {
                    input = (String) inputStream.readObject();
                    System.out.println("Input Message: " + input);
                    //outputStream.writeObject(input);

                    if (ProjectProtocolServer.processLine(input).equalsIgnoreCase("exit")){
                        outputStream.writeObject("exit");
                        break;

                    }else if (ProjectProtocolServer.processLine(input).equalsIgnoreCase("shoot")) {
                        outputStream.writeObject("request_emails");

                        System.out.println("Recebendo a lista...");
                        List<String> list = (LinkedList<String>) inputStream.readObject();

                        System.out.println("Lista recebida. Elemento1: " + list.get(0));
                        System.out.println("Lista recebida. Elemento2: " + list.get(1));

                        System.out.println("Eviando Emails");

                        shootEmail(list, getSubject(), getBody_email());
                        runnable_impar.getInsideThread().join();
                        runnable_par.getInsideThread().join();
                        //Thread.sleep(500);

                        System.out.println("emails enviados. Verificar na respectivas contas");
                        outputStream.writeObject("Sent Emails");
                    } else if (ProjectProtocolServer.processLine(input).equalsIgnoreCase("true"))
                        outputStream.writeObject("client_connected");

                    else if (input.equalsIgnoreCase("subject")) {
                        outputStream.writeObject("request_subject");
                        setSubject((String) inputStream.readObject());
                        System.out.println("Assunto do EMAIL: " + getSubject());
                        outputStream.writeObject("ok");

                    } else if (input.equalsIgnoreCase("body_email")) {
                        outputStream.writeObject("request_body");
                        setBody_email((String) inputStream.readObject());
                        outputStream.writeObject("ok");
                    }
                }
            }
            outputStream.close();
            inputStream.close();
            socket.close();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody_email() {
        return body_email;
    }

    public void setBody_email(String body_email) {
        this.body_email = body_email;
    }
}