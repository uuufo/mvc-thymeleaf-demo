package dev.jlarsen.mvcthymeleafdemo.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min=5, max=40, message="Please enter First and Last name.")
    private String name;

    @NotBlank(message = "Please enter a valid e-mail address.")
    @Email(message = "Please enter a valid e-mail address.")
    private String email;

    @Column(length = 60)
    //@Size(min = 8, max = 25, message="Must be between 8 and 25 characters.")
    private String password;

    private String note;

    @NotBlank(message="Please choose a Mood.")
    private String mood;

    private boolean human;

    private String birthday;

    @NotBlank(message = "Please choose a profession.")
    private String profession;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public boolean isHuman() {
        return human;
    }

    public void setHuman(boolean human) {
        this.human = human;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mood='" + mood + '\'' +
                ", note='" + note + '\'' +
                ", human=" + human +
                ", birthday=" + birthday +
                ", profession='" + profession + '\'' +
                '}';
    }
}