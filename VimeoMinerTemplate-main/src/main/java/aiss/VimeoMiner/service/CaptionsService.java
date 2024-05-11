package aiss.VimeoMiner.service;

import aiss.VimeoMiner.VDmodel.Caption;
import aiss.VimeoMiner.model.CaptionList;
import aiss.VimeoMiner.transform.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class CaptionsService {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    //https://api.vimeo.com/channels/staff/videos/76979871/texttracks
    public List<Caption> getCaptionsFromVideo(String channel_id, String video_id){
        String uri = "https://api.vimeo.com/channels/" + channel_id + "/videos/" + video_id +"/texttracks";
        String token = "451ed1d47ef2e8309f648f71e5a4164f";
        List<aiss.VimeoMiner.model.Caption> capt=null;
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);

        HttpEntity<CaptionList> request = new HttpEntity<>(null, headers);

        List<Caption> res = new ArrayList<>();


        ResponseEntity<CaptionList> response = restTemplate.exchange(uri, HttpMethod.GET, request, CaptionList.class);

        for (aiss.VimeoMiner.model.Caption c: response.getBody().getData()){
            Caption vc = new Transform().captionTrans(c);
            res.add(vc);
        }

        capt= response.getBody().getData();
        return res;
    }

    public aiss.VimeoMiner.model.Caption getCaption(String id){
        String uri="https://api.vimeo.com/videos/"+id;
        String token="451ed1d47ef2e8309f648f71e5a4164f";

        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<aiss.VimeoMiner.model.Caption> request = new HttpEntity<>(null, headers);

        ResponseEntity<aiss.VimeoMiner.model.Caption> response = restTemplate.exchange(uri, HttpMethod.GET, request, aiss.VimeoMiner.model.Caption.class);
        return response.getBody();

    }
}
