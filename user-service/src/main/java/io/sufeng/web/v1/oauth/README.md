### 授权接口

 用户授权有四种模式：  
 - 授权码模式（authorization code）
 - 简化模式（implicit）
 - 密码模式（resource owner password credentials）
 - 客户端模式（client credentials）  
 
 
 开始授权获取code
/oauth2/authorize

``` json
http://localhost:8080/oauth2/authorize?response_type=code&client_id=s6BhdRkqt3&state=xyz &redirect_uri=http://88.88.10.82:8080/clientserver/callback

```




使用code获取access_token
/oauth2/access_token

```json



```