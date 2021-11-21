package com.laioffer.onlineOrder.dao;

// Use this class to interact with mysql

import com.laioffer.onlineOrder.entity.MenuItem;
import com.laioffer.onlineOrder.entity.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// To configure a class as Bean in the Spring container
@Repository
public class MenuInfoDao {
    // 3 important interfaces in Hibernate
    //  a. SessionFactory: 获得 Session 对象
    //  b. Session: 通过 Session 来对数据库进行增删改查的操作
    //  c. Transaction: allows the application to define units of work. 保证代码的一致性
    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants() {
        // session 的创建被写在 try() 括号里边，就不用再写 finally 了
        try (Session session = sessionFactory.openSession()) {
            return session.createCriteria(Restaurant.class)
                    // DISTINCT_ROOT_ENTITY: 去重，否则会30个餐厅+30道菜；现在是3个餐厅+30道菜
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // If getAllMenuItem() returns null, then:
        return new ArrayList<>();
    }

    public MenuItem getMenuItem(int menuItemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
