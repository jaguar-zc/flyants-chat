package org.flyants.chat.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhangchao
 * @Date 2019/5/21 17:30
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class LoginMethod {

    private static final String MARK_SEPARATOR = "@";


    public enum LoginType {
        PHONE, PASSWORD
    }

    public enum LoginMethodStatus {
        ACTIVE, DISABLE
    }

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private LoginType type;

    @Column
    private String mark;

    @Column
    private String peopleId;

    @Column
    @Enumerated(value = EnumType.STRING)
    private LoginMethodStatus status;


    public static String buildPasswordMark(String phone, String password) {
        return phone + MARK_SEPARATOR + DigestUtils.md2Hex(password);
    }

}
