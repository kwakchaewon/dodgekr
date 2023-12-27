package dodgekr.lolcommunity.summoner;

import dodgekr.lolcommunity.RiotApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import org.springframework.http.HttpHeaders;

@Service
@RequiredArgsConstructor
public class RiotApi {

    private final RestTemplate restTemplate;

    @Autowired
    private final RiotApiConfig riotApiConfig;
    private final String RiotUri_getSummonerInfo = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}";

    public Summoner requestSummonerInfo(String summonerName){
        final HttpHeaders headers = new HttpHeaders();

        headers.set("X-Riot-Token", riotApiConfig.getRIOT_API_KEY());
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(RiotUri_getSummonerInfo, HttpMethod.GET, entity, Summoner.class, summonerName).getBody();
    }

}
