package mk.gameIt.web.rest;

import mk.gameIt.domain.HardwareProduct;
import mk.gameIt.service.HardwareProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Stefan on 22.04.2016.
 */

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HardwareController {

    @Autowired
    private HardwareProductService hardwareProductService;

    @RequestMapping(value = {"/hardware/{id}/picture"}, method = RequestMethod.GET)
    public void hardwarePicture(HttpServletResponse response, @PathVariable Long id) throws IOException, SQLException {
        OutputStream out = response.getOutputStream();
        HardwareProduct hardwareProduct = hardwareProductService.findOne(id);
        if (hardwareProduct == null || hardwareProduct.getHardwarePicture() == null) {
            return;
        }
        String contentDisposition = String.format("inline;filename=\"%s\"",
                hardwareProduct.getHardwareModelName() + ".png?hardwareProductId=" + hardwareProduct.getHardwareId());
        response.setHeader("Content-Disposition", contentDisposition);
        response.setContentType("image/png");
        response.setContentLength((int) hardwareProduct.getHardwarePicture().length());
        IOUtils.copy(hardwareProduct.getHardwarePicture().getBinaryStream(), out);
        out.flush();
        out.close();
    }
}
