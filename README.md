# ProxyUtils

### what's this?

该工具大致用途是做为梯子代理请求, 不同于ip代理, 该工具采用现有网络资源, 可以做到零成本请求代理

具体代理流程

![](https://z3.ax1x.com/2021/08/23/hCXOXj.png)

HttpUtil请求工具 ＞＞＞＞＞＞cloudflare边缘化容器＞＞＞＞＞＞ HoppScotch在线请求工具

[cloudflare workers](https://workers.cloudflare.com/) 

##### 优点: 

1. 可以作为梯子发起匿名请求
2. 免费用户有10万请求/每天 的额度
3. 无限部署脚本

##### 缺点:

1. 取不到原始响应的header

[HoppScotch](https://hoppscotch.io/cn/)

##### 优点: 

1. 可以拿到完整的响应
2. 请求参数比较完善

##### 缺点:

1. 代理ip一般不会有太大变化

### How to use use?

1. 注册cloudflare账号 创建workers容器 
2. 复制 [proxy.js](https://github.com/A-Chainsmokers/ProxyUtils/blob/master/js/proxy.js) 粘贴到workers容器 
3. 复制生成的url 粘贴到 yml 配置文件中  格式为:

`https://生成的三级域名.workers.dev?param=`

使用案例参考[测试类](https://github.com/A-Chainsmokers/ProxyUtils/blob/master/src/test/java/com/example/proxyutils/ProxyUtilsApplicationTests.java)

