package dodgekr.lolcommunity.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 로그인 (User 권한 부여)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. username 조회 후 멤버 리턴
        Optional<Member>  _member = this.memberRepository.findByUsername(username);

        // 2. 없다면 UsernameNotFoundException 리턴
        if(_member.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        Member member = _member.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 3. Is_superuser 가 true면 ADMIN 권한 부여, 이외에는 USER 권한 부여
        if (member.isSuperuser()==true){
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }

        System.out.println("authorities = " + authorities);
        System.out.println("member = " + member);
        return new User(member.getUsername(),member.getPassword(), authorities);
    }
}
