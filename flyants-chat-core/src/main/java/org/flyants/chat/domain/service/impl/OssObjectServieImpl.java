package org.flyants.chat.domain.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flyants.chat.domain.service.OssObjectServie;
import org.flyants.common.file.ObjectManagerFactory;
import org.flyants.common.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author zhangchao
 * @Date 2019/5/28 16:54
 * @Version v1.0
 */
@Service
public class OssObjectServieImpl implements OssObjectServie {

    @Autowired
    ObjectManagerFactory objectManagerFactory;


    @Override
    public String generateIcon(String prefix_,String name) {

        String prefix = "default" ;
        if(StringUtils.isNotEmpty(prefix_)){
            prefix = prefix_;
        }

        String imgName = prefix + "/" + UUID.randomUUID().toString().replace("-","") + ".jpg";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageUtil.generateImg(name, byteArrayOutputStream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            String path = objectManagerFactory.upload(inputStream, imgName);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
