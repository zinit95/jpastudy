package com.spring.jpastudy.chap01.repository;

import com.spring.jpastudy.chap01.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;



    /*
    // 실제로 디비에 저장이 되는 코드
    // 새로운 데이터를 넣으면 기존 데이터 사라짐 왜 ? 드롭을 하니깐 ,
    //만역 기존 데이터도 유지 하고 싶으면 application.yml 에 가서 ddl-auto: create 를 update 로 변경하면 된다
    @Test
    @DisplayName("상품을 데이터베이스에 저장한다아아")
    void saveTest2 () {
        //given
        Product product2 = Product.builder()
                .name("참치회")
                .price(55000)
                .category(Product.Category.FOOD)
                .build();
        //when
        productRepository.save(product2);
        //then
    }
    */
    
    // 새로운 데이터를 넣으면 기존 데이터 사라짐 왜 ? 드롭을 하니깐 ,
    //만역 기존 데이터도 유지 하고 싶으면 application.yml 에 가서 ddl-auto: create 를 update 로 변경하면 된다
    @Test
    @DisplayName("상품을 데이터베이스에 저장한더")
    void saveTest () {
        //given
        Product product = Product.builder()
                .name("신발")
                .price(95000)
                .category(Product.Category.FASHION)
                .build();
        //when
        //insert 후 저장 된 데이터의 객체를 반환
        Product saved = productRepository.save(product);
        //then
        assertNotNull(saved); // 성공은 했지만 실제로 데이터가 저장 되지 않음 
    }

}