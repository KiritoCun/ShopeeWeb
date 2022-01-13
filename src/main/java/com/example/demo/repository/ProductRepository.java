package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query(value="select * from Product order by product_id limit ?, 10",nativeQuery = true)
	List<Product> pagingProductHome( int index);
	
	List<Product> findByProductId(int productId);
	
	List<Product> findByCategoryCategoryId(int categoryId);
	
	@Query(value="select count(*) from Product where category_id = ?",nativeQuery = true)
	int getTotalProductByCID(int categoryId);
	
	@Query(value="select * from Product where category_id = ?2 ORDER BY product_id limit ?1, 10",nativeQuery = true)
	List<Product> pagingProductHomeByCategoryId(int index, int categoryId);
	
	@Query(value="select * from Product where description like ? limit 0, 10",nativeQuery = true)
	List<Product> searchByNameFirstPage(String txtSearch);
	
	@Query(value="select * from Product where description like ?1 limit ?2,10 ",nativeQuery = true)
	List<Product> searchByName(String txtSearch,int from);
	
	@Query(value="select count(*) from Product where description like ?",nativeQuery = true)
	int getTotalProductByDescription(String txtSearch);
	
	@Query(value="select * from Product where sell_id = ?2 order by product_id limit ?1, 3",nativeQuery = true)
	List<Product> paggingManagerProduct(int index,int sellId);
	
	@Query(value="select count(*) from Product where sell_id = ?",nativeQuery = true)
	int getTotalProductBySellId(int sellId);
	
	@Modifying
	@Query(value="update product set description = ?1, url = ?2,price_old = ?3,price_current = ?4,province = ?5,sale_off = ?6,category_id = ?7 where product_id = ?8",nativeQuery = true)
	@Transactional
	void editProduct(String description,String url_image,String price_old,String price_current,String province,String sale_off,String category_id,String pid);
	
	@Modifying
	@Query(value="insert into Product(category_id, sell_id, url,description,price_old,price_current,sold,province,sale_off ) VALUES(?7,?8,?2,?1,?3,?4,0,?5,?6 )",nativeQuery = true)
	@Transactional
	void insertProduct(String description, String url_image, String price_old, String price_current, String province, String sale_off, String pcategory, int sell_id);
}
