package org.flyants.chat.domain.service.impl;

import org.flyants.chat.domain.service.AreaService;
import org.flyants.chat.dto.Province;
import org.flyants.common.utils.JsonUtils;
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

    private List<Province> provinces;

    @Override
    public List<Province> listAll() {
        if(provinces == null){
            try {
                InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("area_data/location.json");
                int available = resourceAsStream.available();
                byte[] bytes = new byte[available];
                resourceAsStream.read(bytes);
                provinces = new ArrayList<>();
                provinces = JsonUtils.json2list(new String(bytes), Province.class);
            }catch (Exception e){
                e.printStackTrace();
                provinces = null;
            }
        }
        return provinces;
    }
}
