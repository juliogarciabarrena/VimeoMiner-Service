package aiss.VimeoMiner.controller;

import aiss.VimeoMiner.VDmodel.Channel;
import aiss.VimeoMiner.exceptions.ChannelNotFoundException;
import aiss.VimeoMiner.exceptions.CommentsNotFoundAvailableException;
import aiss.VimeoMiner.service.ChannelService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("vimeoMiner/channel")

//https://api.vimeo.com/channels/1902017
public class ChannelController {

    @Autowired
    ChannelService channelService;
    RestTemplate restTemplate = new RestTemplate();

    //TODO Meter el optional o la exception
    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id,
                           @RequestParam(required = false)int maxVideos,
                           @RequestParam(required = false)int maxComments) throws ChannelNotFoundException, CommentsNotFoundAvailableException {
        Channel c = channelService.getChannel(id,maxVideos,maxComments);
        //Comprobación goes here
        return c;
    }


    //http://localhost:8081/VimeoMiner/VD_Channel/staff
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Channel createChannel(@PathVariable String id,
                                 @RequestParam(required = false)int maxVideos,
                                 @RequestParam(required = false)int maxComments
                                 ) throws CommentsNotFoundAvailableException {

        Channel vdChannel = channelService.getChannel(id,maxVideos,maxComments);
        String uri = "http://localhost:8080/vm/channels";
        ResponseEntity<Channel> resp = restTemplate.postForEntity(uri, vdChannel, Channel.class);

        return resp.getBody();
    }

}
