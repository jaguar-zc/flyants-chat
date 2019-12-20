package io.sufeng.context.domain.service.impl;

import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.entity.Menu;
import io.sufeng.context.domain.repository.JpaSpecification;
import io.sufeng.context.domain.repository.MenuRepository;
import io.sufeng.context.domain.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/5/22 14:18
 * @Version v1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public PageResult<Menu> findList(Integer page, Integer size, String searchBy, String keyWord) {
        Specification specification = JpaSpecification.getSpecification(searchBy, keyWord);
        Page<Menu> all = menuRepository.findAll(specification, PageRequest.of(page - 1, size));

        return new PageResult<Menu>(all.getTotalElements(), all.getContent());
    }


    @Override
    public List<Menu> findListByParentId(String parentId) {
        if(parentId == null){
            return menuRepository.findByParentId(null);
        }else{
            return menuRepository.findByParentId(parentId);
        }
    }

    @Override
    public List<Menu> rootMeuns() {
        return menuRepository.findByParentId(null);
    }

    @Override
    public Menu get(String id) {
        Menu one = menuRepository.findById(id).get();
        return one;
    }

    @Override
    public void add(Menu menu) {
        if(menu.getParentId() == null){
            menu.setLevel(0);
        }else{
            Optional<Menu> optional = menuRepository.findById(menu.getParentId());
            menu.setLevel(optional.get().getLevel() + 1);
        }
        menuRepository.save(menu);
    }

    @Override
    public void update(Menu menu) {
        menuRepository.saveAndFlush(menu);
    }

    @Override
    public void delete(String id) {
        menuRepository.deleteById(id);
    }
}
