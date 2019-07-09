package org.flyants.chat.domain.service;

import org.flyants.chat.dto.Province;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/7/1 14:35
 * @Version v1.0
 */
public interface AreaService extends BaseService {


    List<Province> listAll();

}
