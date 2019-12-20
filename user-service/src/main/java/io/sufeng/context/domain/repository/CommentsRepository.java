package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.comments.Comments;
import io.sufeng.context.domain.entity.comments.CommentsType;
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
