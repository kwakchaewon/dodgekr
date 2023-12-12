package dodgekr.lolcommunity;

import dodgekr.lolcommunity.board.BoardRepository;
import dodgekr.lolcommunity.board.BoardService;
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
	private BoardRepository boardRepository;

	@Autowired
	private MemberService memberService;

	@Autowired
	private BoardService boardService;

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

	/**
	 *  Question 테이블 테스트 데이터 생성
	 */
	@Test
	void createQuestion(){
		for(int i = 1; i<=300; i++){
			String subject = String.format("테스트 데이터: [%03d]",i);
			String content = String.format("[%03d]번째 게시글",i);
			this.boardService.create(subject,content, null);
		}
	}

}
