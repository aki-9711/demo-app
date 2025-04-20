package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FormData {

  @NotBlank(message = "名前を入力してください")
  private String name;

  @NotBlank(message = "メールアドレスを正しく入力してください")
  @Email(message = "正しいメールアドレス形式で入力してください")
  private  String email;

  @Min(value = 0, message = "年齢は0歳以上で入力ください")
  @NotNull(message = "年齢を入力してください")
  private Integer age;

  //ゲッター・セッター
  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
