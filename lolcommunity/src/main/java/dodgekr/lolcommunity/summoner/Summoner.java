package dodgekr.lolcommunity.summoner;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Summoner {
        private String id;
        private String accountId;
        private String puuid;
        private String name;
        private int profileIconId;
        private long revisionDate;
        private int summonerLevel;
}
