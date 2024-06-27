package com.spring.jpastudy.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(exclude = "employees")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id; // 부서번호

    @Column(name = "dept_name", nullable = false)
    private String name;

    /*
        - 양방향 매핑은 데이터베이스와 달리 객체지향 시스템에서 가능한 방법으로
        1대 N 관계에서 1쪽에 N 데이터를 포함시킬 수 있는 방법이다.

        - 양방향 매핑에서 1쪽은 상대방 엔터티 갱신에 관여 할 수 없고
           (리스트에서 사원을 지운다고 실제 디비에서 사원이 삭제되지는 않는다는 말)
           단순히 읽기전용 (조회전용)으로만 사용하는 것이다.
        - mappedBy 에는 상대방 엔터티에 @ManyToOne 에 대응되는 필드명을 꼭 적어야 함

        -CascadeType
            * PERSIST : 부모가 갱신되면 자식도 같이 갱신된다
            - 리스트에 자식을 추가하거나 제거하면 데이터베이스에더 반영 된다
            * REMOVE : 부모가 제거되면 작식도 같이 제거 된다.
            - ON DELETE CASCADE
            * ALL : 위에 내용을 전부 포함
     */
    //(mappedBy = "department") 를 적어야 서로 양방향으로 된다, 부서가 1, 사원이 다 1 : 다,
    // 만약 ALL 을 사용하지 않고 REMOVE 만? 사용하고 싶다면 cascade = CascadeType.ALL -> 에서
    //cascade = {CascadeType.PERSIST, CascadeType.REMOVE} 이렇게 중괄호 안에 넣어서 사용하면 된다
    //@OneToMany(mappedBy = "department", orphanRemoval = true, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})

    @Builder.Default
    private List<Employee> employees = new ArrayList<>(); //null 포인트 인셉션 방지를 위해 새로운 배열을 만든다

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee); // 부모에서 자식을 지워버리면
        employee.setDepartment(null);
    }


    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setDepartment(this);
    }

}
