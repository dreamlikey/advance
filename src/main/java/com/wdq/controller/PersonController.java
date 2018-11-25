package com.wdq.controller;

import com.wdq.mq.MqProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Restful
 * @Author: wudq
 * @Date: 2018/10/31
 */
@RestController
public class PersonController {

    @Autowired
    private MqProvider mqProvider;

    @GetMapping(value = "/person/{id}",
                produces = "application/xml")//响应数据格式，默认json
    public Person getPerson(@PathVariable Long id,
                            @RequestParam(required = false) String name) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        mqProvider.send();
        return person;
    }

    /**
     * 新增
     * 非幂等
     * @Param
     * @return
    */
    @PostMapping("/person/post")
    public void post() {

    }

    /**
     * 修改
     * @Param
     * @return
    */
    @PutMapping("/person/put")
    public void put(@RequestParam(required = true) Person person) {

    }


    /**
     * 删除
     * @Param
     * @return
    */
    @DeleteMapping("/person/del")
    public void delete() {

    }
}

class Person {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}