package com.moha.demo.controller;

import com.moha.demo.entity.View;
import com.moha.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/*JSP页面多个同名数据传到controller*/
@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("show")
    public String demo(){
      /*  CheckEmailValidator cev = new CheckEmailValidator();
        cev.setEmail("799845210@qq.com");
        cev.setReplyString("111");
        cev.setStatus(1);
        cev.setCreateTime("2017");
        mongoTemplate.insert(cev,"CheckEmailValidator");
*/
        return "/index";
    }
    @RequestMapping("form")
    public String form() {
        List<String> list = Arrays.asList("test@gmail.com","miss023@gmail.com","dolphin0520@163.com","stardyun@yahoo.com.cn","799845210@qq.com","knight_xiaodai@outlook.com","1060941496@qq.com","emailss@gmail.com","ffafa@gmail.com");
        demoService.ser(list);
        return "/formSubmit";
    }

    @RequestMapping("data")
    @ResponseBody
    public String forms(String data){
        List<String> list = Arrays.asList(data);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "fff",method = RequestMethod.POST)
    public int vs(@RequestBody List<View> views){
        for(View v : views){
            v.getId();
        }
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "fffs",method = RequestMethod.POST)
    public int vs(Integer[] id,String[] name){

        return 1;
    }
    @ResponseBody
    @RequestMapping(value = "fffss",method = RequestMethod.POST)
    public int vs(Integer[] id){

        return 1;
    }


    @ResponseBody
    @RequestMapping(value = "tms",method = RequestMethod.GET)
    public int vss(Integer[] id){
        demoService.ser(null);
        return 1;
    }




}
