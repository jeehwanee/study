//유저테이블

package com.school.springboot.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.school.springboot.domain.Role;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*ORM : 자바포함 다른 언어들의 Object를 테이블로 매핑해주는 것.
        나는 오브젝트만 만들면 됨. 그러면 JPA가 테이블을 만들어 줌.
 */


@Data //Getter + Setter
@NoArgsConstructor //빈생성자
@AllArgsConstructor //전체생성자
@Builder //빌더 패턴
/*@DynamicInsert //insert할 때, null인 필드를 제외시키고 insert함. 즉 null값이 안들어가서 default value값으로 넣을 수 있음 (role)
일단 이걸 사용하지 않겠음
이걸 사용할 때에는 private String role; 위에 @ColumnDefault("'user'")를 해줬음 아래 주석처리된거랑 셋트임
*/
@Entity //해당 클래스를 테이블화 시키기 위해 @Entity 어노테이션 사용
//스프링부트가 실행될 때, User 클래스를 읽어서 자동으로 MySQL에 테이블이 생성이 됨
public class Member { //유저오브젝트

    @Id //프라이머리키라는 것을 알려주기 위해 추가

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //넘버링 전략이 프로젝트에서 연결된 DB의 넘버링 전략을 따라한다는 것 => auto_increment를 사용하기 위함
    private int id; // 시퀀스, MySQL에서 auto_increment로 넘버링 하기 위함

    @Column(nullable = false, length=20, unique = true) //아이디가 null이 될 수 없고, 아이디 길이 20으로 제한
    private String username; //아이디               //unique = true를 통해 중복아이디 불가능하게 만듦
    @Column(nullable = false, length=50) //비밀번호도 null 불가, 길이 50 제한 나중에 비밀번호를 해시해서 암호화할것임
    private String password;
    @Column(nullable = false, length=50)
    private String email;

    //@ColumnDefault("'user'") //기본 역할 'user' 문자열로 쓰기 대문에 ' ' 붙여야 함. / 정수형은 필요없음
    //private String role; //Enum을 쓰는 게 좋다. Why? 데이터에 도메인(어떤 범위)을 만들어줄 수 있음.
    //admin, user, manager이 범위를 정할 수 있음. 단 String은 userr 이렇게 오타날수도..
    //Enum을 사용하여 role 정하기, 여기 소속인 model패키지에 RoleType이라는 enum을 만들어준다.
    @Enumerated(EnumType.STRING)
    private Role role; //String으로 하면 오타날 수도 있어서 만들어둔 enum의 RoleType ADMIN, USER 두개만 가능하게 해둠
    //DB는 RoleType이라는 것이 없다. 그래서 @Enumerated(EnumType.STRING)으로 해당 enum이 스트링이라고 알려줘야함

    @CreationTimestamp //시간이 자동으로 입력되는 어노테이션
    private Timestamp createDate; //자바 sql이 들고있는 timestamp사용 , 아이디 생성 시간

}
