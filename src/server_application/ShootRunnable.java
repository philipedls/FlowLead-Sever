package server_application;

import java.util.List;

public class ShootRunnable implements Runnable{

    private List<Email> listEmails;
    private Thread insideThread;
    private String type;
    private final String password = "superzon561421";

    public ShootRunnable(List<Email> listEmails, String type){
        this.listEmails = listEmails;
        this.type = type;
        insideThread = new Thread(this);
        insideThread.start();
    }

    public Thread getInsideThread(){
        return this.insideThread;
    }
    @Override
    public void run() {
        String string = type.toUpperCase();
        switch (string){
            case "PAR":
                for (int index=0; index < listEmails.size(); index++){
                    if (index == 0)
                        MyJavaMail.sendMail(listEmails.get(index), password);
                    else if (index%2 == 0){
                        MyJavaMail.sendMail(listEmails.get(index), password);
                    }
                }

                break;
            case "IMPAR":
                for (int index=1; index < listEmails.size(); index++){
                    if (index%2 != 0){
                        MyJavaMail.sendMail(listEmails.get(index), password);
                    }
                }
        }
    }
}
