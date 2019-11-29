package io.sufeng.app.restful.app;

import lombok.extern.slf4j.Slf4j;
import io.sufeng.common.exception.BusinessException;
import io.sufeng.common.file.ObjectUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
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


    @PostMapping("/upload/file")
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


    @PostMapping("/upload/base64Image")
    public String upload(@RequestParam("base64ImageFile") String base64ImageFile){
        if (base64ImageFile == null) // 图像数据为空
            return null;
        String suffixName = ".jpg";
        String newFileName = UUID.randomUUID().toString()+suffixName;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64ImageFile);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            String url = objectUpload.upload(in, newFileName);
            return url;
        } catch (Exception e) {
            return null;
        }
    }

}
