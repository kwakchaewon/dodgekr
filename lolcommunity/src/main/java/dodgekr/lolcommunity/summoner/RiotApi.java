package dodgekr.lolcommunity.summoner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dodgekr.lolcommunity.RiotApiConfig;
import dodgekr.lolcommunity.summoner.domain.LeagueEntryDTO;
import dodgekr.lolcommunity.summoner.domain.SummonerDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.ArrayList;

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
    private final String RiotUri_getLeagueEntries = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}";
//    private final String RiotUri_getLeagueEntries = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/";


    /**
     *
     * @param summonerName
     * @return
     */
    public SummonerDTO getSummoner(String summonerName){
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", riotApiConfig.getRIOT_API_KEY());
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(RiotUri_getSummoner, HttpMethod.GET, entity, SummonerDTO.class, summonerName).getBody();
    }

    @SneakyThrows
    public LeagueEntryDTO[] getLeagueEntries(String encryptedSummonerId) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", riotApiConfig.getRIOT_API_KEY());
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(RiotUri_getLeagueEntries, HttpMethod.GET, entity, LeagueEntryDTO[].class, encryptedSummonerId).getBody();
//        String jsonString = restTemplate.exchange(RiotUri_getLeagueEntries, HttpMethod.GET, entity, String.class, encryptedSummonerId).getBody();
//        ObjectMapper objectMapper = new ObjectMapper();

//        return objectMapper.readValue(jsonString, LeagueEntryDTO[].class);
    }

}
