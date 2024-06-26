package com.spring.jpastudy.chap03_page;

import com.spring.jpastudy.chap02.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class StudentPageRepositoryTest {
    @Autowired //주입 받기 
    StudentPageRepository repository;
    
    @BeforeEach
    void bulkInsert(){
        for (int i = 0; i < 147; i++) {
            Student s = Student.builder()
                    .name("김시골" + i)
                    .city("도시" + i)
                    .major("숨쉬기" + i)
                    .build();
            repository.save(s);
        }
    }

    @Test
    @DisplayName("기본적인 페이지 조회 테스트")
    void basicPageTest() {
        //given
        int pageNo = 6;
        int amount = 10;
        
        //페이지 정보 객체를 생성 (Pageable)
        //여기서는 페이지버노가 zero-based 임 : 1페이지는 0으로 취급
        Pageable pageInfo = PageRequest.of(pageNo - 1, amount);

        //when
        Page<Student> students = repository.findAll(pageInfo);

        // 실질적인 데이터 꺼내기
        List<Student> studentList = students.getContent();

        //총 체이지 수
        int totalPage = students.getTotalPages();

        // 총학생수
        long count = students.getTotalElements();

        //then
        System.out.println("\n\n\n\n\n");
        studentList.forEach(System.out::println);
        System.out.println("\n\n\n\n\n");
    }


}