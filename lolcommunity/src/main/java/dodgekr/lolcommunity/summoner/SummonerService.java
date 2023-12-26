package dodgekr.lolcommunity.summoner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SummonerService {
    private final String RIOT_API_KEY = "RGAPI-5b40150e-ae3f-409c-84fb-94c7571662b1";

    private final RiotApi riotApi;

    @Transactional(readOnly = true)
    public Summoner findBySummoner(String summonerName){
        return riotApi.requestSummonerInfo(summonerName);
    }

}
