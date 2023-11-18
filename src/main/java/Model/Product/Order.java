/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Model.User.User;

/**
 *
 * @author DELL
 */
public class Order {
    private int order_id;
    private int user_id;
    private Timestamp date;
    private List<ProductOrder> entries = new ArrayList<> ();
    private int status;

    public Order(int order_id, int user_id, List<ProductOrder> entries) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.entries = entries;
        this.status = 0; // 0 - pending, 1 - delivering, 2 - delivered
    }

	public int getOrder_id() {
		return order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public Timestamp getDate() {
		return date;
	}

	public List<ProductOrder> getEntries() {
		return entries;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
