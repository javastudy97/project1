package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.costant.Role;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<MemberDto> selectMembers(Pageable pageable) {

        Page<MemberEntity> memberEntityList =
                memberRepository.findAll(pageable);

        System.out.println(memberEntityList.getSize()+"<<< size");

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


    public void memberInsert(MemberDto memberDto) {
        MemberEntity memberEntity = MemberEntity.builder()
                .userEmail(memberDto.getUserEmail())
                .userPw(passwordEncoder.encode(memberDto.getUserPw()))
                .userPhone(memberDto.getUserPhone())
                .userName(memberDto.getUserName())
                .userRole(Role.MEMBER)
                .build();
        memberRepository.save(memberEntity);
    }


    public MemberDto findById(String userEmail) {

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserEmail(userEmail);

        if(!optionalMemberEntity.isPresent()){
            return null;
        }

        MemberDto memberDto = MemberDto.toMemberDto(optionalMemberEntity.get());

        return memberDto;
    }

    @Transactional
    public int memberUpdateDo2(MemberDto memberDto) {



        Long id =
                memberRepository.save(MemberEntity.toMemberEntityUpdate(memberDto,passwordEncoder)).getUserId();

        if (id==null) {
            return 0;
        }
        return 1;
    }

    @Transactional
    public void memberDeleteDo2(Long id) {

        MemberEntity memberEntity = memberRepository.findById(id).get();

        memberRepository.delete(memberEntity);

    }

    /* 중복 체크 */
    @Transactional
    public void checkUserEmail(MemberDto dto){
        boolean emailCheck = memberRepository.existsByUserEmail(dto.getUserEmail());
        if(emailCheck){
            throw new IllegalStateException("이미 사용중인 이메일 입니다.");
        }
    }

    @Transactional
    public void checkUserName(MemberDto dto){
        boolean nameCheck = memberRepository.existsByUserName(dto.getUserName());
        if(nameCheck){
            throw new IllegalStateException("이미 사용중인 닉네임 입니다.");
        }
    }

    // 이메일 찾기
    public MemberDto findEmail(String userName, String userPhone) {
        MemberEntity memberEntity = memberRepository.findByUserNameUserPhone(userName,userPhone);

        if(memberEntity!=null){
            return MemberDto.toMemberDto(memberEntity);
        }
        return null;
    }

}
