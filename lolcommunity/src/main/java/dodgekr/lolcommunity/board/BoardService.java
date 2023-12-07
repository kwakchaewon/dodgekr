package dodgekr.lolcommunity.board;

import dodgekr.lolcommunity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void create(String title, String content, Member author){
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setCreated_at(LocalDateTime.now());
        board.setAuthor(author);
        this.boardRepository.save(board);
    }
}
