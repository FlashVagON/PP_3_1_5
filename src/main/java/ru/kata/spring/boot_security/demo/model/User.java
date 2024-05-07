package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Za-zа-яёА-ЯЁ1-90]{2,15}", message = "Name should be between 2 and 15 characters without space")
    private String name;

    @Column(name = "surname")
    @Pattern(regexp = "[A-Za-zа-яёА-ЯЁ1-90]{2,15}", message = "Surname should be between 2 and 15 characters without space")
    private String surname;

    @Column(name = "age")
    private int age;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 3, message = "Password should be greater then 3 symbols")
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty(message = "The role cannot be omitted")
    private Set<Role> roles;

    public User(String name, String surname, int age, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (!password.equals(user.password)) return false;
        return roles != null ? roles.equals(user.roles) : user.roles == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + password.hashCode();
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}

