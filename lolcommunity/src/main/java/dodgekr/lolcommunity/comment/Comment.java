package dodgekr.lolcommunity.comment;

import dodgekr.lolcommunity.member.Member;
import dodgekr.lolcommunity.post.Post;
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
    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Member member;

    private Long refer_reply_id;
}
