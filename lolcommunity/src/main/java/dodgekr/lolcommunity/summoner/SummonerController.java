package dodgekr.lolcommunity.summoner;

import dodgekr.lolcommunity.summoner.domain.LeagueEntryDTO;
import dodgekr.lolcommunity.summoner.domain.MatchDto;
import dodgekr.lolcommunity.summoner.domain.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SummonerController {

    private final SummonerService summonerService;

    @RequestMapping(value = "/summoner/{summonerName}", method = RequestMethod.GET)
    public String searchSummoner(Model model, @PathVariable String summonerName){

        SummonerDTO summonerDTO = summonerService.findSummoner(summonerName);
        LeagueEntryDTO[] leagueEntryDTO = summonerService.findLeagueEntry(summonerDTO.getId());
        String[] matchList = summonerService.findMatchList(summonerDTO.getPuuid());
        MatchDto matchDto = summonerService.findMatch(matchList[0]);

        model.addAttribute("summonerInfo", summonerDTO);
        model.addAttribute("profileUrl", "http://ddragon.leagueoflegends.com/cdn/13.24.1/img/profileicon/"+ summonerDTO.getProfileIconId() + ".png");
        model.addAttribute("entryInfo", leagueEntryDTO[0]);
//        model.addAttribute("matchList", matchList);

        MatchDto.ParticipantDto desirePlayer = new MatchDto.ParticipantDto();

        List<MatchDto.ParticipantDto> participants = matchDto.getInfo().getParticipants();
        // puuid 값을 비교해 본인의 participants 값만 가져오기
        for(MatchDto.ParticipantDto p: participants){
            if(p.getPuuid().equals(summonerDTO.getPuuid())){
                desirePlayer.setPuuid(p.getPuuid());
                desirePlayer.setTeamId(p.getTeamId());
                desirePlayer.setWin(p.isWin());
                desirePlayer.setKills(p.getKills());
                desirePlayer.setDeaths(p.getDeaths());
                desirePlayer.setAssists(p.getAssists());
                desirePlayer.setChampionId(p.getChampionId());
                desirePlayer.setChampionName(p.getChampionName());
            }
        }

        model.addAttribute("desirePlayer", desirePlayer);
        return "summoner_detail";
    }

}
