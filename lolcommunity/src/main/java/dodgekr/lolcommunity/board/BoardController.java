package dodgekr.lolcommunity.board;

import dodgekr.lolcommunity.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
