package org.flyants.chat.domain.entity.platform.system;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author zhangchao
 * @Date 2019/6/6 11:12
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class DefaultPeopleConfig {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private Integer chatRecordCloudStore; //聊天记录云存储
    @Column
    private Integer messageNotifyVoice;//消息通知声音
    @Column
    private Integer messageNotifyShake;//消息通知震动
    @Column
    private Integer addMeMethod;//添加我的方式
    @Column
    private Integer addMeVerify;//添加我需要验证
    @Column
    private Integer allowTomeRecommendedGroup;//允许向我推荐内容
    @Column
    private Integer dynameicVideoPlayNet;//动态视频自动播放网络   WI-FI 4G

}
