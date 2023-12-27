package dodgekr.lolcommunity.summoner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SummonerService {
    private final RiotApi riotApi;

    @Transactional(readOnly = true)
    public Summoner findBySummoner(String summonerName){
        return riotApi.requestSummonerInfo(summonerName);
    }

}
