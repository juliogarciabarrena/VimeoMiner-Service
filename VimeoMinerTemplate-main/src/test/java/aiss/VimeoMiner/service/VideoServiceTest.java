package aiss.VimeoMiner.service;


import aiss.VimeoMiner.VDmodel.Video;
import aiss.VimeoMiner.exceptions.CommentsNotFoundAvailableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringBootTest
public class VideoServiceTest {
    @Autowired
    VideoService videoService;
    @Test
    @DisplayName("Get all videos from a channel")
    void getVideosFromChannel() throws CommentsNotFoundAvailableException {
        List<Video> videos = videoService.getVideosFromChannel("staff");
        assertFalse(videos.isEmpty(), "The list is empty");
        System.out.println(videos);
    }

    @Test
    @DisplayName("Get a video")
    void getVideo() {
        aiss.VimeoMiner.model.Video videos = videoService.getVideo("34239598");
        assertFalse(videos == null, "The list is empty");
        System.out.println(videos);
    }

}
