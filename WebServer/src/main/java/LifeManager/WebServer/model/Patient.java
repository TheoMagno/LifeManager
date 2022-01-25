package LifeManager.WebServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numUtente;
    private String name;
    private int age;
    private char gender;
    private float weight;
    private float height;
    @ElementCollection
    private List<String> medication;
    @ElementCollection
    private List<String> conditions;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name="doctor")
    private Doctor doctor;
    @OneToMany(mappedBy="patient", orphanRemoval = true)
    private List<Sensor> sensors;

    public Patient() {

    }

    public Patient(String name, Long numUtente, int age, char gender, float weight, float height, String email, String password) {
        this.numUtente = numUtente;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.medication = new ArrayList<String>();
        this.conditions = new ArrayList<String>();
        this.sensors = new ArrayList<Sensor>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "numUtente", nullable = false, unique = true)
    public Long getNumUtente() {
        return numUtente;
    }
    public void setNumUtente(Long numUtente) {
        this.numUtente = numUtente;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "weight", nullable = false)
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Column(name = "height", nullable = false)
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    @Column(name = "gender", nullable = false)
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email=email;
    }

    public void addMedication(String medication) {
        this.medication.add(medication);
    }
    public void remMedication(String medication) {
        this.medication.remove(medication);
    }

    public void addCondition(String condition) {
        this.conditions.add(condition);
    }
    public void remCondition(String condition) {
        this.conditions.remove(condition);
    }

    public List<String> getMedication() {
        return medication;
    }
    
    public List<String> getConditions() {
        return conditions;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public String toString() {
        return "Patient [numUtente='" + getNumUtente() + "'" +
            ", name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            ", gender='" + getGender() + "'" +
            ", weight='" + getWeight() + "'" +
            ", height='" + getHeight() + "'" +
            ", medication='" + getMedication() + "'" +
            ", conditions='" + getConditions() + "'" +
            ", password='" + getPassword() + "'" +
            ", doctor='" + getDoctor() + "'" +
            "]";
    }
}