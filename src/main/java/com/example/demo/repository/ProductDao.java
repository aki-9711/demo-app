package com.example.demo.repository;

import com.example.demo.model.Product;
import java.nio.file.Files;
import java.nio.file.Path;
import org.hibernate.query.derived.AnonymousTupleBasicEntityIdentifierMapping;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
  private final JdbcTemplate jdbcTemplate;

  public ProductDao(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }

  public void insertProduct(Product product){
    try{
      String sql = Files.readString(Path.of(
         new ClassPathResource("sql/insert_product.sql").getURI()
      ));
      jdbcTemplate.update(sql, product.getName(), product.getPrice());
    } catch (Exception e){
      throw new RuntimeException("商品登録に失敗しました", e);
    }


  }
}
