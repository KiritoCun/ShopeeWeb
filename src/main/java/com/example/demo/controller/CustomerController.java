package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
		List<Integer> quanlityProductsCart = new ArrayList<>();
		if (!check) {
			Cookie arr[] = request.getCookies();
			for (Cookie o : arr) {
				if (o.getName().equals("id")) {
					if (o.getValue() != "") {
						String txt[] = o.getValue().split("a");
						for (String s : txt) {
							String tx[] = s.split("w");
							productsCart.add(productService.findByProductId(Integer.parseInt(tx[0])));
							quanlityProductsCart.add(Integer.parseInt(tx[1]));
						}
					}
				}
			}
		}

		model.addAttribute("numberProductIntoCart", productsCart.size());
		model.addAttribute("productsIntoCart", productsCart);
		model.addAttribute("quanlityProductsCart", quanlityProductsCart);
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
		List<Integer> quanlityProductsCart = new ArrayList<>();
		if (!check) {
			Cookie arr[] = request.getCookies();
			for (Cookie o : arr) {
				if (o.getName().equals("id")) {
					if (o.getValue() != "") {
						String txt[] = o.getValue().split("a");
						for (String s : txt) {
							String tx[] = s.split("w");
							productsCart.add(productService.findByProductId(Integer.parseInt(tx[0])));
							quanlityProductsCart.add(Integer.parseInt(tx[1]));
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
		model.addAttribute("quanlityProductsCart", quanlityProductsCart);
		model.addAttribute("productsIntoCart", productsCart);
		model.addAttribute("listC", categoryService.getAllCategory());
		model.addAttribute("listP", productService.pagingProductHomeByCategoryId(index, cateID));
		model.addAttribute("tag", cateID);
		model.addAttribute("endPage", endPage);
		model.addAttribute("tagPage", index);
		model.addAttribute("isCategory", "category?cid=" + cateID + "&index");

		return "customer/Home";
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
					+ "                                    <a class=\"home-product-item\" href=\"productDetail?pid="
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
		if (total > 0) {
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
				out.println("<li class=\"pagination-item\">\r\n"
						+ "								<a href=\"search?txt=" + txtSearch
						+ "&page=2 \" class=\"pagination-item__link\">\r\n"
						+ "									<i class=\"pagination-item__icon fas fa-angle-right\"></i>\r\n"
						+ "							</a>\r\n" + "							</li>");
			}
		} else {
			out.print("");
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
		List<Integer> quanlityProductsCart = new ArrayList<>();
		if (!check) {
			Cookie arr[] = request.getCookies();
			for (Cookie o : arr) {
				if (o.getName().equals("id")) {
					if (o.getValue() != "") {
						String txt[] = o.getValue().split("a");
						for (String s : txt) {
							String tx[] = s.split("w");
							productsCart.add(productService.findByProductId(Integer.parseInt(tx[0])));
							quanlityProductsCart.add(Integer.parseInt(tx[1]));
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
		model.addAttribute("quanlityProductsCart", quanlityProductsCart);
		model.addAttribute("isSearch", "isSearch");
		model.addAttribute("listP", productService.searchByName(txtSearch, index));
		model.addAttribute("listC", categoryService.getAllCategory());
		model.addAttribute("txtS", txtSearch);
		model.addAttribute("endPage", endPage);
		model.addAttribute("tagPage", index);

		return "customer/Home";
	}

	@RequestMapping(value = "productDetail")
	public String productDetail(Model model, HttpServletRequest request) {
		String id = request.getParameter("pid");
		List<Product> list = new ArrayList<>();
		List<Integer> quanlityProductsCart = new ArrayList<>();
		Cookie arr[] = request.getCookies();
		if (arr != null) {
			for (Cookie o : arr) {
				if (o.getName().equals("id")) {
					if (o.getValue() != "") {
						String txt[] = o.getValue().split("a");
						for (String s : txt) {
							String tx[] = s.split("w");
							list.add(productService.findByProductId(Integer.parseInt(tx[0])));
							quanlityProductsCart.add(Integer.parseInt(tx[1]));
						}
					}

				}
			}
		}

		int numberProductIntoCart = list.size();
		Product p = productService.findByProductId(Integer.parseInt(id));
		model.addAttribute("detail", p);
		model.addAttribute("productsIntoCart", list);
		model.addAttribute("numberProductIntoCart", numberProductIntoCart);
		model.addAttribute("quanlityProductsCart", quanlityProductsCart);
		return "customer/Detail";
	}

	@RequestMapping(value = "cartControl")
	public String goToCart(Model model, HttpServletRequest request) {
		String pid = request.getParameter("pid");
		List<Product> list = new ArrayList<>();
		List<Integer> quanlityProductsCart = new ArrayList<>();
		Cookie arr[] = request.getCookies();
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
				if (o.getValue() != "") {
					String txt[] = o.getValue().split("a");
					for (String s : txt) {
						String tx[] = s.split("w");
						list.add(productService.findByProductId(Integer.parseInt(tx[0])));
						quanlityProductsCart.add(Integer.parseInt(tx[1]));
					}
				}
			}
		}
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		int total = 0;
		int index = 0;
		for (Product o : list) {
			total = total + quanlityProductsCart.get(index) * o.getPriceCurrent();
			index++;
		}
		int sum = (int)(1.1 * total);
		
		String totalString = formatter.format(total);
		String vatString = formatter.format(0.1 * total);
		String sumString = formatter.format(sum);
		
		int numberProductIntoCart = list.size();
		model.addAttribute("total", totalString.replace(',', '.'));
		model.addAttribute("vat", vatString.replace(',', '.'));
		model.addAttribute("sum", sumString.replace(',', '.'));
		model.addAttribute("productsIntoCart", list);
		if (pid != null) {
			model.addAttribute("pid", pid);
		}
		model.addAttribute("numberProductIntoCart", numberProductIntoCart);
		model.addAttribute("quanlityProductsCart", quanlityProductsCart);

		return "customer/Cart";
	}

	@RequestMapping(value = "addToCart")
	public void addToCart(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String pid = request.getParameter("productId");
		String quanlity = request.getParameter("quanlity");
		boolean checkExistProduct = false;
		int numberProduct = 0;
		Cookie arr[] = request.getCookies();
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
				if (o.getValue() != "") {
					String txt[] = o.getValue().split("a");
					String cookieChange = "";
					for (String s : txt) {
						String tx[] = s.split("w");
						if(tx[0].equals(pid)) {
							checkExistProduct = true;
							numberProduct = (Integer.parseInt(tx[1])+Integer.parseInt(quanlity));
							tx[1] = numberProduct+"";
						}
						if(!"".equals(cookieChange)) {
							cookieChange +="a"+tx[0]+"w"+tx[1];
						}else {
							cookieChange+=tx[0]+"w"+tx[1];
						}
						
					}
					Cookie c = new Cookie("id", "");
					c.setValue(cookieChange);
					c.setMaxAge(60 * 60 * 24);
					c.setPath("/");
					response.addCookie(c);	
				}
			}
		}
		PrintWriter out = response.getWriter();
		if(!checkExistProduct) {
			Product product = productService.findByProductId(Integer.parseInt(pid));
			out.println("<li class=\"header__cart-item\">\n" + "                                        <img src=\""
					+ product.getUrl() + "\" alt=\"" + product.getDescription() + "\" class=\"header__cart-img\">\n"
					+ "                                        <div class=\"header__cart-item-info\">\n"
					+ "                                            <div class=\"header__cart-item-head\">\n"
					+ "                                                <h5 class=\"header__cart-item-name\">"
					+ product.getDescription() + "</h5>\n"
					+ "                                                <div class=\"header__cart-item-price-wrap\">\n"
					+ "                                                    <span class=\"header__cart-item-price\">"
					+ product.getPriceOld() / 1000 + ".000đ</span>\n"
					+ "                                                    <span class=\"header__cart-item-multiply\">x</span>\n"
					+ "                                                    <span id=quanlity"+product.getProductId()+" class=\"header__cart-item-qnt\">"
					+ quanlity + "</span>\n" + "                                                </div>\n"
					+ "                                            </div>\n" + "\n"
					+ "                                            <div class=\"header__cart-item-body\">\n"
					+ "                                                <span class=\"header__cart-item-description\">\n"
					+ "                                                    Phân chia loại sản phẩm Phân chia loại sản phẩm Phân chia loại sản phẩm\n"
					+ "                                                </span>\n"
					+ "                                                <span onclick=\"deleteFromCart("
					+ product.getProductId() + ");changeCookieCartDelete(" + product.getProductId()
					+ ");changeNumberCartDelete();checkHtmlCartDelete();\" class=\"header__cart-item-del\">\n"
					+ "                                                    Xóa\n"
					+ "                                                </span>\n"
					+ "                                            </div>\n"
					+ "                                        </div>\n" + "                                    </li>");
		} else {
			out.print(numberProduct);
		}
		
	}

	@RequestMapping(value = "removeProductCart")
	public void removeProductCart(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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
			String tx[] = ids[i].split("w");
			if (!tx[0].equals(pid)) {
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
	public void deleteFromCart(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String pid = request.getParameter("productId");

		List<Product> list = new ArrayList<>();
		List<Integer> quanlityProductsCart = new ArrayList<>();
		Cookie arr[] = request.getCookies();
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
				if (o.getValue() != "") {
					String txt[] = o.getValue().split("a");
					for (String s : txt) {
						String tx[] = s.split("w");
						if (!pid.equals(tx[0])) {
							list.add(productService.findByProductId(Integer.parseInt(tx[0])));
							quanlityProductsCart.add(Integer.parseInt(tx[1]));
						}
					}
				}
			}
		}

		PrintWriter out = response.getWriter();
		String outPrint = "";
		int index = -1;
		for (Product p : list) {
			outPrint += "<li class=\"header__cart-item\">\n" + "                                        <img src=\""
					+ p.getUrl() + "\" alt=\"" + p.getDescription() + "\" class=\"header__cart-img\">\n"
					+ "                                        <div class=\"header__cart-item-info\">\n"
					+ "                                            <div class=\"header__cart-item-head\">\n"
					+ "                                                <h5 class=\"header__cart-item-name\">"
					+ p.getDescription() + "</h5>\n"
					+ "                                                <div class=\"header__cart-item-price-wrap\">\n"
					+ "                                                    <span class=\"header__cart-item-price\">"
					+ p.getPriceOld() / 1000 + ".000đ</span>\n"
					+ "                                                    <span class=\"header__cart-item-multiply\">x</span>\n"
					+ "                                                    <span class=\"header__cart-item-qnt\">"
					+ quanlityProductsCart.get(++index) + "</span>\n"
					+ "                                                </div>\n"
					+ "                                            </div>\n" + "\n"
					+ "                                            <div class=\"header__cart-item-body\">\n"
					+ "                                                <span class=\"header__cart-item-description\">\n"
					+ "                                                    Phân chia loại sản phẩm Phân chia loại sản phẩm Phân chia loại sản phẩm\n"
					+ "                                                </span>\n"
					+ "                                                <span onclick=\"deleteFromCart("
					+ p.getProductId() + ");changeCookieCartDelete(" + p.getProductId()
					+ ");changeNumberCartDelete();checkHtmlCartDelete()\" class=\"header__cart-item-del\">\n"
					+ "                                                    Xóa\n"
					+ "                                                </span>\n"
					+ "                                            </div>\n"
					+ "                                        </div>\n"
					+ "                                    </li>\n";
		}
		out.println(outPrint);
	}

	@RequestMapping(value = "changeCookieCart")
	public void changeCookieCart(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String pid = request.getParameter("productId");
		String quanlity = request.getParameter("quanlity");
		boolean checkExistProduct = false;
		int numberProduct = 0;
		Cookie arr[] = request.getCookies();
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
				if (o.getValue() != "") {
					String txt[] = o.getValue().split("a");
					String cookieChange = "";
					for (String s : txt) {
						String tx[] = s.split("w");
						if(tx[0].equals(pid)) {
							checkExistProduct = true;
							numberProduct = (Integer.parseInt(tx[1])+Integer.parseInt(quanlity));
							tx[1] = numberProduct+"";
						}
						if(!"".equals(cookieChange)) {
							cookieChange +="a"+tx[0]+"w"+tx[1];
						}else {
							cookieChange+=tx[0]+"w"+tx[1];
						}						
					}
					PrintWriter out = response.getWriter();
					if(checkExistProduct) {
						out.print(""+cookieChange);
					}else {
						out.print(""+cookieChange+"a"+pid+"w"+quanlity);
					}
					
				} else {
					PrintWriter out = response.getWriter();
					out.print(pid+"w"+quanlity);
				}
			}
		}
		


	}

	@RequestMapping(value = "changeCookieCartDelete")
	public void changeCookieCartDelete(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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
		for (int i = 0; i < array.length; i++) {
			String tx[] = array[i].split("w");
			if (tx[0].equals(pid)) {
				array[i] = "";
			}
		}
		String cookie = "";
		int length = array.length;
		if (length == 1) {
			cookie = "";
		} else if (length == 2) {
			for (int j = 0; j < length; j++) {
				if (array[j] != "") {
					cookie = array[j];
				}
			}
		} else {
			for (int j = 0; j < length; j++) {
				if (!"".equals(array[j])) {
					if (j == length - 1) {
						cookie += array[j];
					} else {
						cookie += array[j] + "a";
					}
				}
			}
			if(array[length-1] == "") {
				int lengthcookie = cookie.length();
				cookie = cookie.substring(0, lengthcookie-1);
			}
		}

		PrintWriter out = response.getWriter();
		out.println("" + cookie);
	}

	@RequestMapping(value = "changeNumberCart")
	public void changeNumberCart(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String pid = request.getParameter("productId");
		List<Product> productsCart = new ArrayList<>();
		Cookie arr[] = request.getCookies();
		boolean check = false;
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
				if (o.getValue() != "") {
					String txt[] = o.getValue().split("a");
					for (String s : txt) {
						String tx[] = s.split("w");
						if(tx[0].equals(pid)) {
							check = true;
						}
						productsCart.add(productService.findByProductId(Integer.parseInt(tx[0])));
					}
				}

			}
		}
		PrintWriter out = response.getWriter();
		if(check) {
			out.println("" + productsCart.size());
		} else {
			int numberProductIntoCart = productsCart.size() + 1;
			out.println("" + numberProductIntoCart);
		}	
	}

	@RequestMapping(value = "changeNumberCartDelete")
	public void changeNumberCartDelete(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		List<Product> productsCart = new ArrayList<>();
		Cookie arr[] = request.getCookies();
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
				if (o.getValue() != "") {
					String txt[] = o.getValue().split("a");
					for (String s : txt) {
						String tx[] = s.split("w");
						productsCart.add(productService.findByProductId(Integer.parseInt(tx[0])));
					}
				}

			}
		}
		int numberProductIntoCart = productsCart.size() - 1;
		PrintWriter out = response.getWriter();
		out.println("" + numberProductIntoCart);
	}

	@RequestMapping(value = "checkNoCart")
	public void checkNoCart(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		boolean check = false;
		Cookie arr[] = request.getCookies();
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
//	                check = Integer.parseInt(o.getValue())==0;
				check = "".equals(o.getValue());
			}
		}
		PrintWriter out = response.getWriter();
		if (check) {
			out.println("true");
		} else {
			out.println("false");
		}
	}

	@RequestMapping(value = "checkNoCartDelete")
	public void checkNoCartDelete(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		boolean check = false;
		Cookie arr[] = request.getCookies();
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
				String tx[] = o.getValue().split("a");
				if (tx.length == 1) {
					check = true;
					break;
				}
			}
		}
			PrintWriter out = response.getWriter();
			if (check) {
				out.println("true");
			} else {
				out.println("false");
			}

	}
	
	@RequestMapping(value = "changeQuanlityForProductIntoCart")
	public void changeQuanlityForProductIntoCart(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String productId = request.getParameter("productId");
		String quanlity = request.getParameter("quanlity");
		List<Product> list = new ArrayList<>();
		List<Integer> quanlityProductsCart = new ArrayList<>();
		Cookie arr[] = request.getCookies();
		for (Cookie o : arr) {
			if (o.getName().equals("id")) {
				if (o.getValue() != "") {
					String cookieChange = "";
					String txt[] = o.getValue().split("a");
					for (String s : txt) {
						String tx[] = s.split("w");
						if (tx[0].equals(productId)) {
							tx[1]=quanlity;
						}
						if(!"".equals(cookieChange)) {
							cookieChange += "a"+tx[0]+"w"+tx[1];
						}else {
							cookieChange = tx[0]+"w"+tx[1];
						}
						list.add(productService.findByProductId(Integer.parseInt(tx[0])));
						quanlityProductsCart.add(Integer.parseInt(tx[1]));
					}
					
					Cookie c = new Cookie("id", "");
					c.setValue(cookieChange);
					c.setMaxAge(60 * 60 * 24);
					c.setPath("/");
					response.addCookie(c);			
				}
			}
		}
		double total = 0;
		int index = 0;
		for (Product o : list) {
			total = total + quanlityProductsCart.get(index) * o.getPriceCurrent();
			index++;
		}
		int sum = (int)(1.1 * total);
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		String totalString = formatter.format(total);
		String vatString = formatter.format(0.1 * total);
		String sumString = formatter.format(sum);
		
		PrintWriter out = response.getWriter();
		out.println("<div\r\n"
				+ "								class=\"bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold\">Thành\r\n"
				+ "								tiền</div>\r\n"
				+ "							<div class=\"p-4\">\r\n"
				+ "								<ul class=\"list-unstyled mb-4\">\r\n"
				+ "									<li class=\"d-flex justify-content-between py-3 border-bottom\"><strong\r\n"
				+ "										class=\"text-muted\">Tổng tiền hàng</strong><strong>"+totalString.replace(',', '.')+"₫</strong></li>\r\n"
				+ "									<li class=\"d-flex justify-content-between py-3 border-bottom\"><strong\r\n"
				+ "										class=\"text-muted\">Phí vận chuyển</strong><strong>Free\r\n"
				+ "											ship</strong></li>\r\n"
				+ "									<li class=\"d-flex justify-content-between py-3 border-bottom\"><strong\r\n"
				+ "										class=\"text-muted\">VAT</strong><strong>"+vatString.replace(',', '.')+"₫</strong></li>\r\n"
				+ "									<li class=\"d-flex justify-content-between py-3 border-bottom\"><strong\r\n"
				+ "										class=\"text-muted\">Tổng thanh toán</strong>\r\n"
				+ "										<h5 class=\"font-weight-bold\">"+sumString.replace(',', '.')+"₫</h5></li>\r\n"
				+ "								</ul>\r\n"
				+ "								<a href=\"order\" class=\"btn btn-dark rounded-pill py-2 btn-block\">Mua\r\n"
				+ "									hàng</a>\r\n"
				+ "							</div>\r\n");
	}
}
