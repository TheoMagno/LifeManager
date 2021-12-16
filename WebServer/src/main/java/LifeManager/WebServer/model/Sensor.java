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
    @ManyToOne()
    @JoinColumn(name = "pacient_id")
    private Pacient patient;
    private short state;
    private short type;

    public Sensor(){

    }

    public Sensor(Pacient patient, short state, short type){
        this.patient=patient;
        this.state = state;
        this.type = type;
    }

    public Pacient getPatient(){
        return this.patient;
    }
    public Long getId(){
        return this.sensor_id;
  
    }

    @Column(name = "type", nullable = false)
    public short getType() {
        return type;
    }
    public void settype(Short type) {
        this.type = type;
    }

    @Column(name = "state", nullable = false)
    public short getState() {
        return state;
    }
    public void setstate(Short state) {
        this.state = state;
    }
}