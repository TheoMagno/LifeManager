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

    //Patient Id
    @ManyToOne()
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
    private short state;
    private int value;

    public Alarm(){

    }

    public Alarm(Sensor sensor, short state, short value){
        this.sensor = sensor;
        this.state = state;
        this.value = value;
    }

    public Sensor getSensor(){
        return this.sensor;
    }
    public Long getId(){
        return this.alarm_id;
  
    }

    @Column(name = "value", nullable = false)
    public int getValue() {
        return value;
    }
    public void setvalue(int value) {
        this.value = value;
    }

    @Column(name = "state", nullable = false)
    public short getState() {
        return state;
    }
    public void setstate(Short state) {
        this.state = state;
    }
}
