package aiss.VimeoMiner.service;

import aiss.VimeoMiner.VDmodel.Channel;
import aiss.VimeoMiner.exceptions.CommentsNotFoundAvailableException;
import aiss.VimeoMiner.model.ChannelList;
import aiss.VimeoMiner.transform.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;
    public List<aiss.VimeoMiner.model.Channel> getAllChannels(){
        String uri = "https://api.vimeo.com/channels";
        String token = "451ed1d47ef2e8309f648f71e5a4164f";
        List<aiss.VimeoMiner.model.Channel> chan=null;
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);

        HttpEntity<ChannelList> request = new HttpEntity<>(null, headers);

        ResponseEntity<ChannelList> response = restTemplate.exchange(uri, HttpMethod.GET, request, ChannelList.class);


        chan= response.getBody().getData();
        return chan;


    }


    public Channel getChannel(String id, int maxVideos, int maxComments) throws CommentsNotFoundAvailableException {
        String uri="https://api.vimeo.com/channels/"+id;
        String token="451ed1d47ef2e8309f648f71e5a4164f";

        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<aiss.VimeoMiner.model.Channel> request = new HttpEntity<>(null, headers);

        ResponseEntity<aiss.VimeoMiner.model.Channel> response = restTemplate.exchange(uri, HttpMethod.GET, request, aiss.VimeoMiner.model.Channel.class);

        Channel vdChannel = new Transform().channelTrans(response.getBody(), maxVideos, maxComments);

        return vdChannel;

    }

}
