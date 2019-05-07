package server_application;


public class ProjectProtocolServer {

    public static String processLine(String text){
        switch (text){
            case "connection":
                return "true";

            case "shoot":
                return "shoot";

            case "exit":
                return "exit";
        }

        return null;
    }

}
