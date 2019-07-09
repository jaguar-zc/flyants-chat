package org.flyants.chat.domain.service.impl;

import org.flyants.chat.domain.service.AreaService;
import org.flyants.chat.dto.Province;
import org.flyants.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/7/1 14:35
 * @Version v1.0
 */
@Service
public class AreaServiceImpl implements AreaService {

    private String LOCATION_JSON = "area_data/location.json";


    private List<Province> provinces;

    @Override
    public List<Province> listAll() {
        logger.info("listAll provinces is null:{}",provinces == null);
        if (provinces == null) {
            try {
                InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(LOCATION_JSON);

                logger.info("ClassLoader.getResourceAsStream:{}", resourceAsStream != null ? resourceAsStream.available() : "null");

                if (resourceAsStream == null || resourceAsStream.available() < 1) {
                    resourceAsStream = new DefaultResourceLoader().getResource(LOCATION_JSON).getInputStream();
                    logger.info("DefaultResourceLoader.getResource:{}", resourceAsStream != null ? resourceAsStream.available() : "null");
                }
                int available = resourceAsStream.available();
                byte[] bytes = new byte[available];
                resourceAsStream.read(bytes);
                provinces = new ArrayList<>();
                String jsonStr = new String(bytes);
                logger.info("listAll jsonStr:{}",jsonStr);
                provinces = JsonUtils.json2list(jsonStr, Province.class);
            } catch (Exception e) {
                e.printStackTrace();
                provinces = null;
            }
        }else{
            logger.info("listAll:{}",provinces.size());
        }
        return provinces;
    }


}
