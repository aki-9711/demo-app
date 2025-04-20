package com.example.demo.controller;

import com.example.demo.model.FormData;
import com.example.demo.model.User;
import com.example.demo.repository.UserDao;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

  private final UserDao userDao;

  public HelloController(UserDao userDao) {
    this.userDao = userDao;
  }

  @GetMapping("/")
  public String showTopPage() {
    return "index"; // templates/index.html を表示
  }

  @GetMapping("/form")
  public String showForm(Model model) {
    model.addAttribute("formData", new FormData());//空のフォームデータ
    return "form";  //templates/form.htmlを表示する
  }

  @PostMapping("/greet")
  public String greet(@ModelAttribute("formData") @Valid FormData formData, BindingResult result, Model model) {
    if (result.hasErrors()){
      return "form"; //エラーがあったらform.htmlに戻す
    }

    User user = new User();
    user.setName(formData.getName());
    user.setEmail(formData.getEmail());
    user.setAge(formData.getAge());

    userDao.insetUser(user); // ← 外だしSQLで保存！

    model.addAttribute("userName", formData.getName());
    model.addAttribute("email", formData.getEmail());
    model.addAttribute("age", formData.getAge());
    return "greeting"; // templates/greeting.htmlを表示する
  }

  @GetMapping("/users")
  public String listUsers(Model model) {
    List<User> userList = userDao.findAll();
    model.addAttribute("userList", userList);
    return "users"; // ← users.html を表示する
  }

}
