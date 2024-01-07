package dodgekr.lolcommunity.summoner;

import dodgekr.lolcommunity.summoner.domain.LeagueEntryDTO;
import dodgekr.lolcommunity.summoner.domain.MatchDto;
import dodgekr.lolcommunity.summoner.domain.OwnMatchDto;
import dodgekr.lolcommunity.summoner.domain.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


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
    public SummonerDTO findSummoner(String summonerName) {
        return riotApi.getSummoner(summonerName);
    }

    @Transactional(readOnly = true)
    public LeagueEntryDTO[] findLeagueEntry(String encryptedSummonerId) {
        return riotApi.getLeagueEntries(encryptedSummonerId);
    }

    @Transactional(readOnly = true)
    public String[] findMatchList(String puuid) {
        return riotApi.getMatchList(puuid);
    }

    @Transactional(readOnly = true)
    public MatchDto findMatch(String matchId) {
        return riotApi.getMatch(matchId);
    }

    public MatchDto.InfoDto getInfoDto(String matchId) {
        return riotApi.getMatch(matchId).getInfo();
    }

    public List<MatchDto.ParticipantDto> getPlayerRecords(String[] matchIds, SummonerDTO summonerDTO) {
        List<MatchDto.ParticipantDto> playerRecoreds = new ArrayList<>();

        for (String matchId : matchIds) {
            MatchDto.ParticipantDto playerRecord = new MatchDto.ParticipantDto();

            MatchDto matchDto = this.findMatch(matchId);
            List<MatchDto.ParticipantDto> participants = matchDto.getInfo().getParticipants();

            for (MatchDto.ParticipantDto p : participants) {
                if (p.getPuuid().equals(summonerDTO.getPuuid())) {
                    playerRecord = p;
                    break;
                }
            }
            playerRecoreds.add(playerRecord);
        }
        return playerRecoreds;
    }

    public List<OwnMatchDto> getOwnMatchDtoList(String[] matchIds, SummonerDTO summonerDTO) {

        // 1. 조회할 매치 id 값(리스트)과 검색한 소환사 정보값(단일) 파라미터로 들어옴

        // 2. 결과로 출력할 List<OwnMatchDto> 객체 생성
        List<OwnMatchDto> ownMatchDtoList = new ArrayList<>();

        // 3. 매치 id loop
        for (String matchId : matchIds) {

            // 3.1 매치 아이디별 상세 매치 정보 조회
            OwnMatchDto ownMatchDto = new OwnMatchDto();
            MatchDto matchDto = this.findMatch(matchId);

            // 3.2 ownMatchDto 게임 정보 설정
            ownMatchDto.setGameDuration(matchDto.getInfo().getGameDuration());
            ownMatchDto.setGameMode(matchDto.getInfo().getGameMode());

            // 3.3 ParticipantDto 중 소환사 정보값과 일치하는 값만 ownMatchDto 값으로 set
            List<MatchDto.ParticipantDto> participants = matchDto.getInfo().getParticipants();
            for (MatchDto.ParticipantDto p : participants) {
                if (p.getPuuid().equals(summonerDTO.getPuuid())) {
                    ownMatchDto.setParticipantDto(p);
                    break;
                }
            }

            ownMatchDtoList.add(ownMatchDto);
        }
        // 4. ownMatchDtoList return
        return ownMatchDtoList;
    }
}
