package dodgekr.lolcommunity.summoner;

import dodgekr.lolcommunity.summoner.domain.LeagueEntryDTO;
import dodgekr.lolcommunity.summoner.domain.MatchDto;
import dodgekr.lolcommunity.summoner.domain.OwnMatchDto;
import dodgekr.lolcommunity.summoner.domain.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public MatchDto.InfoDto getInfoDto(String matchId){
        return riotApi.getMatch(matchId).getInfo();
    }

    public List<MatchDto.ParticipantDto> getPlayerRecords(String[] matchIds, SummonerDTO summonerDTO){
        List<MatchDto.ParticipantDto> playerRecoreds = new ArrayList<>();

        for(String matchId : matchIds){
            MatchDto.ParticipantDto playerRecord = new MatchDto.ParticipantDto();

            MatchDto matchDto = this.findMatch(matchId);
            List<MatchDto.ParticipantDto> participants = matchDto.getInfo().getParticipants();

            for(MatchDto.ParticipantDto p: participants){
                if(p.getPuuid().equals(summonerDTO.getPuuid())){
                    playerRecord = p;
                    break;
                }
            }
            playerRecoreds.add(playerRecord);
        }
        return playerRecoreds;
    }

    public List<OwnMatchDto> getOwnMatchDtoList(String[] matchIds, SummonerDTO summonerDTO){
        List<OwnMatchDto> ownMatchDtoList = new ArrayList<>();

        for (String matchId : matchIds){
            OwnMatchDto ownMatchDto = new OwnMatchDto();
            MatchDto matchDto = this.findMatch(matchId);

            ownMatchDto.setGameDuration(matchDto.getInfo().getGameDuration());
            ownMatchDto.setGameMode(matchDto.getInfo().getGameMode());

            List<MatchDto.ParticipantDto> participants = matchDto.getInfo().getParticipants();
            for(MatchDto.ParticipantDto p: participants){
                if(p.getPuuid().equals(summonerDTO.getPuuid())){
                    ownMatchDto.setParticipantDto(p);
                    break;
                }
            }
        ownMatchDtoList.add(ownMatchDto);
        }
    return ownMatchDtoList;
    }
}
