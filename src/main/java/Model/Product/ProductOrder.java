/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;

/**
 *
 * @author pc
 */
public class ProductOrder {
    private int product_id;
    private int order_id;
    private int quantity;
    private int product_order_id;

    public ProductOrder(int product_order_id, int product_id, int order_id, int quantity) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.quantity = quantity;
        this.product_order_id = product_order_id;
    }

	public int getProduct_id() {
		return product_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getProduct_order_id() {
		return product_order_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    
}
