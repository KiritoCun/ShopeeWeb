package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product findByProductId (int id){
        return (Product) productRepository.findByProductId(id).get(0);
    }
	
	public List<Product> getAllProduct (){
        return (List<Product>) productRepository.findAll();
    }
	
	public List<Product> pagingProductHome (int index){
        return (List<Product>) productRepository.pagingProductHome((index-1)*10);
    }
	
	public List<Product> pagingProductHomeByCategoryId (int index, int categoryId){
        return (List<Product>) productRepository.pagingProductHomeByCategoryId((index-1)*10,categoryId);
    }
	
	public List<Product> findByCategoryCategoryId (int categoryId){
        return (List<Product>) productRepository.findByCategoryCategoryId(categoryId);
    }
	
	public int getTotalProductByCID (int categoryId){
        return productRepository.getTotalProductByCID(categoryId);
    }
	
	public List<Product> searchByNameFirstPage(String txtSearch){
		return productRepository.searchByNameFirstPage("%"+txtSearch+"%");
	}
	
	public List<Product> searchByName(String txtSearch,int indexPage){
		return productRepository.searchByName("%"+txtSearch+"%",(indexPage-1)*10);
	}
	
	public int getTotalProductByDescription(String txtSearch){
		return productRepository.getTotalProductByDescription("%"+txtSearch+"%");
	}
	
	public List<Product> paggingManagerProduct(int index, int sellId){
		return productRepository.paggingManagerProduct((index-1)*3, sellId);
	}
	
	public int getTotalProductBySellId(int sellId) {
		return productRepository.getTotalProductBySellId(sellId);
	}
	
	public void editProduct(String description,String url_image,String price_old,String price_current,String province,String sale_off,String category_id,String pid) {
		productRepository.editProduct(description, url_image, price_old, price_current, province, sale_off, category_id, pid);
	}
	
	public void insertProduct(String description, String url_image, String price_old, String price_current, String province, String sale_off, String pcategory, int sell_id) {
		productRepository.insertProduct(description, url_image, price_old, price_current, province, sale_off, pcategory, sell_id);
	}
}
