package server_application;

import java.io.Serializable;

public class Email implements Serializable {

    private String adress;


    public Email(String adress) {
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }
}
