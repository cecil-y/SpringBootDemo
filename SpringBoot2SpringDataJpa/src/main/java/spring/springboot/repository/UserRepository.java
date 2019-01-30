package spring.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    // from 后面的 User 的 U 必须大写
    @Query("from User where name=:name")
    User findUser(@Param("name") String name);
}
