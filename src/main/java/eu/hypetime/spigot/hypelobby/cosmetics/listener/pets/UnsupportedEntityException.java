package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

/*
    Created by Andre
    At 14:10 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class UnsupportedEntityException extends Exception{

     public UnsupportedEntityException(String message) {
          super(message);
     }

     public UnsupportedEntityException(Throwable cause) {
          super(cause);
     }

     public UnsupportedEntityException(String message, Throwable cause) {
          super(message, cause);
     }

}
