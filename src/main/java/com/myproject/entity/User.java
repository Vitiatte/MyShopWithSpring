package com.myproject.entity;

import com.myproject.entity.enums.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String hashedPassword;

    @Column(name = "salt")
    private String salt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    public User() {
    }

    public static Builder getBuilder() {
        return new User().new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            User.this.id = id;
            return Builder.this;
        }

        public Builder setLogin(String login) {
            User.this.login = login;
            return Builder.this;
        }

        public Builder setHashedPassword(String password) {
            User.this.hashedPassword = password;
            return Builder.this;
        }

        public Builder setUserRole(UserRole role) {
            User.this.userRole = role;
            return Builder.this;
        }

        public Builder setSalt(String salt) {
            User.this.salt = salt;
            return Builder.this;
        }

        public User build() {
            return User.this;
        }
    }
}
