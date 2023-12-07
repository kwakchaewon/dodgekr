package dodgekr.lolcommunity.member;

import dodgekr.lolcommunity.board.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(String username, String email, String password){
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setEmail(email);
        member.setDate_joined(LocalDateTime.now());
        member.set_superuser(false);
        member.set_active(true);

        this.memberRepository.save(member);
        return member;
    }

    public void getMemberLoginCheck(String username, String password){
        // 1. username, password 로 멤버 리턴
        Optional<Member> _member = this.memberRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password));

        // 2. 없다면 Exception 리턴
        if(_member.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
    }

    /**
     * username 으로 member 조회
     */
    public Member getMember(String username){
        Optional<Member> _member = this.memberRepository.findByUsername(username);
        if (_member.isPresent()) {
            return _member.get();
        } else {
            throw new DataNotFoundException("member is not found");
        }
    }

}
