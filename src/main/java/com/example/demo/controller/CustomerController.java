package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
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
@RequestMapping(value = "/")
public class CustomerController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = { "/", "/home" })
	public String goToHomepage(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String action = request.getParameter("action");
		if ("login".equals(action)) {
			String userlogin = request.getParameter("user");
			String passlogin = request.getParameter("pass");
			Account a = accountService.checkLogin(userlogin, passlogin);
			if (a != null) {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(600); // 10 phút
				session.setAttribute("acc", a);

			} else {
				model.addAttribute("active", "active");
				model.addAttribute("annouce", "Thông tin đăng nhập không chính xác !");
			}

			String indexPage = request.getParameter("index");
			if (indexPage == null) {
				indexPage = "1";
			}
			int index = Integer.parseInt(indexPage);
			int count = productService.getAllProduct().size();
			int endPage = count / 10;
			if (count % 10 != 0) {
				endPage++;
			}
			String rememberMe = request.getParameter("rememberMe");
			if (rememberMe != null && rememberMe != "") {
				Cookie u = new Cookie("userNameC", userlogin);
				Cookie p = new Cookie("passwordC", passlogin);
				Cookie remember = new Cookie("rememberMe", rememberMe);
				u.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
				u.setPath("/");
				p.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
				p.setPath("/");
				remember.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
				remember.setPath("/");
				response.addCookie(u);
				response.addCookie(p);
				response.addCookie(remember);
			} else {
				Cookie cookieU = new Cookie("userNameC", "");
				cookieU.setMaxAge(0);
				cookieU.setPath("/");
				response.addCookie(cookieU);

				Cookie cookieP = new Cookie("passwordC", "");
				cookieP.setMaxAge(0);
				cookieP.setPath("/");
				response.addCookie(cookieP);

				Cookie remember = new Cookie("rememberMe", "");
				cookieP.setMaxAge(0);
				remember.setPath("/");
				response.addCookie(remember);
			}
			model.addAttribute("listP", productService.pagingProductHome(index));
			model.addAttribute("listC", categoryService.getAllCategory());
			model.addAttribute("endPage", endPage);
			model.addAttribute("tagPage", index);
		} else {
			if ("register".equals(action)) {
				String userregister = request.getParameter("username");
				String password = request.getParameter("password");
				String repassword = request.getParameter("repassword");
				if (password.equals(repassword)) {
					List<Account> a = accountService.checkRegister(userregister);
					if (a.size() == 0) {
						accountService.register(userregister, password);

						String indexPage = request.getParameter("index");
						if (indexPage == null) {
							indexPage = "1";
						}
						int index = Integer.parseInt(indexPage);
						int count = productService.getAllProduct().size();
						int endPage = count / 10;
						if (count % 10 != 0) {
							endPage++;
						}
						model.addAttribute("listP", productService.pagingProductHome(index));
						model.addAttribute("listC", categoryService.getAllCategory());
						model.addAttribute("endPage", endPage);
						model.addAttribute("tagPage", index);
					}
				} else {

				}
			} else {

				String indexPage = request.getParameter("index");
				if (indexPage == null) {
					indexPage = "1";
				}
				int index = Integer.parseInt(indexPage);
				int count = productService.getAllProduct().size();
				int endPage = count / 10;
				if (count % 10 != 0) {
					endPage++;
				}
				model.addAttribute("listP", productService.pagingProductHome(index));
				model.addAttribute("listC", categoryService.getAllCategory());
				model.addAttribute("endPage", endPage);
				model.addAttribute("tagPage", index);
			}
		}
		
		boolean check = true;
		Cookie ar[] = request.getCookies();
		if (ar != null) {
			for (Cookie o : ar) {
				if (o.getName().equals("id")) {
					check = false;
					break;
				}
			}
		}

		if (check) {
			Cookie c = new Cookie("id", "");
			c.setMaxAge(60 * 60 * 24);
			response.addCookie(c);
			c.setPath("/");
		}

		List<Product> productsCart = new ArrayList<>();
		if (!check) {
			Cookie arr[] = request.getCookies();
			for (Cookie o : arr) {
				if (o.getName().equals("id")) {
					if (o.getValue() != "") {
						String txt[] = o.getValue().split("a");
						for (String s : txt) {
							productsCart.add(productService.findByProductId(Integer.parseInt(s)));
						}
					}
				}
			}
		}

		model.addAttribute("numberProductIntoCart", productsCart.size());
        model.addAttribute("productsIntoCart", productsCart);
        
		return "customer/Home";
	}

	@RequestMapping(value = "category")
	public String filterByCategory(Model model, HttpServletRequest request, HttpServletResponse response) {
		boolean check = true;
		Cookie ar[] = request.getCookies();
		if (ar != null) {
			for (Cookie o : ar) {
				if (o.getName().equals("id")) {
					check = false;
					break;
				}
			}
		}

		if (check) {
			Cookie c = new Cookie("id", "");
			c.setMaxAge(60 * 60 * 24);
			response.addCookie(c);
			c.setPath("/");
		}

		List<Product> productsCart = new ArrayList<>();
		if (!check) {
			Cookie arr[] = request.getCookies();
			for (Cookie o : arr) {
				if (o.getName().equals("id")) {
					if (o.getValue() != "") {
						String txt[] = o.getValue().split("a");
						for (String s : txt) {
							productsCart.add(productService.findByProductId(Integer.parseInt(s)));
						}
					}
				}
			}
		}

		int cateID = Integer.parseInt(request.getParameter("cid"));
		String indexPage = request.getParameter("index");
		if (indexPage == null) {
			indexPage = "1";
		}
		int index = Integer.parseInt(indexPage);
		int count = productService.getTotalProductByCID(cateID);
		int endPage = count / 10;
		if (count % 10 != 0) {
			endPage++;
		}
		
		model.addAttribute("numberProductIntoCart", productsCart.size());
        model.addAttribute("productsIntoCart", productsCart);
		model.addAttribute("listC", categoryService.getAllCategory());
		model.addAttribute("listP", productService.pagingProductHomeByCategoryId(index, cateID));
		model.addAttribute("tag", cateID);
		model.addAttribute("endPage", endPage);
		model.addAttribute("tagPage", index);
		model.addAttribute("isCategory", "category?cid=" + cateID + "&index");

		return "customer/Home";
	}

	@RequestMapping(value = "searchAjaxPagging")
	public void searchByAjaxPagging(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String txtSearch = request.getParameter("txt");// váy
		int total = productService.getTotalProductByDescription(txtSearch);
		int numberPagePagging;
		if (total % 10 != 0) {
			numberPagePagging = total / 10 + 1;
		} else {
			numberPagePagging = total / 10;
		}
		PrintWriter out = response.getWriter();
		if(total == 0) {
			out.println("");
		} else {
			out.println("<li class=\"pagination-item invisible\"><a\r\n" + "								href=\"\r\n"
					+ "								\" class=\"pagination-item__link\"> <i\r\n"
					+ "									class=\"pagination-item__icon fas fa-angle-left\"></i>\r\n"
					+ "							</a></li>");
			for (int i = 1; i <= numberPagePagging; i++) {
				out.println("<li class=\"pagination-item " + (i == 1 ? "pagination-item--active" : "") + "\">\r\n"
						+ "										<a href=\"search?txt=" + txtSearch + "&page=" + i
						+ "\" class=\"pagination-item__link\">\r\n" + "                                    " + i
						+ "</a>\r\n" + "								</li>");
			}
			if (numberPagePagging > 1) {
				out.println("<li class=\"pagination-item\">\r\n" + "								<a href=\"search?txt="
						+ txtSearch + "&page=2 \" class=\"pagination-item__link\">\r\n"
						+ "									<i class=\"pagination-item__icon fas fa-angle-right\"></i>\r\n"
						+ "							</a>\r\n" + "							</li>");
			}
		}
	}

	@RequestMapping(value = "searchAjaxPaggingTop")
	public void searchByAjaxPaggingTop(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String txtSearch = request.getParameter("txt");// váy
		int total = productService.getTotalProductByDescription(txtSearch);
		int numberPagePagging;
		if (total % 10 != 0) {
			numberPagePagging = total / 10 + 1;
		} else {
			numberPagePagging = total / 10;
		}
		PrintWriter out = response.getWriter();
		out.println("<span class=\"home-filter__page-num\"> <span\r\n"
				+ "									class=\"home-filter__page-current\">1</span>/" + numberPagePagging
				+ "\r\n" + "								</span>\r\n" + "\r\n"
				+ "								<div class=\"home-filter__page-control\">\r\n"
				+ "									<a href=\"\" id='leftArrow'\r\n"
				+ "										class=\"home-filter__page-btn home-filter__page-btn--disabled\">\r\n"
				+ "										<i class=\"home-filter__page-icon fas fa-angle-left\"></i>\r\n"
				+ "									</a> <a href=\"search?txt=" + txtSearch + "&page=2\r\n"
				+ "										\"" + (numberPagePagging == 1 ? "id='rightrow'" : "") + " \r\n"
				+ "										class=\"home-filter__page-btn "
				+ (numberPagePagging == 1 ? "home-filter__page-btn--disabled" : "") + "\">\r\n"
				+ "										<i class=\"home-filter__page-icon fas fa-angle-right\"></i>\r\n"
				+ "									</a>\r\n" + "								</div>");
	}

	@RequestMapping(value = "searchAjaxChangeLinkPaging")
	public void searchAjaxChangeLinkPaging(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String txtSearch = request.getParameter("txt");// váy
		PrintWriter out = response.getWriter();
		out.println("											<a href=\"search?txt=" + txtSearch
				+ "&page=0\" id=\"leftArrow\"\r\n"
				+ "												class=\"home-filter__page-btn home-filter__page-btn--disabled\">\r\n"
				+ "												<i class=\"home-filter__page-icon fas fa-angle-left\"></i>\r\n"
				+ "											</a>\r\n"
				+ "											<a href=\"search?txt=&page=2\"\r\n"
				+ "												class=\"home-filter__page-btn\">\r\n"
				+ "												<i class=\"home-filter__page-icon fas fa-angle-right\"></i>\r\n"
				+ "											</a>");
	}

	@RequestMapping(value = "search")
	public String search(Model model, HttpServletRequest request, HttpServletResponse response) {
		boolean check = true;
		Cookie ar[] = request.getCookies();
		if (ar != null) {
			for (Cookie o : ar) {
				if (o.getName().equals("id")) {
					check = false;
					break;
				}
			}
		}

		if (check) {
			Cookie c = new Cookie("id", "");
			c.setMaxAge(60 * 60 * 24);
			response.addCookie(c);
			c.setPath("/");
		}

		List<Product> productsCart = new ArrayList<>();
		if (!check) {
			Cookie arr[] = request.getCookies();
			for (Cookie o : arr) {
				if (o.getName().equals("id")) {
					if (o.getValue() != "") {
						String txt[] = o.getValue().split("a");
						for (String s : txt) {
							productsCart.add(productService.findByProductId(Integer.parseInt(s)));
						}
					}
				}
			}
		}

		String txtSearch = request.getParameter("txt");// váy
		String indexPage = request.getParameter("page");
		if (indexPage == null) {
			indexPage = "1";
		}
		int index = Integer.parseInt(indexPage);
		int count = productService.getTotalProductByDescription(txtSearch);
		int endPage = count / 10;
		if (count % 10 != 0) {
			endPage++;
		}

		model.addAttribute("numberProductIntoCart", productsCart.size());
        model.addAttribute("productsIntoCart", productsCart);
		request.setAttribute("isSearch", "isSearch");
		request.setAttribute("listP", productService.searchByName(txtSearch, index));
		request.setAttribute("listC", categoryService.getAllCategory());
		request.setAttribute("txtS", txtSearch);
		request.setAttribute("endPage", endPage);
		request.setAttribute("tagPage", index);

		return "customer/Home";
	}

	@RequestMapping(value = "productDetail")
	public String productDetail(Model model, HttpServletRequest request) {
		String id = request.getParameter("pid");
        List<Product> list = new ArrayList<>();
        Cookie arr[] = request.getCookies();
        if(arr!=null){
                for (Cookie o : arr) {
                if (o.getName().equals("id")) {
                    if(o.getValue()!=""){
                        String txt[] = o.getValue().split("a");
                        for (String s : txt) {
                            list.add(productService.findByProductId(Integer.parseInt(s)));
                        }
                    }

                }
            } 
        }

        int numberProductIntoCart = list.size();
		Product p = productService.findByProductId(Integer.parseInt(id));
		model.addAttribute("detail", p);
        request.setAttribute("productsIntoCart", list);
        request.setAttribute("numberProductIntoCart", numberProductIntoCart);

		return "customer/Detail";
	}
	
	@RequestMapping(value = "cartControl")
	public String goToCart(Model model, HttpServletRequest request) {
		String pid = request.getParameter("pid");
        List<Product> list = new ArrayList<>();
        Cookie arr[] = request.getCookies();
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                if(o.getValue()!=""){
                       String txt[] = o.getValue().split("a");
                    for (String s : txt) {
                        list.add(productService.findByProductId(Integer.parseInt(s)));
                    }
                }             
            }
        }   
        double total = 0;
        for (Product o : list) {
            total = total + 1 * o.getPriceCurrent();
        }
        int sum = ((int)(1.1*total/1000))*1000;
        int numberProductIntoCart = list.size();
        model.addAttribute("total", total);
        model.addAttribute("vat", 0.1 * total);
        model.addAttribute("sum", sum);
        model.addAttribute("productsIntoCart", list);
        if(pid!=null){
            model.addAttribute("pid", pid);
        }
        model.addAttribute("numberProductIntoCart", numberProductIntoCart);

		return "customer/Cart";
	}
	
	@RequestMapping(value = "addToCart")
	public void addToCart(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String pid = request.getParameter("productId");   
        String quanlity = request.getParameter("quanlity");
        Product product = productService.findByProductId(Integer.parseInt(pid));
        PrintWriter out = response.getWriter();       
        out.println("<li class=\"header__cart-item\">\n" +
"                                        <img src=\""+product.getUrl()+"\" alt=\""+product.getDescription()+"\" class=\"header__cart-img\">\n" +
"                                        <div class=\"header__cart-item-info\">\n" +
"                                            <div class=\"header__cart-item-head\">\n" +
"                                                <h5 class=\"header__cart-item-name\">"+product.getDescription()+"</h5>\n" +
"                                                <div class=\"header__cart-item-price-wrap\">\n" +
"                                                    <span class=\"header__cart-item-price\">"+product.getPriceOld()/1000+".000đ</span>\n" +
"                                                    <span class=\"header__cart-item-multiply\">x</span>\n" +
"                                                    <span class=\"header__cart-item-qnt\">"+quanlity+"</span>\n" +
"                                                </div>\n" +
"                                            </div>\n" +
"\n" +
"                                            <div class=\"header__cart-item-body\">\n" +
"                                                <span class=\"header__cart-item-description\">\n" +
"                                                    Phân chia loại sản phẩm Phân chia loại sản phẩm Phân chia loại sản phẩm\n" +
"                                                </span>\n" +
"                                                <span onclick=\"deleteFromCart("+product.getProductId()+");changeCookieCartDelete("+product.getProductId()+");changeNumberCartDelete();checkHtmlCartDelete();\" class=\"header__cart-item-del\">\n" +
"                                                    Xóa\n" +
"                                                </span>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"                                    </li>");
	}
	
	@RequestMapping(value = "removeProductCart")
	public void removeProductCart(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pid = request.getParameter("pid");
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        String ids[] = txt.split("a");
        String txtOutPut = "";
        for (int i = 0; i < ids.length; i++) {
            if (!ids[i].equals(pid)) {
                if (txtOutPut.isEmpty()) {
                    txtOutPut = ids[i];
                } else {
                    txtOutPut = txtOutPut + "a" + ids[i];
                }
            }
        }
        if (!txtOutPut.isEmpty()) {
            Cookie c = new Cookie("id", txtOutPut);
            c.setMaxAge(60 * 60 * 24);
            response.addCookie(c);
        }
        response.sendRedirect("cartControl");
	}
	
	@RequestMapping(value = "deleteFromCart")
	public void deleteFromCart(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		 String pid = request.getParameter("productId");  

	        List<Product> list = new ArrayList<>();
	        Cookie arr[] = request.getCookies();
	        for (Cookie o : arr) {
	            if (o.getName().equals("id")) {
	                if(o.getValue()!=""){
	                       String txt[] = o.getValue().split("a");
	                    for (String s : txt) {
	                        if(!pid.equals(s)){
	                           list.add(productService.findByProductId(Integer.parseInt(s)));
	                        }
	                    }
	                }             
	            }
	        }
	        
	        Product product = productService.findByProductId(Integer.parseInt(pid));
	        PrintWriter out = response.getWriter();  
	        String outPrint ="";
	        for(Product p:list){
	            outPrint += "<li class=\"header__cart-item\">\n" +
	"                                        <img src=\""+p.getUrl()+"\" alt=\""+p.getDescription()+"\" class=\"header__cart-img\">\n" +
	"                                        <div class=\"header__cart-item-info\">\n" +
	"                                            <div class=\"header__cart-item-head\">\n" +
	"                                                <h5 class=\"header__cart-item-name\">"+p.getDescription()+"</h5>\n" +
	"                                                <div class=\"header__cart-item-price-wrap\">\n" +
	"                                                    <span class=\"header__cart-item-price\">"+p.getPriceOld()/1000+".000đ</span>\n" +
	"                                                    <span class=\"header__cart-item-multiply\">x</span>\n" +
	"                                                    <span class=\"header__cart-item-qnt\">1</span>\n" +
	"                                                </div>\n" +
	"                                            </div>\n" +
	"\n" +
	"                                            <div class=\"header__cart-item-body\">\n" +
	"                                                <span class=\"header__cart-item-description\">\n" +
	"                                                    Phân chia loại sản phẩm Phân chia loại sản phẩm Phân chia loại sản phẩm\n" +
	"                                                </span>\n" +
	"                                                <span onclick=\"deleteFromCart("+p.getProductId()+");changeCookieCartDelete("+product.getProductId()+");changeNumberCartDelete();checkHtmlCartDelete()\" class=\"header__cart-item-del\">\n" +
	"                                                    Xóa\n" +
	"                                                </span>\n" +
	"                                            </div>\n" +
	"                                        </div>\n" +
	"                                    </li>\n";
	        }
	        out.println(outPrint);
	}
	
	@RequestMapping(value = "changeCookieCart")
	public void changeCookieCart(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String pid = request.getParameter("productId");  
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
            }
        }
        if (txt.isEmpty()) {
            txt = pid;
        } else {
            txt = txt + "a" + pid;
        }
        PrintWriter out = response.getWriter(); 
        out.println(""+txt);
	}
	
	@RequestMapping(value = "changeCookieCartDelete")
	public void changeCookieCartDelete(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		 String pid = request.getParameter("productId");  
	        Cookie arr[] = request.getCookies();
	        String txt = "";
	        for (Cookie o : arr) {
	            if (o.getName().equals("id")) {
	                txt = txt + o.getValue();
	            }
	        }
	        String array[] = txt.split("a");
	        for(int i=0;i<array.length;i++){
	            if(array[i].equals(pid)){
	                array[i] = "";
	            }
	        }
	        String cookie = "";
	        
	        if(array.length == 1){
	            cookie = "";
	        }else if(array.length == 2){
	            for(int j=0;j<array.length;j++){
	                if(array[j]!=""){
	                    cookie = array[j];
	                }                  
	            }
	        }else{
	            for(int j=0;j<array.length;j++){
	                if(array[j]!=""){
	                    if(j==array.length){
	                        cookie +=array[j];
	                    }else{
	                        cookie +=array[j]+"a";
	                    }
	                    
	                }                  
	            }
	        }
	        
	        PrintWriter out = response.getWriter(); 
	        out.println(""+cookie);
	}
	
	@RequestMapping(value = "changeNumberCart")
	public void changeNumberCart(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		 List<Product> productsCart = new ArrayList<>();
	        Cookie arr[] = request.getCookies();
	        for (Cookie o : arr) {
	            if (o.getName().equals("id")) {
	                if(o.getValue()!=""){
	                    String txt[] = o.getValue().split("a");
	                    for (String s : txt) {
	                        productsCart.add(productService.findByProductId(Integer.parseInt(s)));
	                    }
	                }
	                
	            }
	        }  
	        int numberProductIntoCart = productsCart.size()+1;
	        PrintWriter out = response.getWriter(); 
	        out.println(""+numberProductIntoCart);
	}
	
	@RequestMapping(value = "changeNumberCartDelete")
	public void changeNumberCartDelete(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		 List<Product> productsCart = new ArrayList<>();
	        Cookie arr[] = request.getCookies();
	        for (Cookie o : arr) {
	            if (o.getName().equals("id")) {
	                if(o.getValue()!=""){
	                    String txt[] = o.getValue().split("a");
	                    for (String s : txt) {
	                        productsCart.add(productService.findByProductId(Integer.parseInt(s)));
	                    }
	                }
	                
	            }
	        }  
	        int numberProductIntoCart = productsCart.size()-1;
	        PrintWriter out = response.getWriter(); 
	        out.println(""+numberProductIntoCart);
	}
	
	@RequestMapping(value = "checkNoCart")
	public void checkNoCart(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		 boolean check=false;
	        Cookie arr[] = request.getCookies();
	        for (Cookie o : arr) {
	            if (o.getName().equals("id")) {
//	                check = Integer.parseInt(o.getValue())==0;
	                    check = "".equals(o.getValue());
	            }
	        }  
	        PrintWriter out = response.getWriter(); 
	        if(check){
	            out.println("true");
	        }else{
	            out.println("false");
	        }
	}
	
	@RequestMapping(value = "checkNoCartDelete")
	public void checkNoCartDelete(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		 boolean check=false;
	        Cookie arr[] = request.getCookies();
	        for (Cookie o : arr) {
	            if (o.getName().equals("id")) {
//	                    check = o.getValue().length()==1;
	                    try {
	                        Double.parseDouble(o.getValue());
	                        check = true;
	                    } catch (NumberFormatException nfe) {
	                        check = false;
	                    }
	            }
	        }  
	        PrintWriter out = response.getWriter(); 
	        if(check){
	            out.println("true");
	        }else{
	            out.println("false");
	        }
	}
	
}
