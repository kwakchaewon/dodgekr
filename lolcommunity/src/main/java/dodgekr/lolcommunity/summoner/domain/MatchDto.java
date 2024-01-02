package dodgekr.lolcommunity.summoner.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class MatchDto {
    @JsonProperty("info")
    private InfoDto info;

    @Getter
    public static class InfoDto{
        @JsonProperty("participants")
        private List<ParticipantDto> participants;
    }

    @Getter
    @Setter
    public static class ParticipantDto{
        @JsonProperty("win")
        boolean win;

        @JsonProperty("puuid")
        String puuid;

        @JsonProperty("teamId")
        int teamId;

        @JsonProperty("kills")
        int kills;

        @JsonProperty("deaths")
        int deaths;

        @JsonProperty("assists")
        int assists;

        @JsonProperty("championId")
        String championId;

        @JsonProperty("championName")
        String championName;
    }

}