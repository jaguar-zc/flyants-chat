package org.flyants.authorize.domain.repository;

import org.flyants.authorize.domain.entity.platform.comments.Comments;
import org.flyants.authorize.domain.entity.platform.comments.CommentsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by jagua on 2019/5/27.
 */
@Repository
public interface CommentsRepository extends JpaRepository<Comments,String>  , JpaSpecificationExecutor {

    Integer countByResourceIdAndCommentsType(String resourceId,  CommentsType commentsType);

}
