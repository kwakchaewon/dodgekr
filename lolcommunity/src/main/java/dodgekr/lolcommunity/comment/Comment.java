package dodgekr.lolcommunity.comment;

import dodgekr.lolcommunity.member.Member;
import dodgekr.lolcommunity.board.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private Board board;

    @ManyToOne
    private Member member;

    private Long referReplyId;
}
