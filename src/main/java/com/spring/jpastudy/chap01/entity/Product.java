package com.spring.jpastudy.chap01.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

//entity 클래스를 만들면 entity.Product 를 바라보고 디비를 만든다!!!!!
// => 전에는 디비를 만들고 entity 를 만들었는데 !!! 이번에는 반대로 !!!

@Getter
@ToString
@EqualsAndHashCode(of = "id") //필드 명을 쓴다
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_product") //디비에 있는 table 이름 변경
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY : 마리아디비, SEQUENCE : 오라클
    @Column(name="prod_id") // 디비에 있는 컬럼명 변경
    private Long id; //PK

    @Column(name="prod_nm", length = 30, nullable = false)
    private String name; //상품명

    @Column(name="price") // 디비에 있는 컬럼명 변경
    private int price; //상품 가격

    @Column(nullable = false) //NOY NULL 임
    @Enumerated(EnumType.STRING) // 왱만하믄 string 으로 바꿔 써라
    private Category category;

    @CreationTimestamp // INSERT 시 자동으로 서버시간 저장
    @Column(updatable = false) // 수정 불가, NOY NULL 임
    private LocalDateTime createdAt; // 상품 등록 시작 , 케멀 케이스로 써도 자동으로 스네이크 케이스로 변경

    @UpdateTimestamp // UPDATE 문 실행 시 자동으로 시간이 저장
    private LocalDateTime updatedAt; // 상품 수정 시간

    // 데이터 베이스에는 저장 안하고 클래스 내부에서만 사용할 필드
    @Transient //디비에서는 사용 하지 않고 내부에서만 사용한다
    private String nickName;

    public enum Category {
        FOOD, FASHION, ELECTRONIC
    }

}
