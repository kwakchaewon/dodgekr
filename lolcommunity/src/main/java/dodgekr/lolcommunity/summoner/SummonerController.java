package dodgekr.lolcommunity.summoner;

import dodgekr.lolcommunity.summoner.domain.LeagueEntryDTO;
import dodgekr.lolcommunity.summoner.domain.MatchDto;
import dodgekr.lolcommunity.summoner.domain.OwnMatchDto;
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
        List<MatchDto.ParticipantDto> playerRecords= summonerService.getPlayerRecords(matchList, summonerDTO);
//        MatchDto.InfoDto matchDto = summonerService.getInfoDto(matchList[0]);
        List<OwnMatchDto> ownMatchDtoList = summonerService.getOwnMatchDtoList(matchList, summonerDTO);

        model.addAttribute("summonerInfo", summonerDTO);
        model.addAttribute("entryInfo", leagueEntryDTO[0]);
//        model.addAttribute("playerRecords", playerRecords);
        model.addAttribute("playerRecords", ownMatchDtoList);

       return "summoner_detail";
    }

}
