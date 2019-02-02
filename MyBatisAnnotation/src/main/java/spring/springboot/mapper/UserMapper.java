package spring.springboot.mapper;

import org.apache.ibatis.annotations.*;
import spring.springboot.entity.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from user where name=#{name}")
    User findByName(@Param("name") String name);

    @Results({@Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")})
    @Select("select name,age from user")
    List<User> findAll();

    @Insert("insert into user(name,age) values(#{name},#{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Update("update user set age=#{age} where name=#{name}")
    void update(User user);

    @Delete("delete from user where id = #{id}")
    void delete(User user);

    @Insert("insert into user(name,age) values(#{name},#{age})")
    void insertByUser(User user);

    @Insert("insert into user(name,age) values(#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    void insertByMap(Map<String, Object> map);
}
