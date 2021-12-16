package LifeManager.WebServer.model;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long medicalID;
    private String name;
    private String password;
    private String speciality;
    private int age;
    private char gender;
    private String email;
    private String workplace;
    @OneToMany(mappedBy="doctor", orphanRemoval = true)
    private List<Pacient> pacients;

    public Doctor() {

    }

    public Doctor(String name, Long medicalID, int age, char gender, String email, String workplace, String speciality, String password) {
        this.medicalID = medicalID;
        this.name = name;
        this.password = password;
        this.speciality = speciality;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.workplace = workplace;
        this.pacients = new ArrayList<Pacient>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "medical_ID", nullable = false, unique = true)
    public Long getMedicalID() {
        return medicalID;
    }
    public void setMedicalID(Long medicalID) {
        this.medicalID = medicalID;
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

    @Column(name = "speciality", nullable = false)
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "gender", nullable = false)
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }

    @Column(name = "workplace", nullable = false)
    public String getWorkplace() {
        return workplace;
    }
    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Doctor [medicalID='" + getMedicalID() + "'" +
            ", name='" + getName() + "'" +
            ", password='" + getPassword() + "'" +
            ", speciality='" + getSpeciality() + "'" +
            ", age='" + getAge() + "'" +
            ", gender='" + getGender() + "'" +
            ", email='" + getEmail() + "'" +
            ", workplace='" + getWorkplace() + "'" +
            "]";
    }
}
