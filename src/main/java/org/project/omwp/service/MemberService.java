package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Page<MemberDto> selectMembers(Pageable pageable) {

        Page<MemberEntity> memberEntityList =
                memberRepository.findAll(pageable);

        return memberEntityList.map(MemberDto::toMemberDto);
    }

    public Page<MemberDto> searchListDo(String type, String keyword, Pageable pageable) {

        if(type.equals("name")) {
           Page<MemberEntity> memberEntityList =
                   memberRepository.findByUserNameContaining(keyword,pageable);
            return memberEntityList.map(MemberDto::toMemberDto);
        } else if(type.equals("email")) {
            Page<MemberEntity> memberEntityList =
                    memberRepository.findByUserEmailContaining(keyword,pageable);
            return memberEntityList.map(MemberDto::toMemberDto);
        } else if(type.equals("role")) {
            Page<MemberEntity> memberEntityList =
                    memberRepository.findByUserRoleContaining(keyword,pageable);
            return memberEntityList.map(MemberDto::toMemberDto);
        }

        return null;
    }

    public Page<MemberDto> searchMemberDo(Long userId, Pageable pageable) {
        Page<MemberEntity> memberEntityList =
                memberRepository.findByUserId(userId,pageable);

       return memberEntityList.map(MemberDto::toMemberDto);
    }

    public MemberDto memberDetailDo(Long id) {
       return MemberDto.toMemberDto(memberRepository.findById(id).get());
    }

    @Transactional
    public int memberUpdateDo(MemberDto memberDto) {
        Long id =
                memberRepository.save(MemberEntity.toMemberEntityUpdate(memberDto)).getUserId();

        if (id==null) {
            return 0;
        }
        return 1;
    }

    @Transactional
    public int memberDeleteDo(Long id, String pw) {
       MemberEntity memberEntity = memberRepository.findById(id).get();
       if(!pw.equals(memberEntity.getUserPw())) {
           return 0;
       }

       memberRepository.delete(memberEntity);
       return 1;
    }
}
