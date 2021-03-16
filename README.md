# inspection-robot
中科院矿井轨道机器人项目

项目原型
---
- [客户端端原型](https://org.modao.cc/app/9b39d658958156c4f97a58c6d9c6503ac3e411e6)
- [后台原型](https://org.modao.cc/app/5f4ad3618b178c00faac01bdf728c40411ee0d15)


运行环境
---
- CentOS 7+
- java 8
- Redis 6.2+
- Mysql 5.6


项目部署
---
- 修改配置文件 [application-prod.yml](main/src/main/resources/application-prod.yml)
    ```
      site:
        explanation: 轨道机器人-生产环境
        host: http://47.97.223.112:60800/ #接口域名
        file-upload-dir: /data/java/file  #上传文件保存目录
      
      spring:
        datasource: #数据库配置
          url: jdbc:p6spy:mysql://127.0.0.1:33066/InspectionRobot?useSSL=false&characterEncoding=utf8 
          username: root
          password: 123456
        redis: #数据库配置
          database: 0
          host: 127.0.0.1
          port: 6379
          password: 123456
      
      spring.resources.static-locations: file:/data/java #静态资源目录(当前未配置域名,将管理后台前端打包文件放入此目录通过springboot访问)
      ```
- Maven打包 `mvn clean install` 对parent根项目打包
- 打包后 jar 包本地地址`main/target/InspectionRobot-main.jar`
- 将打包好的 jar 包上传至服务器, 运行启动命令 `nohup java -jar -server Xms512m -Xmx1024m InspectionRobot-main.jar --spring.profiles.active=prod > /dev/null 2>&1 &`
