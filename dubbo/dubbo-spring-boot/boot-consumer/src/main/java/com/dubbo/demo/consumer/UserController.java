package com.dubbo.demo.consumer;//package com.dubbo.demo.consumer;
//
//
//import com.dubbo.demo.common.entity.SysUser;
//import com.dubbo.demo.common.service.UserService;
//import com.alibaba.dubbo.config.annotation.Reference;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * @author LIUYH
// */
//@RestController
//public class UserController {
//
////    @Reference(version = "1.0")
////    private UserService userService;
////
////    @Reference(version = "1.1")
////    private UserService userServiceNew;
//
//    @Reference(group = "weixin")
//    private UserService weixin1;
//
//    @Reference(group = "aliPay")
//    private UserService aliPay2;
////
////    @GetMapping
////    public List<SysUser> findAll(){
////        return userService.findAll();
////    }
////
////    @GetMapping("q")
////    public List<SysUser> findAllNew(){
////        return userServiceNew.findAll();
////    }
//
//
//    @GetMapping("pay")
//    public String pay(String pay) {
//        if ("weixin".equals(pay)) {
//            return weixin1.pay();
//        }
//        if ("aliPay".equals(pay)){
//            return aliPay2.pay();
//        }
//        return "";
//    }
//}
