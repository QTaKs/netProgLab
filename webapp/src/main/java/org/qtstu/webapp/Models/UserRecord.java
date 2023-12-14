package org.qtstu.webapp.Models;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name="user", schema="main")
public class UserRecord{
    @Id
    @GeneratedValue
    @Column(name="id", unique=true,nullable=false)
    private Integer id;
    @Column(name="registrationDate",nullable=false)
    private Date registrationDate;
    @Column(name="username",nullable=false)
    private String username;
    UserRecord(Integer id,Date registrationDate,String username){
        this.setId(id);
        this.setRegistrationDate(registrationDate);
        this.setUsername(username);
    }

    public UserRecord() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
