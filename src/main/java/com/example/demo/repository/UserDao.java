package com.example.demo.repository;

import com.example.demo.model.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

  private final JdbcTemplate jdbcTemplate;

  public UserDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void insetUser(User user) {
    try {
      // resources/sql/insert_user.sql を読み込む
      String sql = Files.readString(Path.of(
          new ClassPathResource("sql/insert_user.sql").getURI()
      ));
      jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAge());
    } catch (IOException e) {
      throw new RuntimeException("SQLファイル読み込み失敗", e);
    }
  }

  public List<User> findAll() {
    try {
      String sql = Files.readString(Path.of(
          new ClassPathResource("sql/select_all_users.sql").getURI()
      ));
      return jdbcTemplate.query(sql, (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setAge(rs.getInt("age"));
        return user;
      });
    } catch (IOException e) {
      throw new RuntimeException("SQLファイル読み込み失敗", e);
    }


  }
}
