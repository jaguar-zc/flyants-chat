package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.common.exception.BusinessException;
import org.flyants.common.file.ObjectUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author zhangchao
 * @Date 2019/6/6 18:16
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/static")
@Slf4j
public class AppStaticController {

    @Autowired
    ObjectUpload objectUpload;


    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        String suffixName = ".jpg";
        String newFileName = UUID.randomUUID().toString()+suffixName;
        try {
            String url = objectUpload.upload(file.getInputStream(),newFileName);
            log.info("url:{}",url);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }
}
