package LifeManager.WebServer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Pacient")
public class Pacient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numUtente;
    private String first_name;
    private String last_name;
    private int age;
    private char gender;
    private float weight;
    private float height;
    @ElementCollection
    private List<String> medication;
    @ElementCollection
    private List<String> conditions;
    private String password;

    public Pacient() {

    }

    public Pacient(String first_name, String last_name, Long numUtente, int age, char gender, float weight, float height, String email, String password) {
        this.numUtente = numUtente;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.medication = new ArrayList<String>();
        this.conditions = new ArrayList<String>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "numUtente", nullable = false)
    public Long getNumUtente() {
        return numUtente;
    }
    public void setMedicalID(Long numUtente) {
        this.numUtente = numUtente;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String lastName) {
        this.last_name = lastName;
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

    @Override
    public String toString() {
        return "Pacient [numUtente='" + getNumUtente() + "'" +
            ", first_name='" + getFirstName() + "'" +
            ", last_name='" + getLastName() + "'" +
            ", age='" + getAge() + "'" +
            ", gender='" + getGender() + "'" +
            ", weight='" + getWeight() + "'" +
            ", height='" + getHeight() + "'" +
            ", medication='" + getMedication() + "'" +
            ", conditions='" + getConditions() + "'" +
            ", password='" + getPassword() + "'" +
            "]";
    }
}