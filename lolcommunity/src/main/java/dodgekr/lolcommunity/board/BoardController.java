package dodgekr.lolcommunity.board;

import dodgekr.lolcommunity.member.Member;
import dodgekr.lolcommunity.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    /**
     * 게시글 등록 폼
     */
    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createBoard(BoardForm boardForm){
        return "board_form";
    }

    /**
     *  게시글 등록완료
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid BoardForm boardForm, BindingResult bindingResult, Principal principal){

        // 1. 에러가 있을 경우 다시 폼 작성
        if (bindingResult.hasErrors()) {
            return "board_form";
        }

        //2. 에러가 없다면 질문 등록
        Member member = memberService.getMember(principal.getName());
        this.boardService.create(boardForm.getTitle(), boardForm.getContent(), member);
        return "redirect:/";
        // return "redirect:/board/list";
    }
}
