package com.laioffer.onlineOrder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="authorities")
//Serializable 用于内存(Java class)和硬盘(DB)的格式转化，帮助做持久化
public class Authorities implements Serializable {
    // DB 有变化时 serialVersionUID 也会变
    private static final long serialVersionUID = 8734140534986494039L;

    @Id
    private String email;
    private String authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
