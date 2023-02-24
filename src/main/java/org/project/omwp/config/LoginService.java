package org.project.omwp.config;

import lombok.RequiredArgsConstructor;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        // DB에 있는지 확인
        Optional<MemberEntity> memberEntity = memberRepository.findByUserEmail(userEmail);

        if(memberEntity.isEmpty()){
            throw new UsernameNotFoundException("사용자가 없습니다.");
        }


        MemberEntity memberEntity1 = memberEntity.get();

        System.out.println(memberEntity1.getUserEmail() + "<<<<<<<<email");
        System.out.println(memberEntity1.getUserPw() + "<<<<<<<<pw");
        System.out.println(memberEntity1.getUserName() + "<<<<<<<<name");
        System.out.println(memberEntity1.getUserPhone() + "<<<<<<phone");
        System.out.println(memberEntity1.getUserRole().toString() + "<<<<<<<<<<Role");

        return User.builder()
                .username(memberEntity1.getUserEmail())
                .password(memberEntity1.getUserPw())
                .roles(memberEntity1.getUserRole().toString())
                .build();
    }
}
