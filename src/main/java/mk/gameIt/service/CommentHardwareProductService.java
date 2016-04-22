package mk.gameIt.service;

import mk.gameIt.domain.CommentHardwareProduct;
import mk.gameIt.web.dto.CommentHardwareProductObject;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface CommentHardwareProductService {
    CommentHardwareProduct save(CommentHardwareProductObject commentHardwareProductObject);
}
