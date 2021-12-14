package LifeManager.WebServer;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long medicalID;
    private String first_name;
    private String last_name;
    private String password;
    private String speciality;
    private int age;
    private char gender;
    private String email;
    private String workplace;

    public Doctor() {

    }

    public Doctor(String first_name, String last_name, Long medicalID, int age, char gender, String email, String workplace, String speciality, String password) {
        this.medicalID = medicalID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.speciality = speciality;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.workplace = workplace;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "medical_ID", nullable = false)
    public Long getMedicalID() {
        return medicalID;
    }
    public void setMedicalID(Long medicalID) {
        this.medicalID = medicalID;
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
            ", first_name='" + getFirstName() + "'" +
            ", last_name='" + getLastName() + "'" +
            ", password='" + getPassword() + "'" +
            ", speciality='" + getSpeciality() + "'" +
            ", age='" + getAge() + "'" +
            ", gender='" + getGender() + "'" +
            ", email='" + getEmail() + "'" +
            ", workplace='" + getWorkplace() + "'" +
            "]";
    }
}
