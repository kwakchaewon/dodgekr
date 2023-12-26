package dodgekr.lolcommunity.summoner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequiredArgsConstructor
public class SummonerController {

    private final SummonerService summonerService;
//    @RequestMapping(value = "/summoner", method = RequestMethod.GET)
//    public String searchSummoner(Model model, @PathVariable String summonerName){
////                                 @RequestParam(value = "summoner", defaultValue = "너에게난뭐냐") String summonerName)
//        BufferedReader br = null;
////        Summoner temp= null;
//
//        try {
//            String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ summonerName	+"?api_key=" + RIOT_API_KEY;
//            URL url = new URL(urlstr);
//
//            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
//            urlconnection.setRequestMethod("GET");
//            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); // 여기에 문자열을 받아와라.
//
//            String result = "";
//            String line;
//
//            while((line = br.readLine()) != null) {
//                result = result + line;
////                System.out.print(line);
//            }
//
//            System.out.println(result);
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        return "summoner_detail";
//    }

    @RequestMapping(value = "/summoner/{summonerName}", method = RequestMethod.GET)
    public String searchSummoner(Model model, @PathVariable String summonerName){

        Summoner summonerInfo = summonerService.findBySummoner(summonerName);

        System.out.println(summonerInfo);

        model.addAttribute("summonerInfo", summonerInfo);
        return "summoner_detail";
    }

}
