package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.user.CustomerAccessService;
import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.entity.order.Cart;
import com.accenture.flowershop.be.entity.order.CartItem;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
import com.accenture.flowershop.be.entity.order.OrderItem;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@Service("BuyBusinessServiceImpl")
public class BuyBusinessServiceImpl implements BuyBusinessService {

    @Autowired
    private CustomerAccessService customerAccessService;

    @Autowired
    private CatalogBusinessService catalogBusinessService;

    @Autowired
    private OrderAccessService orderAccessService;

    public BuyBusinessServiceImpl(){
    }

    @Override
    public OrderCustomer getOrderCustomer(Integer id) {
        return this.orderAccessService.getOrderCustomer(id);
    }

    @Override
    @Transactional
    public boolean saveOrderCustomer(String userName, Cart cart){
        OrderCustomer orderCustomer = new OrderCustomer();
        User user = this.customerAccessService.getUser(userName);
        Calendar currantDate = GregorianCalendar.getInstance();
        Date finalDate = currantDate.getTime();
        orderCustomer.setUser(user);
        orderCustomer.setSum(cart.discountedSum(user.getDiscount()));
        orderCustomer.setStatus(OrderCustomer.OrderCustomerStatus.CREATED);
        orderCustomer.setDateOrder(finalDate);
        this.orderAccessService.saveOrderCustomer(orderCustomer);
        OrderItem orderItem;
        for (CartItem cartItem : cart.getCart()){
            orderItem = new OrderItem();
            orderItem.setFlower(catalogBusinessService.findFlower(cartItem.getNameFlower()));
            orderItem.setCount(cartItem.getCountFlower());
            orderItem.setOrderCustomer(orderCustomer);
            this.orderAccessService.saveOrderItem(orderItem);
        }
        this.catalogBusinessService.updateFlowerList(cart);
        cart.cartClear();


        return true;
    }

    @Override
    @Transactional
    public boolean editStatuseOrder(Integer orderCustomerId, String userName) {
        OrderCustomer orderCustomer = this.orderAccessService.getOrderCustomer(orderCustomerId);
        User user = this.customerAccessService.getUser(userName);
        if(user.getmoney().compareTo(orderCustomer.getSum()) != -1){
            user.setmoney(user.getmoney().subtract(orderCustomer.getSum()));
            orderCustomer.setStatus(OrderCustomer.OrderCustomerStatus.PAID);
            this.customerAccessService.editCustomer(user);
            this.orderAccessService.updateOrderCustomer(orderCustomer);
            return true;
        }
        else
            return false;
    }

}
