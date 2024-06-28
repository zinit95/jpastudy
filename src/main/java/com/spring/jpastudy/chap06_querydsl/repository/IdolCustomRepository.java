package com.spring.jpastudy.chap06_querydsl.repository;

import com.spring.jpastudy.chap06_querydsl.entity.Idol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IdolCustomRepository {

    //JPA 의 PAGE 인터페이스를 사용
    Page<Idol> findAllByPaging(Pageable pageable);

    // 이름으로 오름차해서 전체조회
    List<Idol> findAllSortedByName2();

    // 그룹명으로 아이돌을 조회
    List<Idol> findByGroupName();
}
