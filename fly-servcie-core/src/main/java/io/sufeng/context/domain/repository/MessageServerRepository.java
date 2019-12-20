package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.message.MessageServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: sufeng
 * @create: 2019-12-20 16:10
 */
@Repository
public interface MessageServerRepository extends JpaRepository<MessageServer, String>, JpaSpecificationExecutor {

    Optional<MessageServer> findByHostAndPort(String host, int port);

}
