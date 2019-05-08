package server_application;


public abstract class ProjectProtocolServer {

    public static String processLine(String text){
        switch (text){
            case "connection":
                return "true";

            case "shoot":
                return "shoot";

            case "exit":
                return "exit";

            case "subject":
                return "subject";

            case "body_email":
                return "email";

        }

        return null;
    }

}
