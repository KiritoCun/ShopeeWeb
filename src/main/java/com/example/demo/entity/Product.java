package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
    private int productId;
	
	@ManyToOne
	@JoinColumn(name="category_id")
    private Category category;
	
	@ManyToOne
	@JoinColumn(name="sell_id")
    private Account sell;
	
	@Column(name="url")
    private String url;
	
	@Column(name="description")
    private String description;
	
	@Column(name="price_old")
    private int priceOld;
	
	@Column(name="price_current")
    private int priceCurrent;
	
	@Column(name="sold")
    private int sold;
	
	@Column(name="province")
    private String province;
	
	@Column(name="sale_off")
    private int saleOff;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

	public Account getSell() {
		return sell;
	}

	public void setSell(Account sell) {
		this.sell = sell;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(int priceOld) {
        this.priceOld = priceOld;
    }

    public int getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(int priceCurrent) {
        this.priceCurrent = priceCurrent;
    }


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(int sale_off) {
        this.saleOff = sale_off;
    }

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

}