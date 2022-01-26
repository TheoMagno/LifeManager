package LifeManager.WebServer.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ALARM")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarm_id;
   
    private int type;
    private int value;
    

    public Alarm(){

    }

    public Alarm( int type, int value){
        this.type = type;
        this.value = value;
       
    }

   

    @Column(name = "value", nullable = false)
    public int getValue() {
        return value;
    }
    public void setvalue(int value) {
        this.value = value;
    }

    @Column(name = "type", nullable = false)
    public int getState() {
        return type;
    }
    public void setstate(int type) {
        this.type = type;
    }

    
}
