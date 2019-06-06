package org.flyants.chat.dto.app;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author zhangchao
 * @Date 2019/6/6 13:30
 * @Version v1.0
 */
@Getter
@Setter
public class PeopleConfigDto {
    private Integer chatRecordCloudStore; //聊天记录云存储
    private Integer messageNotifyVoice;//消息通知声音
    private Integer messageNotifyShake;//消息通知震动
    private Integer addMeMethod;//添加我的方式
    private Integer addMeVerify;//添加我需要验证
    private Integer allowTomeRecommendedGroup;//允许向我推荐内容
    private Integer dynameicVideoPlayNet;//动态视频自动播放网络   WI-FI 4G


}
