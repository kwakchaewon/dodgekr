package dodgekr.lolcommunity.summoner.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class MatchDto {
    @JsonProperty("info")
    private InfoDto info;

    @Getter @Setter
    public static class InfoDto{
        @JsonProperty("participants")
        private List<ParticipantDto> participants;

        @JsonProperty("gameDuration") // 게임 시간(초)
        private String gameDuration;

        @JsonProperty("gameMode") // CLASSIC: 일반, ARAM: 무작위 총력전
        private String gameMode;

        public void setGameDuration(int gameDuration) {
            this.gameDuration = String.valueOf(gameDuration/60) + "분 " + String.valueOf(gameDuration%60) + "초" ;
        }

        public void setGameMode(String gameMode) {
            if (gameMode.equals("CLASSIC")){
                this.gameMode = "일반";
            } else if (gameMode.equals("ARAM")) {
                this.gameMode = "무작위 총력전";
            } else {
                this.gameMode = "기타";
            }
        }
    }

    @Getter @Setter
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

//        @JsonProperty("championName")
//        String championName;

        @JsonProperty("championName")
        String championImg;

        public void setChampionImg(String championImg) {
            this.championImg = "http://ddragon.leagueoflegends.com/cdn/13.24.1/img/champion/" + championImg + ".png";
        }
    }
}
