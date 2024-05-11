package aiss.VimeoMiner.service;

import aiss.VimeoMiner.VDmodel.Channel;
import aiss.VimeoMiner.exceptions.CommentsNotFoundAvailableException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService channelService;

    @Test
    void getAllChannels() {
        List<aiss.VimeoMiner.model.Channel> channels = channelService.getAllChannels();
        assertFalse(channels.isEmpty(), "The list is empty");
        System.out.println(channels);
    }

    @Test
    void getChannel() throws CommentsNotFoundAvailableException {
        Channel channel = channelService.getChannel("1902017");
        assertFalse(channel == null, "No Channel Recieved");
        System.out.println(channel);
    }
}