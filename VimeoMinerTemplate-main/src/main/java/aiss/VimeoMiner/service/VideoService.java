package aiss.VimeoMiner.service;

import aiss.VimeoMiner.VDmodel.Video;
import aiss.VimeoMiner.exceptions.CommentsNotFoundAvailableException;
import aiss.VimeoMiner.model.VideoList;
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
public class VideoService {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    public List<Video> getVideosFromChannel(String channel_id, int maxVideos, int maxComments) throws CommentsNotFoundAvailableException {
        String uri = "https://api.vimeo.com/channels/" + channel_id + "/videos";
        String token = "451ed1d47ef2e8309f648f71e5a4164f";
        List<aiss.VimeoMiner.model.Video> vide=null;
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);

        HttpEntity<VideoList> request = new HttpEntity<>(null, headers);

        ResponseEntity<VideoList> response = restTemplate.exchange(uri, HttpMethod.GET, request, VideoList.class);

        List<Video> res = new ArrayList<>();
        for(aiss.VimeoMiner.model.Video v: response.getBody().getData()){
            Video vd = new Transform().videoTrans(v, channel_id,maxComments);
            res.add(vd);
        }

        List<Video> shortRes = res.subList(0,maxVideos);

        return shortRes;
    }

    public aiss.VimeoMiner.model.Video getVideo(String id){
        String uri="https://api.vimeo.com/videos/"+id;
        String token="451ed1d47ef2e8309f648f71e5a4164f";

        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<aiss.VimeoMiner.model.Video> request = new HttpEntity<>(null, headers);

        ResponseEntity<aiss.VimeoMiner.model.Video> response = restTemplate.exchange(uri, HttpMethod.GET, request, aiss.VimeoMiner.model.Video.class);

        return response.getBody();

    }
}
