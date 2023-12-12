package dodgekr.lolcommunity.board;

import dodgekr.lolcommunity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void create(String title, String content, Member author){
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setCreatedAt(LocalDateTime.now());
        board.setAuthor(author);
        this.boardRepository.save(board);
    }

    public Page<Board> getBoardList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdAt"));

        //pagesize: 한 페이지당 게시글 수
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.boardRepository.findAll(pageable);
    }

    public Board getBoard(Integer id){
        Optional<Board> board = this.boardRepository.findById(id);

        if (board.isPresent()) {
            return board.get();
        } else {
            throw new DataNotFoundException("board not found");
        }
    }

    public void delete(Board question){
        this.boardRepository.delete(question);
    }

    public void update(Board question,String title, String content){
        question.setTitle(title);
        question.setContent(content);
        question.setUpdatedAt(LocalDateTime.now());
        this.boardRepository.save(question);
    }

}
