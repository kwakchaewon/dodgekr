package dodgekr.lolcommunity.summoner.domain;

import lombok.Data;

@Data
public class SummonerDTO {
        private String id;
        private String accountId;
        private String puuid;
        private String name;
        private int profileIconId;
        private long revisionDate;
        private int summonerLevel;
}
