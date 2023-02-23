package org.project.omwp.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.project.omwp.dto.MemberDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Builder
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

//  닉네임
    @Column(name = "user_name", nullable = false)
    private String userName;

//  이메일
    @Column(name = "user_email", nullable = false)
    private String userEmail;

//  전화번호
    @Column(name = "user_phone", nullable = false)
    private String userPhone;

//  비밀번호
    @Column(name = "user_pw", nullable = false)
    private String userPw;

//  회원구분 : member(기본값), admin(관리자)
    @Column(name = "user_role", nullable = false)
    private String userRole;

//  가입일
    @CreationTimestamp
    @Column(name = "user_create", updatable = false)
    private LocalDateTime userCreate;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WishEntity> wishEntities = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderlistEntity> orderlistEntities = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReviewEntity> reviewEntities = new ArrayList<>();


    public static MemberEntity toMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setUserName(memberDto.getUserName());
        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(memberDto.getUserPw());
        memberEntity.setUserPhone(memberDto.getUserPhone());
        memberEntity.setUserRole(memberDto.getUserRole());

        return memberEntity;
    }

    public static MemberEntity toMemberEntityUpdate(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setUserId(memberDto.getUserId());
        memberEntity.setUserName(memberDto.getUserName());
        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(memberDto.getUserPw());
        memberEntity.setUserPhone(memberDto.getUserPhone());
        memberEntity.setUserRole(memberDto.getUserRole());

        return memberEntity;
    }
}
