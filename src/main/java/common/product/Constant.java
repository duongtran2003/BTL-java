/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.product;

/**
 *
 * @author DELL
 */
public class Constant {
    //VOUCHER
    public static final String URL_VOUCHER_POST_AND_GET= "/product/voucher";
    public static final String URL_VOUCHER_DELETE_VOUCHER_BY_ID= "/product/voucher/delete/*";
    public static final String URL_VOUCHER_EDIT= "/product/voucher/edit";
    //HAS VOUCHER
    public static final String URL_HAS_VOUCHER_POST_AND_GET= "/product/hasvoucher";
    public static final String URL_HAS_VOUCHER_DELETE_BY_ID= "/product/hasvoucher/deletebyid/*";
    public static final String URL_HAS_VOUCHER_DELETE_WHEN_EXPIRED= "/product/hasvoucher/deletewhenexpired";
    public static final String URL_HAS_VOUCHER_EDIT="/product/hasvoucher/edit";
    
    public static final String URL_ORDER_ADD="/product/order/createOrder";
    public static final String URL_ORDER_DELETE="/product/order/cancelOrder";
    public static final String URL_ORDER_GET="/product/order/getOrder";
    
}