package org.project.omwp.dto;

import lombok.*;
import org.project.omwp.entity.MemberEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPw;
    private String userRole;
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
