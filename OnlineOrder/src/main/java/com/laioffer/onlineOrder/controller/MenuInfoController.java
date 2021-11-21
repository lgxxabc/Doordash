package com.laioffer.onlineOrder.controller;

import com.laioffer.onlineOrder.entity.MenuItem;
import com.laioffer.onlineOrder.entity.Restaurant;
import com.laioffer.onlineOrder.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

// 本 class 用来定义 REST API

// To load restaurants and corresponding menu information from BD
// @PathVariable:  can be used to handle template variables in the request URI mapping.
// For example:  get menu for a specific restaurant
// http://localhost:8080/restaurant/1/menu
@Controller
public class MenuInfoController {
    @Autowired
    private MenuInfoService menuInfoService;

    //    前端传来 GET REQUEST 想要读取 Restaurant 的信息
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants() {
//        return new ArrayList<>();
        return menuInfoService.getRestaurants();
    }

//    从前端传来了 GET 请求，读取 Restaurant 信息
    @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
//    Use the @ResponseBody annotation on a method to indicate that the return value
//    should be written straight to the HTTP response body, and it will be automatically convert to json format
    @ResponseBody
//    @PathVariable 用来获取前端传来的 URL 中的信息
    public List<MenuItem> getMenus(@PathVariable(value = "restaurantId") int restaurantId) {
//        return new ArrayList<>();
        return menuInfoService.getAllMenuItem(restaurantId);
    }

}
