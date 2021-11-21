package com.laioffer.onlineOrder.service;

import com.laioffer.onlineOrder.dao.CustomerDao;
import com.laioffer.onlineOrder.entity.Cart;
import com.laioffer.onlineOrder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
//    Used for dependency injection
    @Autowired
    private CustomerDao customerDao;

    public void signUp(Customer customer) {
        // Customer 注册后分配给他一个手推车
        Cart cart = new Cart();
        customer.setCart(cart);

        customer.setEnabled(true);  // 表示用户现在变为 active 的状态
        customerDao.signUp(customer);   //在 DB 中留下记录
    }

    // 保存一个缓存
    public Customer getCustomer(String email) {

        return customerDao.getCustomer(email);
    }
}
