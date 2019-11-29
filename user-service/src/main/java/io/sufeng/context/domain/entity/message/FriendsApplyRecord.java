package io.sufeng.context.domain.entity.message;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/6/13 11:13
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class FriendsApplyRecord {

    //同意 拒绝 忽略 同意
    public enum Status{
        APPLY,REJECT,IGNORE,AGREE
    }

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String applyMessageUserId;

    @Column
    private String messageUserId;

    @Column
    private Date applyTime;

    @Column
    private Date handlerTime;

    @Column
    private Status status;


}
