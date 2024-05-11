package aiss.VimeoMiner.transform;

import aiss.VimeoMiner.VDmodel.Caption;
import aiss.VimeoMiner.VDmodel.Channel;
import aiss.VimeoMiner.VDmodel.Comment;
import aiss.VimeoMiner.VDmodel.User;
import aiss.VimeoMiner.VDmodel.Video;
import aiss.VimeoMiner.exceptions.CommentsNotFoundAvailableException;
import aiss.VimeoMiner.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class Transform {

    @Autowired
    UserService userService = new UserService();
    CommentService commentService = new CommentService();

    VideoService videoService = new VideoService();

    ChannelService channelService;

    CaptionsService captionsService = new CaptionsService();
    public User userTrans(aiss.VimeoMiner.model.User user){
        User vdUser = new User();
        String id = user.getUri().split("/")[2];
        vdUser.setId(Long.parseLong(id));
        vdUser.setName(user.getName());
        vdUser.setPicture_link((user.getPictures().getBaseLink()));
        vdUser.setUser_link(user.getLink());

        return vdUser;
    }


    public Comment commentTrans(aiss.VimeoMiner.model.Comment comment, String videoId){
        Comment vdComment = new Comment();
        //UserService userService1 = new UserService();
        String commentId = comment.getUri().split("comments/")[1];


        vdComment.setId(commentId);
        vdComment.setAuthor(userService.getUserFromComment(commentId, videoId));
        vdComment.setText(comment.getText());
        vdComment.setCreatedOn(comment.getCreatedOn());

        return vdComment;

    }


    public Caption captionTrans(aiss.VimeoMiner.model.Caption caption){
        Caption vdCaption = new Caption();
        String captionId = caption.getUri().split("/")[2];
        vdCaption.setId(captionId);
        vdCaption.setLanguage(caption.getLanguage());
        vdCaption.setName(caption.getName());

        return vdCaption;
    }
    //TODO Arreglar las captions y posiblemente las uris
    public Video videoTrans(aiss.VimeoMiner.model.Video video, String channel_id, int maxComments) throws CommentsNotFoundAvailableException {
        Video vdVideo = new Video();
        String uri = video.getUri();
        String videoId = video.getUri().split("videos/")[1];

        vdVideo.setId(videoId);
        vdVideo.setName(video.getName());
        vdVideo.setDescription(video.getDescription());
        vdVideo.setReleaseTime(video.getReleaseTime());
        List<Caption> lsCap = captionsService.getCaptionsFromVideo(channel_id, videoId);
        vdVideo.setCaptions(lsCap);
        vdVideo.setComments(commentService.getAllCommentsFromVideo(videoId, maxComments));




        return vdVideo;
    }

    public Channel channelTrans(aiss.VimeoMiner.model.Channel channel, int maxVideos, int maxComments) throws CommentsNotFoundAvailableException {
        Channel vdChannel = new Channel();
        String channelId = channel.getUri().split("/")[2];

        vdChannel.setId(channelId);
        vdChannel.setName(channel.getName());
        Object desc = channel.getDescription();
        if(desc == null){
            vdChannel.setDescription("");
        } else {
            vdChannel.setDescription(channel.getDescription().toString());
        }

        vdChannel.setCreatedTime(channel.getCreatedTime());
        vdChannel.setVideos(videoService.getVideosFromChannel(channelId, maxVideos, maxComments));

        return vdChannel;

    }
}
