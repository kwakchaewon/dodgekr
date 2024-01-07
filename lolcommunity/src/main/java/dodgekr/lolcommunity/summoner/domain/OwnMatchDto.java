package dodgekr.lolcommunity.summoner.domain;

import lombok.Data;

@Data
public class OwnMatchDto {
    MatchDto.ParticipantDto participantDto = new MatchDto.ParticipantDto();
    private String gameMode;
    private String gameDuration;
}
