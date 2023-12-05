package dodgekr.lolcommunity.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원가입 폼
     */
    @GetMapping("/signup")
    public String signUp(MemberCreateForm memberCreateForm){return "signup_form";}

    /**
     * 회원가입 완료
     */
    @PostMapping("/signup")
    public String signUp(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult){
        // 1. 에러가 있을 경우 다시 폼 작성
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        //2. 두 개의 비밀번호가 동일한지 검증
        if (!(memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2()))){
            // bindingResult.rejectValue(필드명, 오류코드, 에러메시지)
            bindingResult.rejectValue("password2", "passwordInCorrect","2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            this.memberService.create(memberCreateForm.getUsername(), memberCreateForm.getEmail(), memberCreateForm.getPassword1());
        }catch (DataIntegrityViolationException e){

            //3. 사용자 ID 또는 이메일 주소가 동일할 경우 -> DataIntegrityViolationException 예외발생
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch (Exception e){

            // 4. 기타 예외 발생 경우
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        // 5. 성공시, 리다이렉트
        return "redirect:/";
    }

}
