package com.kh.app.web;

import com.kh.app.web.form.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/test/members")
public class TestController {

  //http://localhost:9080/test/members/{id}/{age}
  @ResponseBody
  @GetMapping("/{id}/{age}")
  public String case1 (@PathVariable("id") String id, @PathVariable Long age){
    log.info("id={},age={}",id,age);
    return id+":"+age;
  }

  //http://localhost:9080/test/members?id=hong&age=30
  @ResponseBody
  @GetMapping
  public String case2 (@RequestParam("id") String id, @RequestParam Long age){
    log.info("id={},age={}",id,age);
    return id+":"+age;
  }

  //http://localhost:9080/test/members/header
  @ResponseBody
  @GetMapping("header")
  public String case3 (@RequestHeader("id") String id, @RequestHeader Long age){
    log.info("id={},age={}",id,age);
    return id+":"+age;
  }

  // form, post요청 @RequestParam
  @ResponseBody
  @PostMapping
  public String case4 (@RequestParam("id") String id, @RequestParam Long age){
    log.info("id={},age={}",id,age);
    return id+":"+age;
  }

  // form, post요청 @ModelAttribute
  @ResponseBody
  @PostMapping("/object")
  public String case5 (@ModelAttribute Member member){
    log.info("id={},age={}",member.getId(),member.getAge());
    return member.getId()+":"+member.getAge();
  }

  // form, post요청 josn => @RequestBody
  @ResponseBody
  @PostMapping("/json")
  public String case6 (@RequestBody Member member){
    log.info("id={},age={}",member.getId(),member.getAge());
    return member.getId()+":"+member.getAge();
  }

  // form, post요청 josn => @RequestBody
  // 응답, json
  @ResponseBody
  @PostMapping("/json2")
  public Member case7 (@RequestBody Member member){
    log.info("id={},age={}",member.getId(),member.getAge());
    return member;
  }

  // form, post요청 josn => @RequestBody
  // 응답, json
  @ResponseBody
  @PostMapping("/json3")
  public RestResponse<Object> case8 (@RequestBody Member member){
    log.info("id={},age={}",member.getId(),member.getAge());
    RestResponse<Object> res = null;
    res = RestResponse.createRestResponse("00","성공",member);
    return res;
  }

  //2개 이상의 요청 url 받기
  @ResponseBody
  @GetMapping({"/case1","/case2","/case3"})
  public String case9(){
    return "ok";
  }

  @PostMapping("/object2")
  public String case10 (@ModelAttribute Member member, Model model){
    log.info("id={},age={}",member.getId(),member.getAge());
    model.addAttribute("var1","울산");
    model.addAttribute("var2","KH");
    model.addAttribute("var3","정보교육원");

    List<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    list.add("c");
    model.addAttribute("list",list);

    return "test/view";
  }
}
