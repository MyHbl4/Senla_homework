package org.example.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Name should not be empty")//не должно быть пустым
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
//минимум 2 до 30 знаков
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be more than 0")//минимум 0 и более
    @Column(name = "age")
    private int age;

    @NotEmpty(message = "Email should not be empty")//не может быть пустым
    @Email(message = "Email should be valid")//не верный шаблон
    @Column(name = "email")
    private String email;

    public Person() {
    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

  public Person(String name, int age, String email) {
    this.name = name;
    this.age = age;
    this.email = email;
  }

  public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}