# inspection-robot

中科院矿井轨道机器人项目

## 项目产品原型

- [客户端端原型](https://org.modao.cc/app/9b39d658958156c4f97a58c6d9c6503ac3e411e6)
- [后台原型](https://org.modao.cc/app/5f4ad3618b178c00faac01bdf728c40411ee0d15)

## 快速开始

### 核心依赖

| module                 | version       |
|------------------------|---------------|
| Spring Boot            | 2.2.1.RELEASE |
| Spring Security OAuth2 | 2.2.1.RELEASE |
| lombok                 | 1.18.10       |
| Mybatis Plus           | 3.4.0         |
| jedis                  | 3.1.0         |
| redisson               | 3.14.0        |
| hutool                 | 5.3.7         |

### 模块说明

```lua
service
├── business             -- 业务逻辑模块
│     ├── appuser           -- 客户端用户模块
│     ├── historydata       -- 历史数据模块(历史视频/历史巡检)
│     ├── syscode           -- 系统字典模块(设备管理/巡检轨迹/系统手册)
│     ├── syslog            -- 日志相关模块(报警日志/环境日志/运行日志/视频日志/用户日志)
│     └── sysuser           -- 后台管理员用户与权限模块
├── commons              -- 全局公共组件模块
│     ├── boot-core         -- 全局依赖核心包
│     ├── captcha           -- 验证码生成校验封装
│     ├── excel             -- Excel解析导入导出封装
│     └── localfile         -- 本地文件存储上传下载封装
├── logs                 -- 项目日志目录
└── main                 -- 程序启动入口
    └── src
        └── main
              ├── java      -- 程序启动类目录
              └── resources -- 项目配置文件目录
```

### 运行/部署

运行环境
---

- CentOS 7+
- java 8
- Redis 6.2+
- Mysql 5.6

项目部署
---

- 修改配置文件 [application-prod.yml](main/src/main/resources/application-prod.yml)
  ```yml
  site:
    explanation: 轨道机器人-生产环境
    host: http://47.97.223.112:60800/ #接口域名
    file-upload-dir: /data/java/file  #上传文件保存目录

  spring:
    datasource: #数据库配置
      url: jdbc:p6spy:mysql://127.0.0.1:33066/InspectionRobot?useSSL=false&characterEncoding=utf8
      username: root
      password: 123456
    redis: 
      database: 0
      host: 127.0.0.1
      port: 6379
      password: 123456

  spring.resources.static-locations: file:/data/java #静态资源目录(当前未配置域名,将管理后台前端打包文件放入此目录通过springboot访问)
  ```
- Maven打包 `mvn clean install` 对parent根项目打包
- 打包后 jar 包本地地址`main/target/InspectionRobot-main.jar`
- 将打包好的 jar 包上传至服务器, 运行启动命令 `nohup java -jar -server Xms512m -Xmx1024m InspectionRobot-main.jar --spring.profiles.active=prod > /dev/null 2>&1 &`
