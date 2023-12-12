package dodgekr.lolcommunity.board;

import dodgekr.lolcommunity.member.Member;
import dodgekr.lolcommunity.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

        //2. 에러가 없다면 게시글 등록
        Member member = memberService.getMember(principal.getName());
        this.boardService.create(boardForm.getTitle(), boardForm.getContent(), member);
        return "redirect:/board/list";
    }

    /**
     *  게시글 전체 조회 (페이지네이션 적용)
     */
    @GetMapping("/list")
    public String listBoard(Model model, @RequestParam(value="page", defaultValue="1") int page) {
        Page<Board> paging = this.boardService.getBoardList(page-1);
        model.addAttribute("paging", paging);
        return "board_list";
    }

    /**
     * 게시글 상세
     */
    @GetMapping("/detail/{id}")
    public String detailBoard(Model model, @PathVariable("id") Integer id, BoardForm boardForm){
        Board board = boardService.getBoard(id);
        model.addAttribute("board",board);
        return "board_detail";
    }

    /**
     * 게시글 삭제
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Integer id, Principal principal){

        Board question = this.boardService.getBoard(id);

        // 프론트 단에서 검증됐더라도 백앤드 단에서도 검증 단계가 있는 것이 좋음
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.boardService.delete(question);
        return "redirect:/";
    }

    /**
     *  게시글 수정폼
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{id}")
    public String updateBoard(BoardForm boardForm, @PathVariable("id") Integer id, Principal principal){

        Board board = boardService.getBoard(id);
        if(!board.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        boardForm.setTitle(board.getTitle());
        boardForm.setContent(board.getContent());
        return "board_form";
    }

    /**
     * 게시글 수정
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String updateBoard(@Valid BoardForm boardForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id){
        if (bindingResult.hasErrors()) {
            return "board_form";
        }

        Board board = this.boardService.getBoard(id);

        // 프론트 단에서 검증됐더라도 백앤드 단에서도 검증 단계가 있는 것이 좋음
        if (!board.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.boardService.update(board,boardForm.getTitle(),boardForm.getContent());
        return "redirect:/board/detail/{id}";
    }
}
