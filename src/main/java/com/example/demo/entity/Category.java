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
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
    private int categoryId;
	
	@Column(name="category_name")
    private String categoryName;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Product> productList;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
    public List<Product> getProductList() {
		return productList;
	}

	public void setProduct(List<Product> productList) {
		this.productList = productList;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
 
}
