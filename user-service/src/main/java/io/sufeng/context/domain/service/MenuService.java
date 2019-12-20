package io.sufeng.context.domain.service;

import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.entity.Menu;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/22 14:18
 * @Version v1.0
 */
public interface MenuService {

    public PageResult<Menu> findList(Integer page, Integer size, String searchBy, String keyWord);

    public Menu get(String id);

    public void add(Menu menu);

    public void update(Menu menu);

    public void delete(String id);

    List<Menu> rootMeuns();

    List<Menu> findListByParentId(String parentId);
}
