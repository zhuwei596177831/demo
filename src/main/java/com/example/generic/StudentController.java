package com.example.generic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author 朱伟伟
 * @date 2020-12-27 12:13:26
 * @description
 */
@RestController
public class StudentController {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @GetMapping("/testTransactional")
    public Result<Integer> testTransactional() {
        Integer num = studentService.updateById();
        return Result.ok(num);
    }

    @GetMapping("/insertStudent")
    public Result<Integer> insertStudent(Student student) {
        int insert = studentMapper.insert(student);
        return Result.ok(insert);
    }

    @GetMapping("/getStudent")
    public Result<Student> getStudent() {
        Student student = studentMapper.selectList(null).get(0);
        return Result.ok(student);
    }

    @GetMapping("/selectList")
    public Result<ArrayData<Student>> selectList() {
        List<Student> students = studentMapper.selectList(null);
        return Result.ok(students);
    }

    @GetMapping("/getList")
    public Result<ArrayData<Student>> getMybatisList() {
//        com.github.pagehelper.Page<Student> page = PageHelper.startPage(1, 1);
//        PageHelper.offsetPage(0,1);
        List<Student> mybatisList = studentMapper.getList();
        return Result.ok(mybatisList);
    }

    @GetMapping("/getMybatisPageList")
    public Result<ArrayData<Student>> getMybatisPageList() {
        List<Student> mybatisList = studentMapper.getMybatisPageList("", 1, 1);
        return Result.ok(mybatisList);
    }

    @GetMapping("/getMybatisPlusPageList")
    public Result<ArrayData<Student>> getMybatisPlusPageList() {
        Page<Student> page = new Page<>(1, 1);
        page = studentMapper.getMybatisPlusPageList("", page);
        return Result.ok(page);
    }

    @GetMapping("/getStudentListMap")
    public Result<ArrayData<Map<String, String>>> getStudentListMap() {
        List<Map<String, String>> mapList = new LinkedList<>();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", "531231232");
        map.put("name", "朱伟伟");
        map.put("email", "512222");
        mapList.add(map);
        return Result.ok(mapList);
    }

    /**
     * @author: 朱伟伟
     * @date: 2020-12-31 13:51
     * @description:
     * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#nameLookup
     **/
    @GetMapping("/testNameLookup")
    public Result getNameLookup() {
        return Result.ok();
    }

    @GetMapping(name = "nameLookup", path = "/getNameLookup")
    public Result getNameLookup(String name) {
        return Result.ok(name);
    }

}
