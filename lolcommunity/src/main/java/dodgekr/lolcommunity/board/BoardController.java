package dodgekr.lolcommunity.board;

import dodgekr.lolcommunity.member.Member;
import dodgekr.lolcommunity.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createBoard(@Valid BoardForm boardForm, BindingResult bindingResult, Principal principal){

        // 1. 에러가 있을 경우 다시 폼 작성
        if (bindingResult.hasErrors()) {
            return "board_form";
        }

        //2. 에러가 없다면 질문 등록
        Member member = memberService.getMember(principal.getName());
        this.boardService.create(boardForm.getTitle(), boardForm.getContent(), member);
        return "redirect:/board/list";
    }

    /**
     *  게시글 전체 조회 (페이지네이션 적용)
     */
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="1") int page) {
        Page<Board> paging = this.boardService.getBoardList(page-1);
        model.addAttribute("paging", paging);
        return "board_list";
    }
}
