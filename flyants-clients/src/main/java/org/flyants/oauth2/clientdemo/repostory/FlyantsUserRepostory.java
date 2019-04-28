package org.flyants.oauth2.clientdemo.repostory;

import org.flyants.oauth2.clientdemo.bean.FlyantsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangchao
 * @Date 2019/4/28 14:49
 * @Version v1.0
 */
@Repository
public interface FlyantsUserRepostory extends JpaRepository<FlyantsUser,Long> {
}
