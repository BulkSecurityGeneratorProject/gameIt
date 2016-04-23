package mk.gameIt.service.impl;

import mk.gameIt.GameItApplication;
import mk.gameIt.domain.HardwareProduct;
import mk.gameIt.domain.User;
import mk.gameIt.repository.CommentHardwareProductRepository;
import mk.gameIt.repository.HardwareProductRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.CommentHardwareProductService;
import mk.gameIt.web.dto.CommentHardwareProductObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Created by Stefan on 23.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GameItApplication.class)
@WebAppConfiguration
public class CommentHardwareProductServiceImplTest {
    @Autowired
    private CommentHardwareProductRepository commentHardwareProductRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentHardwareProductService commentHardwareProductService;
    @Autowired
    private HardwareProductRepository hardwareProductRepository;

    @Test
    public void save() throws Exception {
        CommentHardwareProductObject commentHardwareProductObject = new CommentHardwareProductObject();
        commentHardwareProductObject.setCommentText("newText");
        User user = userRepository.findOneByUsername("admin");
        HardwareProduct hardwareProduct = new HardwareProduct();
        hardwareProduct.setHardwareModelName("model1");
        hardwareProduct.setHardwareDescription("testGame1Desc");
        hardwareProduct.setHardwareProductionYear(new Date());
        hardwareProduct.setHardwarePerformance("perff1");
        hardwareProduct = hardwareProductRepository.save(hardwareProduct);
        commentHardwareProductObject.setUserId(user.getUserId());
        commentHardwareProductObject.setHardwareProductId(hardwareProduct.getHardwareId());
        commentHardwareProductService.save(commentHardwareProductObject);
    }
}