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

    @Column(nullable = false)
    private String password;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String profileImg;

    @Column(nullable = false)
    private LocalDateTime dateJoined;

    private LocalDateTime lastLogin;

    // true: 관리자, false: 일반 유저
    @Column(nullable = false)
    private boolean isSuperuser;

    // true: 일반 계정, false: 휴면 계정
    @Column(nullable = false)
    private boolean isActive;

    private String riotId;
}
