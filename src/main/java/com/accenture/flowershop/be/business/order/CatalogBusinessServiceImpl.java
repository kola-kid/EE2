package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Service("CatalogBusinessServiceImpl")
public class CatalogBusinessServiceImpl implements CatalogBusinessService {

    @Autowired
    private FlowerAccessService flowerAccessService;

    public CatalogBusinessServiceImpl(){
    }

    @Override
    public List<Flower> findAllFlower() {
        return  this.flowerAccessService.findAll();
    }

    @Override
    public Flower findFlower(String nameFlower){
                return   this.flowerAccessService.getFlower(nameFlower);
   }


    @Override
    public boolean updateFlowerList( Cart cart) {

        try {
            for (Flower flower : this.flowerAccessService.findAll()) {

                Integer count = cart.findCountFlowerToCart(flower);
                if(count != null && count >0){
                    flower.setCount(flower.getCount()-count);
                    flowerAccessService.updateFlover(flower);
                }
            }
            return true;
        }catch (Exception e){
            System.out.println("Цветы не свписались со склада!");
            return false;
        }
    }


        @Override
        public List<Flower> findListFlower(String flowerName, Integer min, Integer max){
            if(min == null)
                min = 0;
            if(max == null)
                max = Integer.MAX_VALUE;

            BigDecimal minB = new BigDecimal(min);
            BigDecimal maxB = new BigDecimal(max);


        return this.flowerAccessService.findFlowers(flowerName, minB, maxB);
    }

    @Override
    @Transactional
    public void addCountFlower(int count) {

       for(Flower flower : this.flowerAccessService.findAll()){
           flower.setCount(flower.getCount()+count);
           this.flowerAccessService.updateFlover(flower);
       }
    }

}
