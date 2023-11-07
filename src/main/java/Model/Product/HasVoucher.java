package Model.Product;

import java.sql.Date;


public class HasVoucher {
    private int has_voucher_id;
    private int user_id;
    private int voucher_id;
    private Date expiration_date;

    public HasVoucher(int has_voucher_id, int user_id, int voucher_id, Date expiration_date) {
        this.has_voucher_id = has_voucher_id;
        this.user_id = user_id;
        this.voucher_id = voucher_id;
        this.expiration_date = expiration_date;
    }

    public int getHas_voucher_id() {
        return has_voucher_id;
    }

    public void setHas_voucher_id(int has_voucher_id) {
        this.has_voucher_id = has_voucher_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(int voucher_id) {
        this.voucher_id = voucher_id;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    @Override
    public String toString() {
        return "HasVoucher{" + "has_voucher_id=" + has_voucher_id + ", user_id=" + user_id + ", voucher_id=" + voucher_id + ", expiration_date=" + expiration_date + '}';
    }
    
    
    
}




