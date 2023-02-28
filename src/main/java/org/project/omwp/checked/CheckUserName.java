package org.project.omwp.checked;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.repository.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUserName extends AbstractValidator<MemberDto> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDto dto, Errors errors) {
        if(memberRepository.existsByUserName(dto.getUserName())){
            errors.rejectValue("userName","닉네임 중복오류","이미 사용중인 닉네임 입니다.");
        }
    }
}
