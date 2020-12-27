package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.generic.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 朱伟伟
 * @date 2020-12-27 13:51:55
 * @description
 */
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select * from student")
    List<Student> getList();

    @Select("<script>" +
            " select * from student" +
            "<where>" +
            "<if test=\"name != null and name != ''\">" +
            " and name = #{name}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Student> getMybatisPageList(@Param("name") String name, Integer pageNum, Integer pageSize);

    @Select("<script>" +
            " select * from student" +
            "<where>" +
            "<if test=\"name != null and name != ''\">" +
            " and name = #{name}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<Student> getMybatisPlusPageList(@Param("name") String name, Page<Student> page);
}
