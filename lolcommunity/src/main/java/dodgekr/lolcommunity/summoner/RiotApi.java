package dodgekr.lolcommunity.summoner;

import dodgekr.lolcommunity.RiotApiConfig;
import dodgekr.lolcommunity.summoner.domain.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

/**
 * RiotApi 함수 작성 원칙
 * 반환 타입: Return value
 * 함수명: discription
 */

@Service
@RequiredArgsConstructor
public class RiotApi {

    private final RestTemplate restTemplate;

    @Autowired
    private final RiotApiConfig riotApiConfig;
    private final String RiotUri_getSummoner = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}";

    public SummonerDTO getSummoner(String summonerName){
        final HttpHeaders headers = new HttpHeaders();

        headers.set("X-Riot-Token", riotApiConfig.getRIOT_API_KEY());
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(RiotUri_getSummoner, HttpMethod.GET, entity, SummonerDTO.class, summonerName).getBody();
    }

}
