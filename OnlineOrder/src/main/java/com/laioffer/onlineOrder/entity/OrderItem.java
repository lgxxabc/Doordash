package com.laioffer.onlineOrder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderitem")

public class OrderItem implements Serializable {
    private static final long serialVersionUID = -2455760938054036364L;

    @Id
    // The @GeneratedValue annotation is to configure the way of increment of the specified column(field).
    // 用户在添加 orderItem 的主键时不会重复
    // 目前只有本class可能会被添加新的 id，因此需要 @GeneratedValue
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int quantity;

    private double price;

    @ManyToOne
    private MenuItem menuItem;

    @ManyToOne
//    Break dependency, 防止溢出；因为 Cart 和 OrderItem 相互 depend
    @JsonIgnore
    private Cart cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
