package dodgekr.lolcommunity.board;

import dodgekr.lolcommunity.member.Member;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Board> getBoardList(int page, String keyword) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdAt"));

        //pagesize: 한 페이지당 게시글 수
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Board> spec = search(keyword);
        return this.boardRepository.findAll(spec, pageable);
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

    public void update(Board board,String title, String content){
        board.setTitle(title);
        board.setContent(content);
        board.setUpdatedAt(LocalDateTime.now());
        this.boardRepository.save(board);
    }

    /**
     * 제목, 내용, 작성자 검색
     * 검색어(word)를 입력받아 쿼리의 조인과 where 문 완성
     */
    private Specification<Board> search(String keyword) {

        /**
         * select distinct
         * b.id, b.title, b.content, b.author_id, b.created_at, b.updated_at
         * from board b
         * left outer join Member m on b.author_id = m.id
         * where
         * b.title like '%keyword%'
         * or b.content like '%keyword%'
         * or m.username like '%keyword%'
         */

        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Board> b, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Board, Member> m = b.join("author", JoinType.LEFT);
                return cb.or(cb.like(b.get("title"), "%" + keyword + "%"), // 제목
                        cb.like(b.get("content"), "%" + keyword + "%"),      // 내용
                        cb.like(m.get("username"), "%" + keyword + "%"));    // 질문 작성자
            }
        };
    }

}
