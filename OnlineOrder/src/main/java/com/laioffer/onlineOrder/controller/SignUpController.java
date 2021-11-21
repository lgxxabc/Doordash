package com.laioffer.onlineOrder.controller;

import com.laioffer.onlineOrder.entity.Customer;
import com.laioffer.onlineOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

// Use @Controller to mark a class its role as a web component, so the spring
// mvc will register the methods which annotated the @RequestMapping.
// 标记了 @Controller, 才会被 dispatchServlet 创建出来
// Controller 不负责业务逻辑，只移交来自前端的数据给后端
@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/signup", method=RequestMethod.POST) // URL: value = resource_path from FE, Method: POST
    @ResponseStatus(value=HttpStatus.CREATED)   // 回复201，表示已经 Created 成功
    // Request 底层用了 Jackson，Convert the request body in the http request to a backend object
    public void signUp(@RequestBody Customer customer) {
       // business logic
//        System.out.println(customer.getEmail());    // 用于 Postman signup 功能测试
        customerService.signUp(customer);

    }
}
