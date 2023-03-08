package org.project.omwp.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.project.omwp.costant.Role;
import org.project.omwp.entity.MemberEntity;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDto {

    private Long userId;

    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 맞지 않습니다." )
    private String userEmail;

    @NotBlank(message = "닉네임은 필수 입력 사항입니다.")
    @Pattern(regexp = "[A-Za-z0-9가-힣]{2,}", message = "닉네임 형식이 올바르지 않습니다.")
    private String userName;

    @NotBlank(message = "전화번호는 필수 입력 사항입니다")
    @Pattern(regexp = "[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}", message = "전화번호 형식이 맞지 않습니다.")
    private String userPhone;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    private String userPw;
    private Role userRole;

    @CreationTimestamp
    @Column(name = "member_create", nullable = false, updatable = false)
    private LocalDateTime userCreate;

    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDto = new MemberDto();

        memberDto.setUserId(memberEntity.getUserId());
        memberDto.setUserName(memberEntity.getUserName());
        memberDto.setUserEmail(memberEntity.getUserEmail());
        memberDto.setUserPhone(memberEntity.getUserPhone());
        memberDto.setUserPw(memberEntity.getUserPw());
        memberDto.setUserRole(memberEntity.getUserRole());
        memberDto.setUserCreate(memberEntity.getUserCreate());

        return memberDto;

    }
}
