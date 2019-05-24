package org.flyants.authorize.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.flyants.authorize.domain.Language;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String peopleNo;

    @Column
    private Date creationDate;

    @Column
    private Date modificationDate;

    @Column
    private String encodedPrincipal;

    @Column
    private String nickName;

    @Column
    private String phone;

    @Column
    private Integer sex;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Language language = Language.zh_CN;

    @Column
    private String country;

    @Column
    private String province;

    @Column
    private String city;

    @OneToMany(mappedBy = "peopleId",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<LoginMethod> loginMethodList = new ArrayList<>();





}
