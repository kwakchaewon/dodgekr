package dodgekr.lolcommunity.summoner;

import lombok.RequiredArgsConstructor;
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
    private final String RIOT_API_KEY = "RGAPI-5b40150e-ae3f-409c-84fb-94c7571662b1";
    private final String RiotUri_getSummonerInfo = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}";

    public Summoner requestSummonerInfo(String summonerName){
        final HttpHeaders headers = new HttpHeaders();

        headers.set("X-Riot-Token", RIOT_API_KEY);


        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(RiotUri_getSummonerInfo, HttpMethod.GET, entity, Summoner.class, summonerName).getBody();
    }

}
