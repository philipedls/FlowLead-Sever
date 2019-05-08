package server_application;

import java.util.List;

public class ShootRunnable implements Runnable{

    private List<String> listEmails;
    private Thread insideThread;
    private String type;
    // A SENHA DO SEU EMAIL
    private final String password = "DIGITE A SENHA DO SEU EMAIL QUE SERA USADO COMO REMETENTE!";
    private String assunto;
    private String corpo_email;

    public ShootRunnable(List<String> listEmails, String type, String assunto, String corpo_email){
        this.listEmails = listEmails;
        this.type = type;
        this.assunto = assunto;
        this.corpo_email = corpo_email;
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
                        MyJavaMail.sendMail(listEmails.get(index), password, assunto, corpo_email);
                    else if (index%2 == 0){
                        MyJavaMail.sendMail(listEmails.get(index), password, assunto, corpo_email);
                    }
                }

                break;
            case "IMPAR":
                for (int index=1; index < listEmails.size(); index++){
                    if (index%2 != 0){
                        MyJavaMail.sendMail(listEmails.get(index), password, assunto, corpo_email);
                    }
                }
        }
    }
}
