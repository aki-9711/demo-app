package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductDao;
import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
  private final ProductDao productDao;

  public ProductController(ProductDao productDao){
    this.productDao = productDao;
  }

  @GetMapping("/product/form")
  public String showForm(Model model){
    model.addAttribute("product", new Product());
    return "product_form";
  }

  @PostMapping("/product/new")
  public String insert(@ModelAttribute Product product){
    productDao.insertProduct(product);
    return "redirect:/products"; // 登録後の遷移先（一覧ページをあとで作る）
  }

}
