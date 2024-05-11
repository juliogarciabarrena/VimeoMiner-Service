package aiss.VimeoMiner.service;


import aiss.VimeoMiner.VDmodel.Caption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringBootTest
public class CaptionsServiceTest {
    @Autowired
    CaptionsService captionsService;
    @Test
    @DisplayName("Get all captions from a channel")
    void getCaptionsFromVideo() {
        List<Caption> captions = captionsService.getCaptionsFromVideo("staff", "76979871");
        assertFalse(captions.isEmpty(), "The list is empty");
        System.out.println(captions);
    }

    @Test
    @DisplayName("Get a caption")
    void getCaption() {
        aiss.VimeoMiner.model.Caption capt = captionsService.getCaption("34239598");
        assertFalse(capt == null, "The list is empty");
        System.out.println(capt);
    }

}
