package dodgekr.lolcommunity.summoner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SummonerDTO {
        private String id;
        private String accountId;
        private String puuid;
        private String name;

        @JsonProperty("profileIconId")
        private String profileImg;
        private long revisionDate;
        private int summonerLevel;

        public void setProfileImg(String profileImg) {
                this.profileImg = "http://ddragon.leagueoflegends.com/cdn/13.24.1/img/profileicon/"+ profileImg + ".png";
        }
}
