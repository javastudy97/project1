package org.project.omwp.checked;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.repository.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUserEmail extends AbstractValidator<MemberDto> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDto dto, Errors errors) {
        if(memberRepository.existsByUserEmail(dto.getUserEmail())){
            errors.rejectValue("userEmail","이메일 중복오류","이미 사용중인 이메일 입니다.");
        }
    }
}
