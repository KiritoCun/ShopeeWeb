package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Account;
import com.example.demo.entity.Product;
import com.example.demo.service.AccountService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;


@Controller
public class SellerController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "manageProduct")
	public String filterByCategory(Model model, HttpServletRequest request, HttpServletResponse response) {
	        HttpSession session = request.getSession();
	        Account a = (Account) session.getAttribute("acc");
	        int id = a.getSellId();
	        String indexPage = request.getParameter("index");
	        if(indexPage == null){
	            indexPage = "1";
	        }
	        int index = Integer.parseInt(indexPage);
	        int count = productService.getTotalProductBySellId(id);
	        int endPage = count/3;
	        if(count % 3 != 0){
	            endPage++;
	        }

	        request.setAttribute("listC", categoryService.getAllCategory());
	        request.setAttribute("listP", productService.paggingManagerProduct(index,id));
	        request.setAttribute("endPage", endPage);
	        request.setAttribute("tag", index);
	        request.setAttribute("total", count);

		return "seller/ManagerProduct";
	}
	
	@RequestMapping(value = "logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("acc");  
        response.sendRedirect("home");
	}
	
	@RequestMapping(value = "addProduct")
	public String addProduct(Model model, HttpServletRequest request) {
	        String indexPage = request.getParameter("index");
	        if(indexPage == null){
	            indexPage = "1";
	        }
	        int index = Integer.parseInt(indexPage);
	        model.addAttribute("listC",categoryService.getAllCategory());
	        model.addAttribute("tag", index);

		return "seller/AddProduct";
	}
	
	@RequestMapping(value = "doAddProduct")
	public void doAddProduct(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String description = request.getParameter("description");
        String url_image = request.getParameter("url_image");
        String price_old = request.getParameter("price_old");
        String price_current = request.getParameter("price_current");
        String province = request.getParameter("province");
        String sale_off = request.getParameter("sale_off");
        String pcategory = request.getParameter("category");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int sell_id = a.getSellId();
        productService.insertProduct(description, url_image, price_old, price_current, province, sale_off, pcategory, sell_id);
        response.sendRedirect("manageProduct");
	}
	
	@RequestMapping(value = "loadProduct")
	public String loadProduct(Model model, HttpServletRequest request) {
		String id = request.getParameter("pid");
        String tag = request.getParameter("tag");
        
        model.addAttribute("listC",categoryService.getAllCategory());
        model.addAttribute("detail",productService.findByProductId(Integer.parseInt(id)));
        model.addAttribute("tag",tag);

		return "seller/EditProduct";
	}
	
	@RequestMapping(value = "editProduct")
	public void editProduct(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pid = request.getParameter("product_id");
        String description = request.getParameter("description");
        String url_image = request.getParameter("url_image");
        String price_old = request.getParameter("price_old");
        String price_current = request.getParameter("price_current");
        String province = request.getParameter("province");
        String sale_off = request.getParameter("sale_off");
        String category_id = request.getParameter("category");
        
        productService.editProduct(description, url_image, price_old, price_current, province, sale_off, category_id, pid);
        response.sendRedirect("manageProduct");
	}
	
	@RequestMapping(value = "changeUrlDeleteProduct")
	public void changeUrlDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String productId = request.getParameter("txt");
		PrintWriter out = response.getWriter();
		out.println("<a style=\"display:block;width:100%;text-decoration:none;color:black\" href=\"deleteProduct?pid="+productId+"\">Delete</a>");
		
	}
}
