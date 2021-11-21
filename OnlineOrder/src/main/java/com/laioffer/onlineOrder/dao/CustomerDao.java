package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.Authorities;
import com.laioffer.onlineOrder.entity.Customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// DAO: Data Access Object
// 用来与数据库进行交互，增删改查（需要用 Session interface 实现增删改查）
// Session interface 又是由 SessionFactory 创建出来的对象

@Repository
public class CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmail(customer.getEmail());

        Session session = null;

        // Session 对 DB 增删改查, 代替 SQL 语句
        try{
            session = sessionFactory.openSession();     // JDBC Connection
            // beginTransaction 告诉 session 开始工作了
            session.beginTransaction();     // Transaction 保证数据一致性(Save 设计多张表，防止save时某张表出现异常)
            session.save(authorities);      // 在数据库中 save authorities 这个 object(在 DB 中保存了一条记录)
            session.save(customer);
            // 工作完 commit 一下
            session.getTransaction().commit();  // 用 Transaction 来 commit，这样某张表 save 出错时可以 rollback() 回来
        } catch (Exception ex) {
            ex.printStackTrace();   // 打印错误信息
            // 先确认 session 不是 null，的确拿到了 objects
            if (session != null) session.getTransaction().rollback();   // save 出错就回滚 Transaction 之前的状态
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        // 讲 session 在 try 后边创建出来，就不用再写 finally 了，更省事
        try (Session session = sessionFactory.openSession()) {
            // get 相当于 SELECT * FROM Customer WHERE email = 'email'，然后将query结果转成customer这个object
            customer = session.get(Customer.class, email);  // email 时 PK(Primary Key)
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;

//        Session session = null;
//        try {
//            session = sessionFactory.openSession();
//            customer = session.get(Customer.class, email);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return customer;
    }
}
