package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sell_id")
    private int sellId;
	
	@Column(name="cmnd")
    private int cmnd;
	
	@Column(name="full_name")
    private String fullname;
	
	@Column(name="phone")
    private String phone;
	
	@Column(name="address")
    private String address;
	
	@Column(name="user_name")
    private String username;
	
	@Column(name="password")
    private String password;
	
	@Column(name="link_image")
    private String linkImage;
	
	@OneToMany(mappedBy = "sell", fetch = FetchType.LAZY)
	private List<Product> productList;

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sell_id) {
        this.sellId = sell_id;
    }

    public int getCmnd() {
        return cmnd;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUser_name(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLink_image(String link_image) {
        this.linkImage = link_image;
    }
    
    public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
