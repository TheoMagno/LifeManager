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
@Table(name="SENSOR")
public class Sensor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensor_id;

    //Patient Id
    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;
    private int state;
    private int type;

    public Sensor(){

    }

    public Sensor(int state, int type){
        this.state = state;
        this.type = type;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient(){
        return this.patient;
    }
    public Long getId(){
        return this.sensor_id;
  
    }

    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }
    public void settype(int type) {
        this.type = type;
    }

    @Column(name = "state", nullable = false)
    public int getState() {
        return state;
    }
    public void setstate(int state) {
        this.state = state;
    }
}