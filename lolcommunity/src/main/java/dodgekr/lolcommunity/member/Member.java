package dodgekr.lolcommunity.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(length = 100, unique = true)
    private String email;

    @Column(nullable = true)
    private String profile_img;

    private LocalDateTime date_joined;

    @Column(nullable = true)
    private LocalDateTime last_login;

    // 0: 관리자, 1: 일반 유저
    private byte is_superuser;

    // 0: 휴면 계정, 1: 일반 계정
    private byte is_active;

    @Column(nullable = true)
    private String riot_id;
}
