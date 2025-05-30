/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDateTime;
/**
 *
 * @author rohin
 */
public class Log {
    private int id;
    private String action;
    private String actor;
    private LocalDateTime timestamp;
    
    public Log(int id, String action, String actor, LocalDateTime timestamp){
       this.id=id;
      this.action=action;
      this.actor=actor;
      this.timestamp=timestamp;
    }
    public int getId(){
        return id;
    }
    public String getAction(){
        return action;
    } 
    public String getActor(){
        return actor;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setAction(String action){
        this.action=action;
    }
    public void setActor(String actor){
        this.actor=actor;
    }
    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp=timestamp;
    }
}
