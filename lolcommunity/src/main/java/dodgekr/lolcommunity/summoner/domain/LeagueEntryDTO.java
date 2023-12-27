package dodgekr.lolcommunity.summoner.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LeagueEntryDTO {

        private String leagueId;
        private String summonerId;
        private String summonerName;
        private String queueType;
        private String tier;
        private String rank;

        private int leaguePoints;
        private int wins;
        private int losses;

        private boolean hotStreak;
        private boolean veteran;
        private boolean freshBlood;
        private boolean inactive;

//        private MiniSeriesDTO miniSeries;


//
//
//    @Data
//    public class MiniSeriesDTO{
//        private int losses;
//        private int target;
//        private int wins;
//        private String progress;
//    }
}