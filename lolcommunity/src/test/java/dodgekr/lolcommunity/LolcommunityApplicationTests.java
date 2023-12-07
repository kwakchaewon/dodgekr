package dodgekr.lolcommunity;

import dodgekr.lolcommunity.member.Member;
import dodgekr.lolcommunity.member.MemberRepository;
import dodgekr.lolcommunity.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootTest
class LolcommunityApplicationTests {

	/**
	 *  test 작성 조건
	 *  1. given: 주어진 조건
	 *  2. when: 실행 로직
	 *  3. 결과
	 */

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberService memberService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

	/**
	 * admin 계정 생성
	 */
	@Test
	void createAdminMember(){
		Member member = new Member();

		member.setUsername("admin");
		member.setPassword(passwordEncoder.encode("admin"));
		member.setEmail("admin@naver.com");
		member.setDateJoined(LocalDateTime.now());
		member.setSuperuser(true);
		member.setActive(true);
		memberRepository.save(member);
	}

}
