package io.sufeng.context.domain.entity.message;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Im服务器
 *
 * @author: sufeng
 * @create: 2019-12-20 16:07
 */
@Getter
@Setter
@Entity
public class MessageServer {

    public enum ServerStatus {
        STOP, START
    }

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ServerStatus serverStatus;

    @Column
    private Date createTime;

    @Column
    private Date updateTime;

    @Column
    private String host;

    @Column
    private Integer port;

}
