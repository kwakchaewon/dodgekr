package dodgekr.lolcommunity.summoner;

import dodgekr.lolcommunity.summoner.domain.LeagueEntryDTO;
import dodgekr.lolcommunity.summoner.domain.MatchDto;
import dodgekr.lolcommunity.summoner.domain.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 서비스 네이밍 원칙
 * 함수명:
 * 반환 타입: Return value
 */

@Service
@RequiredArgsConstructor
public class SummonerService {
    private final RiotApi riotApi;

    @Transactional(readOnly = true)
    public SummonerDTO findSummoner(String summonerName){
        return riotApi.getSummoner(summonerName);
    }

    @Transactional(readOnly = true)
    public LeagueEntryDTO[] findLeagueEntry(String encryptedSummonerId){
        return riotApi.getLeagueEntries(encryptedSummonerId);
    }

    @Transactional(readOnly = true)
    public String[] findMatchList(String puuid){
        return riotApi.getMatchList(puuid);
    }

    @Transactional(readOnly = true)
    public MatchDto findMatch(String matchId){
        return riotApi.getMatch(matchId);
    }
}
