package aiss.VimeoMiner.service;

import aiss.VimeoMiner.VDmodel.User;
import aiss.VimeoMiner.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import aiss.VimeoMiner.transform.Transform;
@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate = new RestTemplate();


    public aiss.VimeoMiner.model.User getUser(String id_user){
        String uri="https://api.vimeo.com/users/" + id_user;
        String token = "451ed1d47ef2e8309f648f71e5a4164f";
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);

        HttpEntity<aiss.VimeoMiner.model.User> request = new HttpEntity<>(null, headers);

        ResponseEntity<aiss.VimeoMiner.model.User> response = restTemplate.exchange(uri, HttpMethod.GET, request, aiss.VimeoMiner.model.User.class);

        return response.getBody();
    }
    public User getUserFromComment(String id_comment, String id_video){
        String uri="https://api.vimeo.com/videos/"+id_video+"/comments/"+id_comment;
        String token = "451ed1d47ef2e8309f648f71e5a4164f";
        HttpHeaders headers = new HttpHeaders();


        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Comment> request = new HttpEntity<>(null, headers);

        ResponseEntity<Comment> response = restTemplate.exchange(uri, HttpMethod.GET, request, Comment.class);


        User vdUser = new Transform().userTrans(response.getBody().getUser());

        return vdUser;
    }



}
