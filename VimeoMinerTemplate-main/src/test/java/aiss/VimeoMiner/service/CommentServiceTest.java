package aiss.VimeoMiner.service;

import aiss.VimeoMiner.VDmodel.Comment;
import aiss.VimeoMiner.exceptions.CommentsNotFoundAvailableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService service;

    @Test
    @DisplayName("get all comments from a video")
    void getCommentsFromVideo() throws CommentsNotFoundAvailableException {
        List<Comment> comments= service.getAllCommentsFromVideo("65843743", 3);
        assertFalse(comments.isEmpty(), "The list is empty");
        System.out.println(comments);
    }

    @Test
    @DisplayName("get a commet")
    void getComment(){
        aiss.VimeoMiner.model.Comment c= service.getComment("18772746","65843743");
        assertFalse( c == null, "The list is empty");
        System.out.println(c);
    }
}
