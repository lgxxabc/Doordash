package com.laioffer.onlineOrder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable{
    private static final long serialVersionUID = 2455760938054036111L;

    @Id
    private int id;

    private String address;

    private String name;

    private String phone;

    private String imageUrl;

//    One restaurant to many menuItems，@OneToMany不能创建 FK(否则 Restaurant class 中会有很多重复的FK)
//    mappedBy: 用 指向对应 class 的 FK 的方法（而不是JoinTable）来创建 OneToMany 的关系，mappedBy = “XXX”，XXX是@ManyToOne 所在的 class 名
//    单向关联用 FK、双向关联用 JoinTable
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<MenuItem> menuItemList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

}
