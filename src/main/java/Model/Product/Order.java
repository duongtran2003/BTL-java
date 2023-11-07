/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;

import Model.User.User;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Order {
    private int order_id;
    private Product product;
    private int user_id;
    private int has_voucher_id;
    private int quantity;
    private String nametag;
    private String color;
    private int size;
    private int squad_number;
    private Date date_time;

    public Order() {
    }

    public Order(int order_id, Product product, int user_id, int has_voucher_id, int quantity, String nametag, String color, int size, int squad_number, Date date_time) {
        this.order_id = order_id;
        this.product = product;
        this.user_id = user_id;
        this.has_voucher_id = has_voucher_id;
        this.quantity = quantity;
        this.nametag = nametag;
        this.color = color;
        this.size = size;
        this.squad_number = squad_number;
        this.date_time = date_time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public Product getProduct() {
        return product;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getHas_voucher_id() {
        return has_voucher_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNametag() {
        return nametag;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public int getSquad_number() {
        return squad_number;
    }

    public Date getDate_time() {
        return date_time;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", product=" + product + ", user_id=" + user_id + ", has_voucher_id=" + has_voucher_id + ", quantity=" + quantity + ", nametag=" + nametag + ", color=" + color + ", size=" + size + ", squad_number=" + squad_number + ", date_time=" + date_time + '}';
    }

    
    
    
    
}
