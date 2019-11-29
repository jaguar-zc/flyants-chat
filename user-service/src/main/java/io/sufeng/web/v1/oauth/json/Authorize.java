package io.sufeng.web.v1.oauth.json;

import lombok.*;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:01
 * @Version v1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authorize {

    private String responseType;

    private String clientId;

    private String redirectUri;

    private String scope;

    private String state;

}
