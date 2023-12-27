package dodgekr.lolcommunity.summoner;

import dodgekr.lolcommunity.summoner.domain.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 서비스 네이밍 원칙
 * 반환 타입: Return value
 */

@Service
@RequiredArgsConstructor
public class SummonerService {
    private final RiotApi riotApi;

    @Transactional(readOnly = true)
    public SummonerDTO findBySummoner(String summonerName){
        return riotApi.getSummoner(summonerName);
    }

}
