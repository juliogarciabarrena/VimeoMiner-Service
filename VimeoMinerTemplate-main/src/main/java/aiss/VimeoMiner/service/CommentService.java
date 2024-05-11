package aiss.VimeoMiner.service;

import aiss.VimeoMiner.VDmodel.Comment;
import aiss.VimeoMiner.exceptions.CommentsNotFoundAvailableException;
import aiss.VimeoMiner.model.CommentList;
import aiss.VimeoMiner.transform.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    // get all comments from a video

    public List<Comment> getAllCommentsFromVideo(String video_id, int maxComments) throws CommentsNotFoundAvailableException {
        String uri="https://api.vimeo.com/videos/"+video_id+"/comments";
        String token = "451ed1d47ef2e8309f648f71e5a4164f";
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);

        HttpEntity<CommentList> request = new HttpEntity<>(null, headers);

            ResponseEntity<CommentList> response = restTemplate.exchange(uri, HttpMethod.GET, request, CommentList.class);
            List<Comment> res = new ArrayList<>();
            //assert arrayComm != null;
            ;
            if (response.getStatusCode() == HttpStatusCode.valueOf(403)){
                throw new CommentsNotFoundAvailableException();
            }

            for (aiss.VimeoMiner.model.Comment c: response.getBody().getData()){
                Comment vc = new Transform().commentTrans(c, video_id);
                res.add(vc);
            }
            if (res.size() > maxComments)
            {
               res = res.subList(0,maxComments);
            }
            return res;




        //Comment[] arrayComm = response.getBody();


    }

    //get a specific comment from a video

    public aiss.VimeoMiner.model.Comment getComment(String id, String id_video){
        String uri="https://api.vimeo.com/videos/"+id_video+"/comments/"+id;
        String token = "451ed1d47ef2e8309f648f71e5a4164f";
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);

        HttpEntity<aiss.VimeoMiner.model.Comment> request = new HttpEntity<>(null, headers);

        ResponseEntity<aiss.VimeoMiner.model.Comment> response = restTemplate.exchange(uri, HttpMethod.GET, request, aiss.VimeoMiner.model.Comment.class);

        return response.getBody();
    }




}
