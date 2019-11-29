package io.sufeng.context.domain.entity;

import io.sufeng.context.domain.Language;
import lombok.Getter;
import lombok.Setter;
import io.sufeng.context.domain.entity.message.MessageUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * @Author zhangchao
 * @Date 2019/4/25 18:15
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class People {


    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String peopleNo;

    @Column
    private Date createTime;

    @Column
    private Date updateTime;

    @Column
    private String encodedPrincipal;

    @Column
    private String nickName;

    @Column
    private String phone;

    @Column
    @Enumerated(value = EnumType.STRING)
    private PeopleSex sex;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Language language = Language.zh_CN;

    @Column
    private String province;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String street;

    @JoinColumn(name="message_user_id", unique=true)
    @OneToOne(fetch=FetchType.LAZY)
    private MessageUser messageUser;

    @OneToMany(mappedBy = "peopleId",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<LoginMethod> loginMethodList = new ArrayList<>();



}
