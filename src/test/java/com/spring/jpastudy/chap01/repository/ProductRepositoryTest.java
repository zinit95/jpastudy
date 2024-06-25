package com.spring.jpastudy.chap01.repository;

import com.spring.jpastudy.chap01.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static com.spring.jpastudy.chap01.entity.Product.Category.*;

import javax.persistence.Lob;
import javax.persistence.PrePersist;

import java.util.List;
import java.util.Optional;

import static com.spring.jpastudy.chap01.entity.Product.Category.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void  insertBeforeTest(){
        Product p1 = Product.builder()
                .name("아이폰")
                .category(ELECTRONIC)
                .price(2000000)
                .build();
        Product p2 = Product.builder()
                .name("탕수육")
                .category(FOOD)
                .price(20000)
                .build();
        Product p3 = Product.builder()
                .name("구두")
                .category(FASHION)
                .price(300000)
                .build();
        Product p4 = Product.builder()
                .name("주먹밥")
                .category(FOOD)
                .price(1500)
                .build();

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
    }


    // =============================================================================================


    /*
     //@Transactional, @Rollback -- 맨 위에 이거 넣으면 안됨, @SpringBootTest 이것만 넣어야 됨
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


    // =============================================================================================


    // 새로운 데이터를 넣으면 기존 데이터 사라짐 왜 ? 드롭을 하니깐 ,
    //만역 기존 데이터도 유지 하고 싶으면 application.yml 에 가서 ddl-auto: create 를 update 로 변경하면 된다
    @Test
    @DisplayName("상품을 데이터베이스에 저장한더")
    void saveTest () {
        //given
        Product product = Product.builder()
                .name("떡볶이")
//                .price(95000)
//                .category(Product.Category.FASHION)
                .build();
        //when
        //insert 후 저장 된 데이터의 객체를 반환
        Product saved = productRepository.save(product);
        //then
        assertNotNull(saved); // 성공은 했지만 실제로 데이터가 저장 되지 않음 
    }


    // =============================================================================================


    @Test
    @DisplayName("1번 상품을 삭제한다")
    void deleteTest() {
        //given
        Long id = 1L;
        //when
        productRepository.deleteById(id);
        //then
        Product foundProduct = productRepository.findById(id)
                .orElse(null); //id 를 가져 올 수 없으면 null 을 가져올 것이다.
        assertNull(foundProduct);
    }


    // =============================================================================================


    @Test
    @DisplayName("3번 상품을 단일조회하면 그 상품명이 구두이다.")
    void findOneTest() {
        //given
        Long id = 3L;
        //when
        Product foundProduct = productRepository.findById(id).orElse(null);
        //then
        assertEquals("구두", foundProduct.getName());

        System.out.println("\n\n\nfoundProduct = " + foundProduct + "\n\n\n\n");
    }


    // =============================================================================================


    @Test
    @DisplayName("상품을 전체조회하면 상품의 총 개수가 4개이다.")
    void findAllTest() {
        //given

        //when
        List<Product> productList = productRepository.findAll();

        //then
        System.out.println("\n\n\n");

        productList.forEach(System.out::println);

        System.out.println("\n\n\n");

        assertEquals(4, productList.size());
    }


    // =============================================================================================


    @Test
    @DisplayName("2번 상품의 이름과 카테고리를 수정한다")
    void modifyTest() {
        //given
        Long id = 2L;
        String newName = "청소기";
        Product.Category newCategory = ELECTRONIC;
        //when

        /*
            jpa 에서는 수정메서드를 따로 제공하지 않습니다
            단일 조회를 수행항 수 setter 를 통해 값을 변경하고
            다시 save 를 하면 INSERT 대신에 UPDATE 문이 나갑니다.
        */

        Product product = productRepository.findById(id).orElse(null);
        product.setName(newName);
        product.setCategory(newCategory);

        Product saved = productRepository.save(product);
        assertEquals(newName, saved.getName()); //save 된 이름이 new name 일 것이다.
        //then
    }


    // =============================================================================================






}