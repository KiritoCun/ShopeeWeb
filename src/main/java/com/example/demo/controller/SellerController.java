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
	
	@RequestMapping(value = "searchAjax")
	public void searchByAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String txtSearch = request.getParameter("txt");// váy
		List<Product> list = productService.searchByNameFirstPage(txtSearch);
		PrintWriter out = response.getWriter();
		for (Product o : list) {
			out.println("<div class=\"product col p-2-4 t-4 m-6\">\n"
					+ "                                    <a class=\"home-product-item\" href=\"detail?pid="
					+ o.getProductId() + "\">\n" + "                                        \n"
					+ "                                        <div class=\"home-product-item__img\" style=\"background-image: url("
					+ o.getUrl() + ");\"></div>\n" + "                                        \n"
					+ "                                        <h4 class=\"home-product-item__name\">\n"
					+ "                                            " + o.getDescription() + "\n"
					+ "                                        </h4>\n" + "                                        \n"
					+ "                                        <div class=\"home-product-item__price\">\n"
					+ "                                            <span class=\"home-product-item__price-old\">"
					+ o.getPriceOld() / 1000 + "00đ</span>\n"
					+ "                                            <span class=\"home-product-item__price-current\">"
					+ o.getPriceCurrent() / 1000 + "00đ</span>\n" + "                                        </div>\n"
					+ "\n" + "                                        <div class=\"home-product-item__action\">\n"
					+ "                                            <!-- Liked: home-product-item__like--liked -->\n"
					+ "                                            <span class=\"home-product-item__like home-product-item__like--liked\">\n"
					+ "                                                <i class=\"home-product-item__like-icon-empty far fa-heart\"></i>\n"
					+ "                                                <i class=\"home-product-item__like-icon-fill fas fa-heart\"></i>\n"
					+ "                                            </span>\n" + "\n"
					+ "                                            <div class=\"home-product-item__rating\">\n"
					+ "                                                <i class=\"home-product-item__star--gold fas fa-star\"></i>\n"
					+ "                                                <i class=\"home-product-item__star--gold fas fa-star\"></i>\n"
					+ "                                                <i class=\"home-product-item__star--gold fas fa-star\"></i>\n"
					+ "                                                <i class=\"home-product-item__star--gold fas fa-star\"></i>\n"
					+ "                                                <i class=\"fas fa-star\"></i>\n"
					+ "                                            </div>\n"
					+ "                                            <span class=\"home-product-item__sold\">"
					+ o.getSold() + " đã bán</span>\n" + "                                        </div>\n" + "\n"
					+ "                                        <div class=\"home-product-item__origin\">\n"
					+ "                                            <span class=\"home-product-item__brand\">Whoo</span>\n"
					+ "                                            <span class=\"home-product-item__origin-name\">"
					+ o.getProvince() + "</span>\n" + "                                        </div>\n" + "\n"
					+ "                                        <div class=\"home-product-item__favorite\">\n"
					+ "                                            <i class=\"fas fa-check\"></i>\n"
					+ "                                            <span>Yêu thích</span>\n"
					+ "                                        </div>\n" + "\n"
					+ "                                        <div class=\"home-product-item__sale-off\">\n"
					+ "                                            <span class=\"home-product-item__sale-off-percent\">"
					+ o.getSaleOff() + "%</span>\n"
					+ "                                            <span class=\"home-product-item__sale-off-label\">GIẢM</span>\n"
					+ "                                        </div>\n" + "                                    </a>\n"
					+ "                                </div> ");
		}
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
