## 搭建框架 
* Consumer
* Provider
* Prvoider-common
* rpc
  * common
    * URL	
    * RequestMessage		服务调用的消息体
  * register
    * LocalRegisterMap		充当本地注册中心（注册接口和实现类的关系）
    * RemoteRegisterMap	充当远程注册中心（注册 ip 和 port）
  * protocol
    * HttpClient	实现把消息发送到 server 端
    * HttpServer	对消息进行处理
    * DispatcherServlet	
    * HttpServerHandler
  * proxy
    * ProxyFactory	给出代理对象，方便 client 调用方法。负载均衡、服务发现、服务容错、mock 服务
  * loadbalance
    * RandomLoadbalance



## 可以拓展的点
底层是 netty 、tomcat
序列化方法
负载均衡（一个接口有多个机器提供，选哪个）
服务注册与发现
mock
服务容错


