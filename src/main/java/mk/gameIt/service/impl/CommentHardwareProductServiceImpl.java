package mk.gameIt.service.impl;

import mk.gameIt.domain.CommentHardwareProduct;
import mk.gameIt.domain.HardwareProduct;
import mk.gameIt.repository.CommentHardwareProductRepository;
import mk.gameIt.repository.HardwareProductRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.CommentHardwareProductService;
import mk.gameIt.web.dto.CommentHardwareProductObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class CommentHardwareProductServiceImpl implements CommentHardwareProductService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(CommentHardwareProductServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    HardwareProductRepository hardwareProductRepository;
    @Autowired
    CommentHardwareProductRepository commentHardwareProductRepository;

    @Transactional
    @Override
    public CommentHardwareProduct save(CommentHardwareProductObject commentHardwareProductObject) {
        CommentHardwareProduct commentHardwareProduct= new CommentHardwareProduct();
        if (commentHardwareProductObject.getCommentDate() == null) {
            commentHardwareProduct.setCommentDate(LocalDateTime.now());
        }
        else{
            commentHardwareProduct.setCommentDate(commentHardwareProductObject.getCommentDate());
        }
        commentHardwareProduct.setCommentText(commentHardwareProductObject.getCommentText());
        long nmrComments = commentHardwareProductRepository.count();
        commentHardwareProduct.setCommentId(nmrComments);
        commentHardwareProduct.setHardwareId(hardwareProductRepository.findOne(commentHardwareProductObject.getHardwareProductId()));
        commentHardwareProduct.setUserId(userRepository.findOne(commentHardwareProductObject.getUserId()));
        commentHardwareProduct = commentHardwareProductRepository.save(commentHardwareProduct);
        log.debug("Created Comment for HardwareProduct: {}", commentHardwareProduct);
        return commentHardwareProduct;
    }
}
