package org.flyants.authorize.domain.repository;

import org.flyants.authorize.domain.entity.platform.dynamic.DynamicComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jagua on 2019/5/27.
 */
@Repository
public interface DynamicCommentsRepository extends JpaRepository<DynamicComments,Long>  {

    Integer countByDynamicId(Long dynamicId);

}
