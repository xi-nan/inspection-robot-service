
**轨道机器人 API 接口文档** 


**简介**：[在线接口地址](http://47.97.223.112:60800/doc.html)


**HOST**:47.97.223.112:60800


**联系人**:


**Version**:1.0

**接口路径**：/v2/api-docs


# 后台-APP用户

## 添加用户


**接口描述**:


**接口地址**:`/sys/app/user/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"enabled": true,
	"id": 0,
	"mobile": "",
	"nickName": "",
	"password": "",
	"username": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |AppUserSaveParam  | AppUserSaveParam   |

**schema属性说明**



**AppUserSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|enabled| 是否启用  | body | true |boolean  |    |
|id| ID  | body | false |integer(int64)  |    |
|mobile| 手机号  | body | false |string  |    |
|nickName| 昵称  | body | false |string  |    |
|password| 密码  | body | true |string  |    |
|username| 登录名  | body | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取用户详情 by id


**接口描述**:


**接口地址**:`/sys/app/user/id/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"createTime": 0,
	"enabled": true,
	"id": 0,
	"mobile": "",
	"nickName": "",
	"username": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|createTime| 创建时间，时间戳 毫秒  |integer(int64)  | integer(int64)   |
|enabled| 是否启用  |boolean  |    |
|id| ID  |integer(int64)  | integer(int64)   |
|mobile| 手机  |string  |    |
|nickName| 昵称  |string  |    |
|username| 账号名  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |AppUserDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除用户 by id


**接口描述**:


**接口地址**:`/sys/app/user/id/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 获取列表(分页)


**接口描述**:


**接口地址**:`/sys/app/user/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"mobileLike": "",
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"usernameLike": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |AppUserPageParam  | AppUserPageParam   |

**schema属性说明**



**AppUserPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|mobileLike| 手机  | body | false |string  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|usernameLike| 用户名  | body | false |string  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"createTime": 0,
			"enabled": true,
			"id": 0,
			"mobile": "",
			"nickName": "",
			"username": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | AppUserDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**AppUserDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime | 创建时间，时间戳 毫秒   |integer(int64)  |    |
|enabled | 是否启用   |boolean  |    |
|id | ID   |integer(int64)  |    |
|mobile | 手机   |string  |    |
|nickName | 昵称   |string  |    |
|username | 账号名   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«AppUserDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改用户信息


**接口描述**:


**接口地址**:`/sys/app/user/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"enabled": true,
	"id": 0,
	"mobile": "",
	"nickName": "",
	"password": "",
	"username": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |AppUserSaveParam  | AppUserSaveParam   |

**schema属性说明**



**AppUserSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|enabled| 是否启用  | body | true |boolean  |    |
|id| ID  | body | false |integer(int64)  |    |
|mobile| 手机号  | body | false |string  |    |
|nickName| 昵称  | body | false |string  |    |
|password| 密码  | body | true |string  |    |
|username| 登录名  | body | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 启用/禁用 用户


**接口描述**:


**接口地址**:`/sys/app/user/{id}/enabled/{enabled}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|enabled| enabled  | path | true |boolean  |    |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 重置密码为 a12345


**接口描述**:


**接口地址**:`/sys/app/user/{id}/resetPwd`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-历史巡检相关

## 获取巡检电池记录


**接口描述**:


**接口地址**:`/sys/history/inspection/battery/{inspectionId}/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json
[
	{
		"availableTime": "",
		"electricCurrent": "",
		"electricity": "",
		"logTime": 0,
		"voltage": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|availableTime| 可用时间  |string  |    |
|electricCurrent| 电流  |string  |    |
|electricity| 当前电量  |string  |    |
|logTime| 数值记录时间  |integer(int64)  | integer(int64)   |
|voltage| 电压  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |InspectionBatteryDTO|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 重试所有被中断或执行失败的巡检视频转码操作


**接口描述**:


**接口地址**:`/sys/history/inspection/continueRecodeVideo`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检环境记录


**接口描述**:


**接口地址**:`/sys/history/inspection/environment/{inspectionId}/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json
[
	{
		"butane": "",
		"carbonMonoxide": "",
		"ethane": "",
		"humidity": "",
		"hydrogenSulfide": "",
		"logTime": 0,
		"methane": "",
		"propane": "",
		"temperature": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|butane| 丁烷  |string  |    |
|carbonMonoxide| 一氧化碳  |string  |    |
|ethane| 乙烷  |string  |    |
|humidity| 湿度  |string  |    |
|hydrogenSulfide| 硫化氢  |string  |    |
|logTime| 数值记录时间  |integer(int64)  | integer(int64)   |
|methane| 甲烷  |string  |    |
|propane| 丙烷  |string  |    |
|temperature| 温度  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |InspectionEnvironmentDTO|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检列表


**接口描述**:


**接口地址**:`/sys/history/inspection/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"endTime": 0,
	"equipmentId": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"startTime": 0
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |HistoryInspectionPageParam  | HistoryInspectionPageParam   |

**schema属性说明**



**HistoryInspectionPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|endTime| 上传时间段搜索 结束  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|startTime| 上传时间段搜索 开始  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"createTime": 0,
			"creator": "",
			"deleted": 0,
			"enabled": true,
			"endTime": 0,
			"equipmentId": 0,
			"id": 0,
			"modifier": "",
			"modifyTime": 0,
			"startTime": 0
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | HistoryInspectionDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**HistoryInspectionDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime |    |integer(int64)  |    |
|creator |    |string  |    |
|deleted |    |integer(int32)  |    |
|enabled |    |boolean  |    |
|endTime | 巡检结束时间   |integer(int64)  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|id | 巡检ID   |integer(int64)  |    |
|modifier |    |string  |    |
|modifyTime |    |integer(int64)  |    |
|startTime | 巡检开始时间   |integer(int64)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«HistoryInspectionDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检照片列表


**接口描述**:


**接口地址**:`/sys/history/inspection/photo/{inspectionId}/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检速度记录


**接口描述**:


**接口地址**:`/sys/history/inspection/speed/{inspectionId}/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json
[
	{
		"logTime": 0,
		"speed": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|logTime| 数值记录时间  |integer(int64)  | integer(int64)   |
|speed| 当前速度  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |InspectionSpeedDTO|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检轨迹,返回文件ID


**接口描述**:


**接口地址**:`/sys/history/inspection/trajectory/{inspectionId}/{type}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |
|type| type,可用值:PRESET,ACTUAL  | path | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检视频,返回文件ID


**接口描述**:


**接口地址**:`/sys/history/inspection/video/{inspectionId}/{type}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |
|type| type,可用值:VISIBLE,THERMOGRAPHY  | path | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-历史视频

## 重试所有被中断或执行失败的历史视频转码操作


**接口描述**:


**接口地址**:`/sys/history/video/continueRecodeVideo`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除视频


**接口描述**:


**接口地址**:`/sys/history/video/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 获取视频列表(分页)


**接口描述**:


**接口地址**:`/sys/history/video/{type}/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"upTimeEnd": 0,
	"upTimeStart": 0,
	"videoNameLike": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |HistoryVideoPageParam  | HistoryVideoPageParam   |
|type| type,可用值:VISIBLE,THERMOGRAPHY  | path | true |string  |    |

**schema属性说明**



**HistoryVideoPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|upTimeEnd| 上传时间段搜索 结束  | body | false |integer(int64)  |    |
|upTimeStart| 上传时间段搜索 开始  | body | false |integer(int64)  |    |
|videoNameLike| 视频名称搜索  | body | false |string  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"fileId": 0,
			"id": 0,
			"isRecode": true,
			"startTime": 0,
			"videoDuration": "",
			"videoName": "",
			"videoType": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | HistoryVideoDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**HistoryVideoDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|fileId | 视频文件ID   |integer(int64)  |    |
|id | 视频记录ID   |integer(int64)  |    |
|isRecode | 视频文件已转码,未转码播放时提示正在转码   |boolean  |    |
|startTime | 开始录制时间   |integer(int64)  |    |
|videoDuration | 视频时长   |string  |    |
|videoName | 视频名称   |string  |    |
|videoType | 视频类型,可用值:VISIBLE,THERMOGRAPHY   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«HistoryVideoDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-巡检轨迹管理

## 添加


**接口描述**:


**接口地址**:`/sys/inspectionTrack/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"coordinateData": "",
	"id": 0,
	"rfidData": "",
	"trackName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |InspectionTrackSaveParam  | InspectionTrackSaveParam   |

**schema属性说明**



**InspectionTrackSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|coordinateData| 坐标数据  | body | false |string  |    |
|id| ID  | body | false |integer(int64)  |    |
|rfidData| RFID数据  | body | false |string  |    |
|trackName| 轨迹名称  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取详情 by id


**接口描述**:


**接口地址**:`/sys/inspectionTrack/id/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"coordinateData": "",
	"createTime": 0,
	"enabled": true,
	"id": 0,
	"rfidData": "",
	"trackName": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|coordinateData| 坐标数据  |string  |    |
|createTime| 创建时间  |integer(int64)  | integer(int64)   |
|enabled| 是否启用  |boolean  |    |
|id| ID  |integer(int64)  | integer(int64)   |
|rfidData| RFID数据  |string  |    |
|trackName| 轨迹名称  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |InspectionTrackDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除 by id


**接口描述**:


**接口地址**:`/sys/inspectionTrack/id/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 获取列表(分页)


**接口描述**:


**接口地址**:`/sys/inspectionTrack/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"createTimeEnd": 0,
	"createTimeStart": 0,
	"current": 1,
	"enabled": true,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"trackName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |InspectionTrackPageParam  | InspectionTrackPageParam   |

**schema属性说明**



**InspectionTrackPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTimeEnd| 创建时间筛选-结束  | body | false |integer(int64)  |    |
|createTimeStart| 创建时间筛选-开始  | body | false |integer(int64)  |    |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|enabled| 是否启用  | body | false |boolean  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|trackName| 轨迹名称  | body | false |string  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"coordinateData": "",
			"createTime": 0,
			"enabled": true,
			"id": 0,
			"rfidData": "",
			"trackName": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | InspectionTrackDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**InspectionTrackDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|coordinateData | 坐标数据   |string  |    |
|createTime | 创建时间   |integer(int64)  |    |
|enabled | 是否启用   |boolean  |    |
|id | ID   |integer(int64)  |    |
|rfidData | RFID数据   |string  |    |
|trackName | 轨迹名称   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«InspectionTrackDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改保存


**接口描述**:


**接口地址**:`/sys/inspectionTrack/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"coordinateData": "",
	"id": 0,
	"rfidData": "",
	"trackName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |InspectionTrackSaveParam  | InspectionTrackSaveParam   |

**schema属性说明**



**InspectionTrackSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|coordinateData| 坐标数据  | body | false |string  |    |
|id| ID  | body | false |integer(int64)  |    |
|rfidData| RFID数据  | body | false |string  |    |
|trackName| 轨迹名称  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 启用/禁用


**接口描述**:


**接口地址**:`/sys/inspectionTrack/{id}/enabled/{enabled}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|enabled| enabled  | path | true |boolean  |    |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-报警日志

## 查看详情


**接口描述**:


**接口地址**:`/sys/log/alarm/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"alarmType": "",
	"alarmTypeStr": "",
	"content": "",
	"equipmentId": 0,
	"location": "",
	"logTime": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|alarmType| 报警类型,可用值:ROBOT_STATUS,ENVIRONMENTAL_PARAMETERS,CAMERA_MONITORING_ALARM  |string  |    |
|alarmTypeStr| 报警位置  |string  |    |
|content| 日志内容  |string  |    |
|equipmentId| 设备ID  |integer(int64)  | integer(int64)   |
|location| 报警位置中文描述  |string  |    |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogAlarmDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/sys/log/alarm/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"alarmType": "",
			"alarmTypeStr": "",
			"content": "",
			"equipmentId": 0,
			"location": "",
			"logTime": 0
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogAlarmDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogAlarmDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|alarmType | 报警类型,可用值:ROBOT_STATUS,ENVIRONMENTAL_PARAMETERS,CAMERA_MONITORING_ALARM   |string  |    |
|alarmTypeStr | 报警位置   |string  |    |
|content | 日志内容   |string  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|location | 报警位置中文描述   |string  |    |
|logTime | 日志记录时间   |integer(int64)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogAlarmDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-权限

## 【添加】权限菜单


**接口描述**:


**接口地址**:`/sys/permission/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"code": "",
	"id": 0,
	"level": 0,
	"name": "",
	"parentId": 0,
	"parentLevel": 0,
	"sortOrder": 0,
	"type": 0,
	"url": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |PermissionSaveParam  | PermissionSaveParam   |

**schema属性说明**



**PermissionSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|code| 资源码  | body | false |string  |    |
|id| 权限ID  | body | false |integer(int64)  |    |
|level| 菜单级别：eg:系统权限菜单为0级菜单  | body | false |integer(int32)  |    |
|name| 菜单名称  | body | false |string  |    |
|parentId| 父级ID  | body | false |integer(int64)  |    |
|parentLevel| 父级菜单级别  | body | false |integer(int32)  |    |
|sortOrder| 排序参数：支持小数，越小越靠前  | body | false |number  |    |
|type| 菜单类型:0:页面，1：按钮  | body | false |integer(int32)  |    |
|url| URL  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 【编辑】权限菜单


**接口描述**:


**接口地址**:`/sys/permission/edit`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"code": "",
	"id": 0,
	"level": 0,
	"name": "",
	"parentId": 0,
	"parentLevel": 0,
	"sortOrder": 0,
	"type": 0,
	"url": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |PermissionSaveParam  | PermissionSaveParam   |

**schema属性说明**



**PermissionSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|code| 资源码  | body | false |string  |    |
|id| 权限ID  | body | false |integer(int64)  |    |
|level| 菜单级别：eg:系统权限菜单为0级菜单  | body | false |integer(int32)  |    |
|name| 菜单名称  | body | false |string  |    |
|parentId| 父级ID  | body | false |integer(int64)  |    |
|parentLevel| 父级菜单级别  | body | false |integer(int32)  |    |
|sortOrder| 排序参数：支持小数，越小越靠前  | body | false |number  |    |
|type| 菜单类型:0:页面，1：按钮  | body | false |integer(int32)  |    |
|url| URL  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取资源码列表


**接口描述**:


**接口地址**:`/sys/permission/getMenuResource`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取权限列表


**接口描述**:


**接口地址**:`/sys/permission/list/all`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json
[
	{
		"children": [
			{
				"children": [],
				"code": "",
				"id": 0,
				"level": 0,
				"name": "",
				"parentId": 0,
				"sortOrder": 0,
				"type": 0,
				"url": ""
			}
		],
		"code": "",
		"id": 0,
		"level": 0,
		"name": "",
		"parentId": 0,
		"sortOrder": 0,
		"type": 0,
		"url": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|children| 子权限项  |array  | PermissionDTO   |
|code| 资源码  |string  |    |
|id|   |integer(int64)  | integer(int64)   |
|level| 菜单级别：eg:系统权限菜单为0级菜单  |integer(int32)  | integer(int32)   |
|name| 菜单名称  |string  |    |
|parentId| 父级ID  |integer(int64)  | integer(int64)   |
|sortOrder| 排序参数：支持小数，越小越靠前  |integer(int32)  | integer(int32)   |
|type| 菜单类型:0:页面，1：按钮  |integer(int32)  | integer(int32)   |
|url| URL  |string  |    |



**schema属性说明**




**PermissionDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|children | 子权限项   |array  | PermissionDTO   |
|code | 资源码   |string  |    |
|id |    |integer(int64)  |    |
|level | 菜单级别：eg:系统权限菜单为0级菜单   |integer(int32)  |    |
|name | 菜单名称   |string  |    |
|parentId | 父级ID   |integer(int64)  |    |
|sortOrder | 排序参数：支持小数，越小越靠前   |integer(int32)  |    |
|type | 菜单类型:0:页面，1：按钮   |integer(int32)  |    |
|url | URL   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |PermissionDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 查询自己拥有的权限


**接口描述**:


**接口地址**:`/sys/permission/list/self`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json
[
	{
		"children": [
			{
				"children": [],
				"code": "",
				"id": 0,
				"level": 0,
				"name": "",
				"parentId": 0,
				"sortOrder": 0,
				"type": 0,
				"url": ""
			}
		],
		"code": "",
		"id": 0,
		"level": 0,
		"name": "",
		"parentId": 0,
		"sortOrder": 0,
		"type": 0,
		"url": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|children| 子权限项  |array  | PermissionDTO   |
|code| 资源码  |string  |    |
|id|   |integer(int64)  | integer(int64)   |
|level| 菜单级别：eg:系统权限菜单为0级菜单  |integer(int32)  | integer(int32)   |
|name| 菜单名称  |string  |    |
|parentId| 父级ID  |integer(int64)  | integer(int64)   |
|sortOrder| 排序参数：支持小数，越小越靠前  |integer(int32)  | integer(int32)   |
|type| 菜单类型:0:页面，1：按钮  |integer(int32)  | integer(int32)   |
|url| URL  |string  |    |



**schema属性说明**




**PermissionDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|children | 子权限项   |array  | PermissionDTO   |
|code | 资源码   |string  |    |
|id |    |integer(int64)  |    |
|level | 菜单级别：eg:系统权限菜单为0级菜单   |integer(int32)  |    |
|name | 菜单名称   |string  |    |
|parentId | 父级ID   |integer(int64)  |    |
|sortOrder | 排序参数：支持小数，越小越靠前   |integer(int32)  |    |
|type | 菜单类型:0:页面，1：按钮   |integer(int32)  |    |
|url | URL   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |PermissionDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 【删除】权限菜单


**接口描述**:


**接口地址**:`/sys/permission/{ids}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids| ids  | path | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# 后台-环境日志

## 查看详情


**接口描述**:


**接口地址**:`/sys/log/environmental/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"butane": "",
	"carbonMonoxide": "",
	"equipmentId": 0,
	"ethane": "",
	"humidity": "",
	"hydrogenSulfide": "",
	"logTime": 0,
	"methane": "",
	"propane": "",
	"temperature": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|butane| 丁烷  |string  |    |
|carbonMonoxide| 一氧化碳  |string  |    |
|equipmentId| 设备ID  |integer(int64)  | integer(int64)   |
|ethane| 乙烷  |string  |    |
|humidity| 湿度  |string  |    |
|hydrogenSulfide| 硫化氢  |string  |    |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |
|methane| 甲烷  |string  |    |
|propane| 丙烷  |string  |    |
|temperature| 温度  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogEnvironmentalDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/sys/log/environmental/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"butane": "",
			"carbonMonoxide": "",
			"equipmentId": 0,
			"ethane": "",
			"humidity": "",
			"hydrogenSulfide": "",
			"logTime": 0,
			"methane": "",
			"propane": "",
			"temperature": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogEnvironmentalDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogEnvironmentalDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|butane | 丁烷   |string  |    |
|carbonMonoxide | 一氧化碳   |string  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|ethane | 乙烷   |string  |    |
|humidity | 湿度   |string  |    |
|hydrogenSulfide | 硫化氢   |string  |    |
|logTime | 日志记录时间   |integer(int64)  |    |
|methane | 甲烷   |string  |    |
|propane | 丙烷   |string  |    |
|temperature | 温度   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogEnvironmentalDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-用户日志

## 查看详情


**接口描述**:


**接口地址**:`/sys/log/sysUser/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"content": "",
	"creator": "",
	"logTime": 0,
	"operationType": "",
	"operationTypeStr": "",
	"userType": "",
	"userTypeStr": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|content| 日志内容  |string  |    |
|creator| 操作用户  |string  |    |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |
|operationType| 操作类型,可用值:ADD_USER,MODIFY_USER,UPD_PASSWORD,RESET_PASSWORD,DEL_USER  |string  |    |
|operationTypeStr| 操作类型中文描述  |string  |    |
|userType| 用户类型,可用值:ADMIN,APP  |string  |    |
|userTypeStr| 用户类型中文描述  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogSysUserDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/sys/log/sysUser/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"content": "",
			"creator": "",
			"logTime": 0,
			"operationType": "",
			"operationTypeStr": "",
			"userType": "",
			"userTypeStr": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogSysUserDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogSysUserDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|content | 日志内容   |string  |    |
|creator | 操作用户   |string  |    |
|logTime | 日志记录时间   |integer(int64)  |    |
|operationType | 操作类型,可用值:ADD_USER,MODIFY_USER,UPD_PASSWORD,RESET_PASSWORD,DEL_USER   |string  |    |
|operationTypeStr | 操作类型中文描述   |string  |    |
|userType | 用户类型,可用值:ADMIN,APP   |string  |    |
|userTypeStr | 用户类型中文描述   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogSysUserDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-管理员用户

## 获取当前登陆用户信息


**接口描述**:


**接口地址**:`/sys/admin/`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"createTime": 0,
	"enabled": true,
	"id": 0,
	"mobile": "",
	"nickName": "",
	"roleId": 0,
	"roleName": "",
	"username": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|createTime| 创建时间，时间戳 毫秒  |integer(int64)  | integer(int64)   |
|enabled| 是否启用  |boolean  |    |
|id| ID  |integer(int64)  | integer(int64)   |
|mobile| 手机  |string  |    |
|nickName| 昵称  |string  |    |
|roleId| 所属角色ID  |integer(int64)  | integer(int64)   |
|roleName| 所属角色名称  |string  |    |
|username| 账号名  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |SysUserDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 添加用户


**接口描述**:


**接口地址**:`/sys/admin/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"enabled": true,
	"id": 0,
	"mobile": "",
	"nickName": "",
	"password": "",
	"roleId": 0,
	"username": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |SysUserSaveParam  | SysUserSaveParam   |

**schema属性说明**



**SysUserSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|enabled| 是否启用  | body | true |boolean  |    |
|id| ID  | body | false |integer(int64)  |    |
|mobile| 手机号  | body | false |string  |    |
|nickName| 昵称  | body | false |string  |    |
|password| 密码  | body | true |string  |    |
|roleId| 角色ID  | body | true |integer(int64)  |    |
|username| 登录名  | body | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取用户详情 by id


**接口描述**:


**接口地址**:`/sys/admin/id/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"createTime": 0,
	"enabled": true,
	"id": 0,
	"mobile": "",
	"nickName": "",
	"roleId": 0,
	"roleName": "",
	"username": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|createTime| 创建时间，时间戳 毫秒  |integer(int64)  | integer(int64)   |
|enabled| 是否启用  |boolean  |    |
|id| ID  |integer(int64)  | integer(int64)   |
|mobile| 手机  |string  |    |
|nickName| 昵称  |string  |    |
|roleId| 所属角色ID  |integer(int64)  | integer(int64)   |
|roleName| 所属角色名称  |string  |    |
|username| 账号名  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |SysUserDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 管理员登陆


**接口描述**:


**接口地址**:`/sys/admin/login`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"password": "",
	"username": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |SysUserLoginParam  | SysUserLoginParam   |

**schema属性说明**



**SysUserLoginParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|password| 密码  | body | true |string  |    |
|username| 登录名  | body | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取管理员列表(分页)


**接口描述**:


**接口地址**:`/sys/admin/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"roleId": 0,
	"size": 10,
	"usernameLike": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |SysUserPageParam  | SysUserPageParam   |

**schema属性说明**



**SysUserPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|roleId| 所属角色ID  | body | false |integer(int64)  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|usernameLike| 用户名  | body | false |string  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"createTime": 0,
			"enabled": true,
			"id": 0,
			"mobile": "",
			"nickName": "",
			"roleId": 0,
			"roleName": "",
			"username": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | SysUserDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**SysUserDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime | 创建时间，时间戳 毫秒   |integer(int64)  |    |
|enabled | 是否启用   |boolean  |    |
|id | ID   |integer(int64)  |    |
|mobile | 手机   |string  |    |
|nickName | 昵称   |string  |    |
|roleId | 所属角色ID   |integer(int64)  |    |
|roleName | 所属角色名称   |string  |    |
|username | 账号名   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«SysUserDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改当前登陆账号密码


**接口描述**:


**接口地址**:`/sys/admin/pwd`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"newPassword": "",
	"oldPassword": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |SysUserUpdPwdParam  | SysUserUpdPwdParam   |

**schema属性说明**



**SysUserUpdPwdParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|newPassword| 新密码  | body | true |string  |    |
|oldPassword| 旧密码  | body | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存用户信息


**接口描述**:


**接口地址**:`/sys/admin/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"enabled": true,
	"id": 0,
	"mobile": "",
	"nickName": "",
	"password": "",
	"roleId": 0,
	"username": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |SysUserSaveParam  | SysUserSaveParam   |

**schema属性说明**



**SysUserSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|enabled| 是否启用  | body | true |boolean  |    |
|id| ID  | body | false |integer(int64)  |    |
|mobile| 手机号  | body | false |string  |    |
|nickName| 昵称  | body | false |string  |    |
|password| 密码  | body | true |string  |    |
|roleId| 角色ID  | body | true |integer(int64)  |    |
|username| 登录名  | body | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除用户


**接口描述**:


**接口地址**:`/sys/admin/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 启用/禁用 用户


**接口描述**:


**接口地址**:`/sys/admin/{id}/enabled/{enabled}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|enabled| enabled  | path | true |boolean  |    |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 重置密码为 a12345


**接口描述**:


**接口地址**:`/sys/admin/{id}/resetPwd`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-系统手册

## 添加问题


**接口描述**:


**接口地址**:`/sys/questions/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"answer": "",
	"code": "",
	"question": "",
	"sort": 0
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |QuestionSaveParam  | QuestionSaveParam   |

**schema属性说明**



**QuestionSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|answer| 答案  | body | false |string  |    |
|code| 问题code  | body | false |string  |    |
|question| 问题  | body | false |string  |    |
|sort| 排序  | body | false |integer(int64)  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取问题详情


**接口描述**:


**接口地址**:`/sys/questions/code/{code}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|code| code  | path | true |string  |    |

**响应示例**:

```json
{
	"answer": "",
	"code": "",
	"question": "",
	"sort": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|answer| 答案  |string  |    |
|code| 问题code  |string  |    |
|question| 问题  |string  |    |
|sort| 排序  |integer(int64)  | integer(int64)   |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |QuestionDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改问题


**接口描述**:


**接口地址**:`/sys/questions/edit`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"answer": "",
	"code": "",
	"question": "",
	"sort": 0
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |QuestionSaveParam  | QuestionSaveParam   |

**schema属性说明**



**QuestionSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|answer| 答案  | body | false |string  |    |
|code| 问题code  | body | false |string  |    |
|question| 问题  | body | false |string  |    |
|sort| 排序  | body | false |integer(int64)  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取问题列表(分页)


**接口描述**:


**接口地址**:`/sys/questions/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"searchKey": "",
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |QuestionPageParam  | QuestionPageParam   |

**schema属性说明**



**QuestionPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|searchKey| 关键词查找  | body | false |string  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"answer": "",
			"code": "",
			"question": "",
			"sort": 0
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | QuestionDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**QuestionDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|answer | 答案   |string  |    |
|code | 问题code   |string  |    |
|question | 问题   |string  |    |
|sort | 排序   |integer(int64)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«QuestionDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除问题


**接口描述**:


**接口地址**:`/sys/questions/{code}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|code| code  | path | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# 后台-视频日志

## 查看详情


**接口描述**:


**接口地址**:`/sys/log/video/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"creator": "",
	"equipmentId": 0,
	"logTime": 0,
	"operationType": "",
	"operationTypeStr": "",
	"videoDuration": "",
	"videoName": "",
	"videoType": "",
	"videoTypeStr": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|creator| 操作用户  |string  |    |
|equipmentId| 设备ID  |integer(int64)  | integer(int64)   |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |
|operationType| 操作类型,可用值:ADD,DEL  |string  |    |
|operationTypeStr| 操作类型中文描述  |string  |    |
|videoDuration| 视频时长  |string  |    |
|videoName| 视频名称  |string  |    |
|videoType| 视频类型,可用值:VISIBLE,THERMOGRAPHY  |string  |    |
|videoTypeStr| 视频类型中文描述  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogVideoDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/sys/log/video/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"creator": "",
			"equipmentId": 0,
			"logTime": 0,
			"operationType": "",
			"operationTypeStr": "",
			"videoDuration": "",
			"videoName": "",
			"videoType": "",
			"videoTypeStr": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogVideoDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogVideoDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|creator | 操作用户   |string  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|logTime | 日志记录时间   |integer(int64)  |    |
|operationType | 操作类型,可用值:ADD,DEL   |string  |    |
|operationTypeStr | 操作类型中文描述   |string  |    |
|videoDuration | 视频时长   |string  |    |
|videoName | 视频名称   |string  |    |
|videoType | 视频类型,可用值:VISIBLE,THERMOGRAPHY   |string  |    |
|videoTypeStr | 视频类型中文描述   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogVideoDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-角色

## 添加角色


**接口描述**:


**接口地址**:`/sys/role/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"id": 1,
	"permissionList": [],
	"roleDesc": "",
	"roleName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |RoleSaveParam  | RoleSaveParam   |

**schema属性说明**



**RoleSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| 角色ID！  | body | false |integer(int64)  |    |
|permissionList| 角色权限集合！  | body | false |array  |    |
|roleDesc| 角色描述！  | body | false |string  |    |
|roleName| 角色名称！  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除角色


**接口描述**:


**接口地址**:`/sys/role/delete/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 获取列表


**接口描述**:


**接口地址**:`/sys/role/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"nameLike": "",
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |RolePageParam  | RolePageParam   |

**schema属性说明**



**RolePageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|nameLike| 名称  | body | false |string  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"defaultRole": true,
			"id": 0,
			"permissionIds": [],
			"roleDesc": "",
			"roleName": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | RoleDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**RoleDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|defaultRole | 默认角色设置   |boolean  |    |
|id |    |integer(int64)  |    |
|permissionIds | 角色拥有的权限ID数组   |array  |    |
|roleDesc | 角色描述   |string  |    |
|roleName | 角色名称   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«RoleDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 编辑角色


**接口描述**:


**接口地址**:`/sys/role/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"id": 1,
	"permissionList": [],
	"roleDesc": "",
	"roleName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |RoleSaveParam  | RoleSaveParam   |

**schema属性说明**



**RoleSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| 角色ID！  | body | false |integer(int64)  |    |
|permissionList| 角色权限集合！  | body | false |array  |    |
|roleDesc| 角色描述！  | body | false |string  |    |
|roleName| 角色名称！  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取详情


**接口描述**:


**接口地址**:`/sys/role/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"defaultRole": true,
	"id": 0,
	"permissionIds": [],
	"roleDesc": "",
	"roleName": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|defaultRole| 默认角色设置  |boolean  |    |
|id|   |integer(int64)  | integer(int64)   |
|permissionIds| 角色拥有的权限ID数组  |array  |    |
|roleDesc| 角色描述  |string  |    |
|roleName| 角色名称  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |RoleDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 后台-设备管理

## 添加设备


**接口描述**:


**接口地址**:`/sys/equipment/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"funcDescr": "",
	"id": 0,
	"plcIp": "",
	"plcPort": "",
	"scopeDescr": "",
	"tcAccount": "",
	"tcIp": "",
	"tcPort": "",
	"tcPwd": "",
	"userId": 0,
	"vcAccount": "",
	"vcIp": "",
	"vcPort": "",
	"vcPwd": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |EquipmentSaveParam  | EquipmentSaveParam   |

**schema属性说明**



**EquipmentSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|funcDescr| 功能描述  | body | false |string  |    |
|id| 设备ID  | body | false |integer(int64)  |    |
|plcIp| PLC IP  | body | false |string  |    |
|plcPort| PLC端口  | body | false |string  |    |
|scopeDescr| 范围描述  | body | false |string  |    |
|tcAccount| 热成像摄像头 密码  | body | false |string  |    |
|tcIp| 热成像摄像头 IP  | body | false |string  |    |
|tcPort| 热成像摄像头 端口  | body | false |string  |    |
|tcPwd| 可见光摄像头 密码  | body | false |string  |    |
|userId| 关联账号  | body | false |integer(int64)  |    |
|vcAccount| 可见光摄像头 密码  | body | false |string  |    |
|vcIp| 可见光摄像头 IP  | body | false |string  |    |
|vcPort| 可见光摄像头 端口  | body | false |string  |    |
|vcPwd| 可见光摄像头 密码  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 查看详情


**接口描述**:


**接口地址**:`/sys/equipment/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"enabled": true,
	"funcDescr": "",
	"id": 0,
	"plcIp": "",
	"plcPort": "",
	"scopeDescr": "",
	"tcAccount": "",
	"tcIp": "",
	"tcPort": "",
	"tcPwd": "",
	"userId": 0,
	"username": "",
	"vcAccount": "",
	"vcIp": "",
	"vcPort": "",
	"vcPwd": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|enabled| 是否启用  |boolean  |    |
|funcDescr| 功能描述  |string  |    |
|id| 设备ID  |integer(int64)  | integer(int64)   |
|plcIp| PLC IP  |string  |    |
|plcPort| PLC端口  |string  |    |
|scopeDescr| 范围描述  |string  |    |
|tcAccount| 热成像摄像头 密码  |string  |    |
|tcIp| 热成像摄像头 IP  |string  |    |
|tcPort| 热成像摄像头 端口  |string  |    |
|tcPwd| 可见光摄像头 密码  |string  |    |
|userId| 关联账号  |integer(int64)  | integer(int64)   |
|username| 关联账号  |string  |    |
|vcAccount| 可见光摄像头 密码  |string  |    |
|vcIp| 可见光摄像头 IP  |string  |    |
|vcPort| 可见光摄像头 端口  |string  |    |
|vcPwd| 可见光摄像头 密码  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |EquipmentDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改设备


**接口描述**:


**接口地址**:`/sys/equipment/edit`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"funcDescr": "",
	"id": 0,
	"plcIp": "",
	"plcPort": "",
	"scopeDescr": "",
	"tcAccount": "",
	"tcIp": "",
	"tcPort": "",
	"tcPwd": "",
	"userId": 0,
	"vcAccount": "",
	"vcIp": "",
	"vcPort": "",
	"vcPwd": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |EquipmentSaveParam  | EquipmentSaveParam   |

**schema属性说明**



**EquipmentSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|funcDescr| 功能描述  | body | false |string  |    |
|id| 设备ID  | body | false |integer(int64)  |    |
|plcIp| PLC IP  | body | false |string  |    |
|plcPort| PLC端口  | body | false |string  |    |
|scopeDescr| 范围描述  | body | false |string  |    |
|tcAccount| 热成像摄像头 密码  | body | false |string  |    |
|tcIp| 热成像摄像头 IP  | body | false |string  |    |
|tcPort| 热成像摄像头 端口  | body | false |string  |    |
|tcPwd| 可见光摄像头 密码  | body | false |string  |    |
|userId| 关联账号  | body | false |integer(int64)  |    |
|vcAccount| 可见光摄像头 密码  | body | false |string  |    |
|vcIp| 可见光摄像头 IP  | body | false |string  |    |
|vcPort| 可见光摄像头 端口  | body | false |string  |    |
|vcPwd| 可见光摄像头 密码  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取设备列表(分页)


**接口描述**:


**接口地址**:`/sys/equipment/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"id": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"userSearch": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |EquipmentPageParam  | EquipmentPageParam   |

**schema属性说明**



**EquipmentPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|id| 设备ID  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|userSearch| 绑定账号搜索(用户名、昵称、手机号)  | body | false |string  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"enabled": true,
			"funcDescr": "",
			"id": 0,
			"plcIp": "",
			"plcPort": "",
			"scopeDescr": "",
			"tcAccount": "",
			"tcIp": "",
			"tcPort": "",
			"tcPwd": "",
			"userId": 0,
			"username": "",
			"vcAccount": "",
			"vcIp": "",
			"vcPort": "",
			"vcPwd": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | EquipmentDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**EquipmentDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|enabled | 是否启用   |boolean  |    |
|funcDescr | 功能描述   |string  |    |
|id | 设备ID   |integer(int64)  |    |
|plcIp | PLC IP   |string  |    |
|plcPort | PLC端口   |string  |    |
|scopeDescr | 范围描述   |string  |    |
|tcAccount | 热成像摄像头 密码   |string  |    |
|tcIp | 热成像摄像头 IP   |string  |    |
|tcPort | 热成像摄像头 端口   |string  |    |
|tcPwd | 可见光摄像头 密码   |string  |    |
|userId | 关联账号   |integer(int64)  |    |
|username | 关联账号   |string  |    |
|vcAccount | 可见光摄像头 密码   |string  |    |
|vcIp | 可见光摄像头 IP   |string  |    |
|vcPort | 可见光摄像头 端口   |string  |    |
|vcPwd | 可见光摄像头 密码   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«EquipmentDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除设备


**接口描述**:


**接口地址**:`/sys/equipment/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# 后台-设备运行日志

## 查看详情


**接口描述**:


**接口地址**:`/sys/log/equipmentRun/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"batteryInformation": "",
	"butane": "",
	"carbonMonoxide": "",
	"equipmentId": 0,
	"equipmentStatus": "",
	"ethane": "",
	"humidity": "",
	"hydrogenSulfide": "",
	"logTime": 0,
	"methane": "",
	"propane": "",
	"temperature": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|batteryInformation| 电池信息  |string  |    |
|butane| 丁烷  |string  |    |
|carbonMonoxide| 一氧化碳  |string  |    |
|equipmentId| 设备ID  |integer(int64)  | integer(int64)   |
|equipmentStatus| 设备状态,可用值:STANDBY,CHARGING,AT_WORK  |string  |    |
|ethane| 乙烷  |string  |    |
|humidity| 湿度  |string  |    |
|hydrogenSulfide| 硫化氢  |string  |    |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |
|methane| 甲烷  |string  |    |
|propane| 丙烷  |string  |    |
|temperature| 温度  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogEquipmentRunDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/sys/log/equipmentRun/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"batteryInformation": "",
			"butane": "",
			"carbonMonoxide": "",
			"equipmentId": 0,
			"equipmentStatus": "",
			"ethane": "",
			"humidity": "",
			"hydrogenSulfide": "",
			"logTime": 0,
			"methane": "",
			"propane": "",
			"temperature": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogEquipmentRunDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogEquipmentRunDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|batteryInformation | 电池信息   |string  |    |
|butane | 丁烷   |string  |    |
|carbonMonoxide | 一氧化碳   |string  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|equipmentStatus | 设备状态,可用值:STANDBY,CHARGING,AT_WORK   |string  |    |
|ethane | 乙烷   |string  |    |
|humidity | 湿度   |string  |    |
|hydrogenSulfide | 硫化氢   |string  |    |
|logTime | 日志记录时间   |integer(int64)  |    |
|methane | 甲烷   |string  |    |
|propane | 丙烷   |string  |    |
|temperature | 温度   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogEquipmentRunDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-历史巡检相关

## 获取巡检电池记录


**接口描述**:


**接口地址**:`/app/history/inspection/battery/{inspectionId}/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json
[
	{
		"availableTime": "",
		"electricCurrent": "",
		"electricity": "",
		"logTime": 0,
		"voltage": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|availableTime| 可用时间  |string  |    |
|electricCurrent| 电流  |string  |    |
|electricity| 当前电量  |string  |    |
|logTime| 数值记录时间  |integer(int64)  | integer(int64)   |
|voltage| 电压  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |InspectionBatteryDTO|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 上传巡检电池记录


**接口描述**:


**接口地址**:`/app/history/inspection/battery/{inspectionId}/save`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|file| file  | formData | false |file  |    |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检环境记录


**接口描述**:


**接口地址**:`/app/history/inspection/environment/{inspectionId}/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json
[
	{
		"butane": "",
		"carbonMonoxide": "",
		"ethane": "",
		"humidity": "",
		"hydrogenSulfide": "",
		"logTime": 0,
		"methane": "",
		"propane": "",
		"temperature": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|butane| 丁烷  |string  |    |
|carbonMonoxide| 一氧化碳  |string  |    |
|ethane| 乙烷  |string  |    |
|humidity| 湿度  |string  |    |
|hydrogenSulfide| 硫化氢  |string  |    |
|logTime| 数值记录时间  |integer(int64)  | integer(int64)   |
|methane| 甲烷  |string  |    |
|propane| 丙烷  |string  |    |
|temperature| 温度  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |InspectionEnvironmentDTO|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 上传巡检环境记录


**接口描述**:


**接口地址**:`/app/history/inspection/environment/{inspectionId}/save`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|file| file  | formData | false |file  |    |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检列表


**接口描述**:


**接口地址**:`/app/history/inspection/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"endTime": 0,
	"equipmentId": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"startTime": 0
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |HistoryInspectionPageParam  | HistoryInspectionPageParam   |

**schema属性说明**



**HistoryInspectionPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|endTime| 上传时间段搜索 结束  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|startTime| 上传时间段搜索 开始  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"createTime": 0,
			"creator": "",
			"deleted": 0,
			"enabled": true,
			"endTime": 0,
			"equipmentId": 0,
			"id": 0,
			"modifier": "",
			"modifyTime": 0,
			"startTime": 0
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | HistoryInspectionDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**HistoryInspectionDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime |    |integer(int64)  |    |
|creator |    |string  |    |
|deleted |    |integer(int32)  |    |
|enabled |    |boolean  |    |
|endTime | 巡检结束时间   |integer(int64)  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|id | 巡检ID   |integer(int64)  |    |
|modifier |    |string  |    |
|modifyTime |    |integer(int64)  |    |
|startTime | 巡检开始时间   |integer(int64)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«HistoryInspectionDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检照片列表


**接口描述**:


**接口地址**:`/app/history/inspection/photo/{inspectionId}/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存巡检照片


**接口描述**:


**接口地址**:`/app/history/inspection/photo/{inspectionId}/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|fileIds| fileIds  | query | true |array  | integer   |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 创建巡检记录


**接口描述**:


**接口地址**:`/app/history/inspection/save/{equipmentId}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|endTime| endTime  | query | true |integer  |    |
|equipmentId| equipmentId  | path | true |integer  |    |
|startTime| startTime  | query | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检速度记录


**接口描述**:


**接口地址**:`/app/history/inspection/speed/{inspectionId}/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json
[
	{
		"logTime": 0,
		"speed": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|logTime| 数值记录时间  |integer(int64)  | integer(int64)   |
|speed| 当前速度  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |InspectionSpeedDTO|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 上传巡检速度记录


**接口描述**:


**接口地址**:`/app/history/inspection/speed/{inspectionId}/save`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|file| file  | formData | false |file  |    |
|inspectionId| inspectionId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存巡检轨迹


**接口描述**:


**接口地址**:`/app/history/inspection/trajectory/{inspectionId}/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|actualFileId| actualFileId  | query | true |integer  |    |
|inspectionId| inspectionId  | path | true |integer  |    |
|presetFileId| presetFileId  | query | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检轨迹,返回文件ID


**接口描述**:


**接口地址**:`/app/history/inspection/trajectory/{inspectionId}/{type}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |
|type| type,可用值:PRESET,ACTUAL  | path | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存巡检视频


**接口描述**:


**接口地址**:`/app/history/inspection/video/{inspectionId}/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |
|thermographyVideoFileId| thermographyVideoFileId  | query | true |integer  |    |
|visibleVideoFileId| visibleVideoFileId  | query | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取巡检视频,返回文件ID


**接口描述**:


**接口地址**:`/app/history/inspection/video/{inspectionId}/{type}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|inspectionId| inspectionId  | path | true |integer  |    |
|type| type,可用值:VISIBLE,THERMOGRAPHY  | path | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-历史视频

## 删除视频


**接口描述**:


**接口地址**:`/app/history/video/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 获取视频列表(分页)


**接口描述**:


**接口地址**:`/app/history/video/{type}/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"upTimeEnd": 0,
	"upTimeStart": 0,
	"videoNameLike": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |HistoryVideoPageParam  | HistoryVideoPageParam   |
|type| type,可用值:VISIBLE,THERMOGRAPHY  | path | true |string  |    |

**schema属性说明**



**HistoryVideoPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|upTimeEnd| 上传时间段搜索 结束  | body | false |integer(int64)  |    |
|upTimeStart| 上传时间段搜索 开始  | body | false |integer(int64)  |    |
|videoNameLike| 视频名称搜索  | body | false |string  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"fileId": 0,
			"id": 0,
			"isRecode": true,
			"startTime": 0,
			"videoDuration": "",
			"videoName": "",
			"videoType": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | HistoryVideoDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**HistoryVideoDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|fileId | 视频文件ID   |integer(int64)  |    |
|id | 视频记录ID   |integer(int64)  |    |
|isRecode | 视频文件已转码,未转码播放时提示正在转码   |boolean  |    |
|startTime | 开始录制时间   |integer(int64)  |    |
|videoDuration | 视频时长   |string  |    |
|videoName | 视频名称   |string  |    |
|videoType | 视频类型,可用值:VISIBLE,THERMOGRAPHY   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«HistoryVideoDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存视频记录


**接口描述**:


**接口地址**:`/app/history/video/{type}/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"equipmentId": 0,
	"fileId": 0,
	"startTime": 0,
	"videoDuration": "",
	"videoName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |HistoryVideoSaveParam  | HistoryVideoSaveParam   |
|type| type,可用值:VISIBLE,THERMOGRAPHY  | path | true |string  |    |

**schema属性说明**



**HistoryVideoSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|fileId| 视频文件ID  | body | false |integer(int64)  |    |
|startTime| 开始录制时间  | body | false |integer(int64)  |    |
|videoDuration| 视频时长  | body | false |string  |    |
|videoName| 视频名称  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-巡检轨迹管理

## 添加


**接口描述**:


**接口地址**:`/app/inspectionTrack/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"coordinateData": "",
	"id": 0,
	"rfidData": "",
	"trackName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |InspectionTrackSaveParam  | InspectionTrackSaveParam   |

**schema属性说明**



**InspectionTrackSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|coordinateData| 坐标数据  | body | false |string  |    |
|id| ID  | body | false |integer(int64)  |    |
|rfidData| RFID数据  | body | false |string  |    |
|trackName| 轨迹名称  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取详情 by id


**接口描述**:


**接口地址**:`/app/inspectionTrack/id/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"coordinateData": "",
	"createTime": 0,
	"enabled": true,
	"id": 0,
	"rfidData": "",
	"trackName": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|coordinateData| 坐标数据  |string  |    |
|createTime| 创建时间  |integer(int64)  | integer(int64)   |
|enabled| 是否启用  |boolean  |    |
|id| ID  |integer(int64)  | integer(int64)   |
|rfidData| RFID数据  |string  |    |
|trackName| 轨迹名称  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |InspectionTrackDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除 by id


**接口描述**:


**接口地址**:`/app/inspectionTrack/id/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 获取列表(分页)


**接口描述**:


**接口地址**:`/app/inspectionTrack/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"createTimeEnd": 0,
	"createTimeStart": 0,
	"current": 1,
	"enabled": true,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"trackName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |InspectionTrackPageParam  | InspectionTrackPageParam   |

**schema属性说明**



**InspectionTrackPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTimeEnd| 创建时间筛选-结束  | body | false |integer(int64)  |    |
|createTimeStart| 创建时间筛选-开始  | body | false |integer(int64)  |    |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|enabled| 是否启用  | body | false |boolean  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|trackName| 轨迹名称  | body | false |string  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"coordinateData": "",
			"createTime": 0,
			"enabled": true,
			"id": 0,
			"rfidData": "",
			"trackName": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | InspectionTrackDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**InspectionTrackDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|coordinateData | 坐标数据   |string  |    |
|createTime | 创建时间   |integer(int64)  |    |
|enabled | 是否启用   |boolean  |    |
|id | ID   |integer(int64)  |    |
|rfidData | RFID数据   |string  |    |
|trackName | 轨迹名称   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«InspectionTrackDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改保存


**接口描述**:


**接口地址**:`/app/inspectionTrack/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"coordinateData": "",
	"id": 0,
	"rfidData": "",
	"trackName": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |InspectionTrackSaveParam  | InspectionTrackSaveParam   |

**schema属性说明**



**InspectionTrackSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|coordinateData| 坐标数据  | body | false |string  |    |
|id| ID  | body | false |integer(int64)  |    |
|rfidData| RFID数据  | body | false |string  |    |
|trackName| 轨迹名称  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 启用/禁用


**接口描述**:


**接口地址**:`/app/inspectionTrack/{id}/enabled/{enabled}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|enabled| enabled  | path | true |boolean  |    |
|id| id  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-报警日志

## 查看详情


**接口描述**:


**接口地址**:`/app/log/alarm/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"alarmType": "",
	"alarmTypeStr": "",
	"content": "",
	"equipmentId": 0,
	"location": "",
	"logTime": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|alarmType| 报警类型,可用值:ROBOT_STATUS,ENVIRONMENTAL_PARAMETERS,CAMERA_MONITORING_ALARM  |string  |    |
|alarmTypeStr| 报警位置  |string  |    |
|content| 日志内容  |string  |    |
|equipmentId| 设备ID  |integer(int64)  | integer(int64)   |
|location| 报警位置中文描述  |string  |    |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogAlarmDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/app/log/alarm/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"alarmType": "",
			"alarmTypeStr": "",
			"content": "",
			"equipmentId": 0,
			"location": "",
			"logTime": 0
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogAlarmDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogAlarmDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|alarmType | 报警类型,可用值:ROBOT_STATUS,ENVIRONMENTAL_PARAMETERS,CAMERA_MONITORING_ALARM   |string  |    |
|alarmTypeStr | 报警位置   |string  |    |
|content | 日志内容   |string  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|location | 报警位置中文描述   |string  |    |
|logTime | 日志记录时间   |integer(int64)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogAlarmDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存日志记录


**接口描述**:


**接口地址**:`/app/log/alarm/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"alarmType": "",
	"content": "",
	"equipmentId": 0,
	"location": "",
	"logTime": 0
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogAlarmSaveParam  | LogAlarmSaveParam   |

**schema属性说明**



**LogAlarmSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|alarmType| 报警类型,可用值:ROBOT_STATUS,ENVIRONMENTAL_PARAMETERS,CAMERA_MONITORING_ALARM  | body | false |string  |    |
|content| 日志内容  | body | false |string  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|location| 报警位置  | body | false |string  |    |
|logTime| 日志记录时间  | body | false |integer(int64)  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-环境日志

## 查看详情


**接口描述**:


**接口地址**:`/app/log/environmental/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"butane": "",
	"carbonMonoxide": "",
	"equipmentId": 0,
	"ethane": "",
	"humidity": "",
	"hydrogenSulfide": "",
	"logTime": 0,
	"methane": "",
	"propane": "",
	"temperature": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|butane| 丁烷  |string  |    |
|carbonMonoxide| 一氧化碳  |string  |    |
|equipmentId| 设备ID  |integer(int64)  | integer(int64)   |
|ethane| 乙烷  |string  |    |
|humidity| 湿度  |string  |    |
|hydrogenSulfide| 硫化氢  |string  |    |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |
|methane| 甲烷  |string  |    |
|propane| 丙烷  |string  |    |
|temperature| 温度  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogEnvironmentalDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/app/log/environmental/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"butane": "",
			"carbonMonoxide": "",
			"equipmentId": 0,
			"ethane": "",
			"humidity": "",
			"hydrogenSulfide": "",
			"logTime": 0,
			"methane": "",
			"propane": "",
			"temperature": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogEnvironmentalDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogEnvironmentalDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|butane | 丁烷   |string  |    |
|carbonMonoxide | 一氧化碳   |string  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|ethane | 乙烷   |string  |    |
|humidity | 湿度   |string  |    |
|hydrogenSulfide | 硫化氢   |string  |    |
|logTime | 日志记录时间   |integer(int64)  |    |
|methane | 甲烷   |string  |    |
|propane | 丙烷   |string  |    |
|temperature | 温度   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogEnvironmentalDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存日志记录


**接口描述**:


**接口地址**:`/app/log/environmental/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"butane": "",
	"carbonMonoxide": "",
	"equipmentId": 0,
	"ethane": "",
	"humidity": "",
	"hydrogenSulfide": "",
	"logTime": 0,
	"methane": "",
	"propane": "",
	"temperature": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogEnvironmentalSaveParam  | LogEnvironmentalSaveParam   |

**schema属性说明**



**LogEnvironmentalSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|butane| 丁烷  | body | false |string  |    |
|carbonMonoxide| 一氧化碳  | body | false |string  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|ethane| 乙烷  | body | false |string  |    |
|humidity| 湿度  | body | false |string  |    |
|hydrogenSulfide| 硫化氢  | body | false |string  |    |
|logTime| 日志记录时间  | body | false |integer(int64)  |    |
|methane| 甲烷  | body | false |string  |    |
|propane| 丙烷  | body | false |string  |    |
|temperature| 温度  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-用户

## 客户端心跳上报, 返回错误码20104则清除本地token，并弹窗提示


**接口描述**:


**接口地址**:`/app/user/health`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取当前登陆用户信息


**接口描述**:


**接口地址**:`/app/user/info`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"createTime": 0,
	"enabled": true,
	"id": 0,
	"mobile": "",
	"nickName": "",
	"username": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|createTime| 创建时间，时间戳 毫秒  |integer(int64)  | integer(int64)   |
|enabled| 是否启用  |boolean  |    |
|id| ID  |integer(int64)  | integer(int64)   |
|mobile| 手机  |string  |    |
|nickName| 昵称  |string  |    |
|username| 账号名  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |AppUserDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 客户端登陆


**接口描述**:


**接口地址**:`/app/user/login`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"password": "",
	"username": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |AppUserLoginParam  | AppUserLoginParam   |

**schema属性说明**



**AppUserLoginParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|password| 密码  | body | true |string  |    |
|username| 登录名  | body | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改密码


**接口描述**:


**接口地址**:`/app/user/pwd`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"newPassword": "",
	"oldPassword": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |AppUserUpdPwdParam  | AppUserUpdPwdParam   |

**schema属性说明**



**AppUserUpdPwdParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|newPassword| 新密码  | body | true |string  |    |
|oldPassword| 旧密码  | body | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-系统手册

## 获取问题详情


**接口描述**:


**接口地址**:`/app/questions/code/{code}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|code| code  | path | true |string  |    |

**响应示例**:

```json
{
	"answer": "",
	"code": "",
	"question": "",
	"sort": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|answer| 答案  |string  |    |
|code| 问题code  |string  |    |
|question| 问题  |string  |    |
|sort| 排序  |integer(int64)  | integer(int64)   |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |QuestionDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取问题列表(分页)


**接口描述**:


**接口地址**:`/app/questions/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"searchKey": "",
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |QuestionPageParam  | QuestionPageParam   |

**schema属性说明**



**QuestionPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|searchKey| 关键词查找  | body | false |string  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"answer": "",
			"code": "",
			"question": "",
			"sort": 0
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | QuestionDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**QuestionDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|answer | 答案   |string  |    |
|code | 问题code   |string  |    |
|question | 问题   |string  |    |
|sort | 排序   |integer(int64)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«QuestionDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-视频日志

## 查看详情


**接口描述**:


**接口地址**:`/app/log/video/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"creator": "",
	"equipmentId": 0,
	"logTime": 0,
	"operationType": "",
	"operationTypeStr": "",
	"videoDuration": "",
	"videoName": "",
	"videoType": "",
	"videoTypeStr": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|creator| 操作用户  |string  |    |
|equipmentId| 设备ID  |integer(int64)  | integer(int64)   |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |
|operationType| 操作类型,可用值:ADD,DEL  |string  |    |
|operationTypeStr| 操作类型中文描述  |string  |    |
|videoDuration| 视频时长  |string  |    |
|videoName| 视频名称  |string  |    |
|videoType| 视频类型,可用值:VISIBLE,THERMOGRAPHY  |string  |    |
|videoTypeStr| 视频类型中文描述  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogVideoDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/app/log/video/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"creator": "",
			"equipmentId": 0,
			"logTime": 0,
			"operationType": "",
			"operationTypeStr": "",
			"videoDuration": "",
			"videoName": "",
			"videoType": "",
			"videoTypeStr": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogVideoDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogVideoDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|creator | 操作用户   |string  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|logTime | 日志记录时间   |integer(int64)  |    |
|operationType | 操作类型,可用值:ADD,DEL   |string  |    |
|operationTypeStr | 操作类型中文描述   |string  |    |
|videoDuration | 视频时长   |string  |    |
|videoName | 视频名称   |string  |    |
|videoType | 视频类型,可用值:VISIBLE,THERMOGRAPHY   |string  |    |
|videoTypeStr | 视频类型中文描述   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogVideoDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-设备管理

## 查看详情


**接口描述**:


**接口地址**:`/app/equipment/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"enabled": true,
	"funcDescr": "",
	"id": 0,
	"plcIp": "",
	"plcPort": "",
	"scopeDescr": "",
	"tcAccount": "",
	"tcIp": "",
	"tcPort": "",
	"tcPwd": "",
	"userId": 0,
	"username": "",
	"vcAccount": "",
	"vcIp": "",
	"vcPort": "",
	"vcPwd": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|enabled| 是否启用  |boolean  |    |
|funcDescr| 功能描述  |string  |    |
|id| 设备ID  |integer(int64)  | integer(int64)   |
|plcIp| PLC IP  |string  |    |
|plcPort| PLC端口  |string  |    |
|scopeDescr| 范围描述  |string  |    |
|tcAccount| 热成像摄像头 密码  |string  |    |
|tcIp| 热成像摄像头 IP  |string  |    |
|tcPort| 热成像摄像头 端口  |string  |    |
|tcPwd| 可见光摄像头 密码  |string  |    |
|userId| 关联账号  |integer(int64)  | integer(int64)   |
|username| 关联账号  |string  |    |
|vcAccount| 可见光摄像头 密码  |string  |    |
|vcIp| 可见光摄像头 IP  |string  |    |
|vcPort| 可见光摄像头 端口  |string  |    |
|vcPwd| 可见光摄像头 密码  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |EquipmentDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取当前账号绑定的设备列表


**接口描述**:


**接口地址**:`/app/equipment/list/own`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json
[
	{
		"enabled": true,
		"funcDescr": "",
		"id": 0,
		"plcIp": "",
		"plcPort": "",
		"scopeDescr": "",
		"tcAccount": "",
		"tcIp": "",
		"tcPort": "",
		"tcPwd": "",
		"userId": 0,
		"username": "",
		"vcAccount": "",
		"vcIp": "",
		"vcPort": "",
		"vcPwd": ""
	}
]
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|enabled| 是否启用  |boolean  |    |
|funcDescr| 功能描述  |string  |    |
|id| 设备ID  |integer(int64)  | integer(int64)   |
|plcIp| PLC IP  |string  |    |
|plcPort| PLC端口  |string  |    |
|scopeDescr| 范围描述  |string  |    |
|tcAccount| 热成像摄像头 密码  |string  |    |
|tcIp| 热成像摄像头 IP  |string  |    |
|tcPort| 热成像摄像头 端口  |string  |    |
|tcPwd| 可见光摄像头 密码  |string  |    |
|userId| 关联账号  |integer(int64)  | integer(int64)   |
|username| 关联账号  |string  |    |
|vcAccount| 可见光摄像头 密码  |string  |    |
|vcIp| 可见光摄像头 IP  |string  |    |
|vcPort| 可见光摄像头 端口  |string  |    |
|vcPwd| 可见光摄像头 密码  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |EquipmentDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取设备列表(分页)


**接口描述**:


**接口地址**:`/app/equipment/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"id": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10,
	"userSearch": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |EquipmentPageParam  | EquipmentPageParam   |

**schema属性说明**



**EquipmentPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|id| 设备ID  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |
|userSearch| 绑定账号搜索(用户名、昵称、手机号)  | body | false |string  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"enabled": true,
			"funcDescr": "",
			"id": 0,
			"plcIp": "",
			"plcPort": "",
			"scopeDescr": "",
			"tcAccount": "",
			"tcIp": "",
			"tcPort": "",
			"tcPwd": "",
			"userId": 0,
			"username": "",
			"vcAccount": "",
			"vcIp": "",
			"vcPort": "",
			"vcPwd": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | EquipmentDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**EquipmentDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|enabled | 是否启用   |boolean  |    |
|funcDescr | 功能描述   |string  |    |
|id | 设备ID   |integer(int64)  |    |
|plcIp | PLC IP   |string  |    |
|plcPort | PLC端口   |string  |    |
|scopeDescr | 范围描述   |string  |    |
|tcAccount | 热成像摄像头 密码   |string  |    |
|tcIp | 热成像摄像头 IP   |string  |    |
|tcPort | 热成像摄像头 端口   |string  |    |
|tcPwd | 可见光摄像头 密码   |string  |    |
|userId | 关联账号   |integer(int64)  |    |
|username | 关联账号   |string  |    |
|vcAccount | 可见光摄像头 密码   |string  |    |
|vcIp | 可见光摄像头 IP   |string  |    |
|vcPort | 可见光摄像头 端口   |string  |    |
|vcPwd | 可见光摄像头 密码   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«EquipmentDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 客户端-设备运行日志

## 查看详情


**接口描述**:


**接口地址**:`/app/log/equipmentRun/details/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | path | true |integer  |    |

**响应示例**:

```json
{
	"batteryInformation": "",
	"butane": "",
	"carbonMonoxide": "",
	"equipmentId": 0,
	"equipmentStatus": "",
	"ethane": "",
	"humidity": "",
	"hydrogenSulfide": "",
	"logTime": 0,
	"methane": "",
	"propane": "",
	"temperature": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|batteryInformation| 电池信息  |string  |    |
|butane| 丁烷  |string  |    |
|carbonMonoxide| 一氧化碳  |string  |    |
|equipmentId| 设备ID  |integer(int64)  | integer(int64)   |
|equipmentStatus| 设备状态,可用值:STANDBY,CHARGING,AT_WORK  |string  |    |
|ethane| 乙烷  |string  |    |
|humidity| 湿度  |string  |    |
|hydrogenSulfide| 硫化氢  |string  |    |
|logTime| 日志记录时间  |integer(int64)  | integer(int64)   |
|methane| 甲烷  |string  |    |
|propane| 丙烷  |string  |    |
|temperature| 温度  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LogEquipmentRunDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取日志列表(分页)


**接口描述**:


**接口地址**:`/app/log/equipmentRun/page`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"current": 1,
	"equipmentId": 0,
	"logTimeEnd": 0,
	"logTimeStart": 0,
	"orders": [
		{
			"column": "id",
			"asc": false
		}
	],
	"size": 10
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogPageParam  | LogPageParam   |

**schema属性说明**



**LogPageParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|current| 第几页，默认第一页  | body | false |integer(int64)  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|logTimeEnd| 日志记录时间段搜索 结束  | body | false |integer(int64)  |    |
|logTimeStart| 日志记录时间段搜索 开始  | body | false |integer(int64)  |    |
|orders| 排序信息，排序的字段和正反序  | body | false |array  |    |
|size| 每页大小，默认10  | body | false |integer(int64)  |    |

**响应示例**:

```json
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"batteryInformation": "",
			"butane": "",
			"carbonMonoxide": "",
			"equipmentId": 0,
			"equipmentStatus": "",
			"ethane": "",
			"humidity": "",
			"hydrogenSulfide": "",
			"logTime": 0,
			"methane": "",
			"propane": "",
			"temperature": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|current|   |integer(int64)  | integer(int64)   |
|hitCount|   |boolean  |    |
|pages|   |integer(int64)  | integer(int64)   |
|records|   |array  | LogEquipmentRunDTO   |
|searchCount|   |boolean  |    |
|size|   |integer(int64)  | integer(int64)   |
|total|   |integer(int64)  | integer(int64)   |



**schema属性说明**




**LogEquipmentRunDTO**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|batteryInformation | 电池信息   |string  |    |
|butane | 丁烷   |string  |    |
|carbonMonoxide | 一氧化碳   |string  |    |
|equipmentId | 设备ID   |integer(int64)  |    |
|equipmentStatus | 设备状态,可用值:STANDBY,CHARGING,AT_WORK   |string  |    |
|ethane | 乙烷   |string  |    |
|humidity | 湿度   |string  |    |
|hydrogenSulfide | 硫化氢   |string  |    |
|logTime | 日志记录时间   |integer(int64)  |    |
|methane | 甲烷   |string  |    |
|propane | 丙烷   |string  |    |
|temperature | 温度   |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |IPage«LogEquipmentRunDTO»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存日志记录


**接口描述**:


**接口地址**:`/app/log/equipmentRun/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"batteryInformation": "",
	"butane": "",
	"carbonMonoxide": "",
	"equipmentId": 0,
	"equipmentStatus": "",
	"ethane": "",
	"humidity": "",
	"hydrogenSulfide": "",
	"logTime": 0,
	"methane": "",
	"propane": "",
	"temperature": ""
}
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|param| param  | body | true |LogEquipmentRunSaveParam  | LogEquipmentRunSaveParam   |

**schema属性说明**



**LogEquipmentRunSaveParam**

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|batteryInformation| 电池信息  | body | false |string  |    |
|butane| 丁烷  | body | false |string  |    |
|carbonMonoxide| 一氧化碳  | body | false |string  |    |
|equipmentId| 设备ID  | body | false |integer(int64)  |    |
|equipmentStatus| 设备状态,可用值:STANDBY,CHARGING,AT_WORK  | body | false |string  |    |
|ethane| 乙烷  | body | false |string  |    |
|humidity| 湿度  | body | false |string  |    |
|hydrogenSulfide| 硫化氢  | body | false |string  |    |
|logTime| 日志记录时间  | body | false |integer(int64)  |    |
|methane| 甲烷  | body | false |string  |    |
|propane| 丙烷  | body | false |string  |    |
|temperature| 温度  | body | false |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 通用-文件上传相关

## 普通文件上传


**接口描述**:


**接口地址**:`/app/file/upload/`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|file| file  | formData | true |file  |    |
|name| name  | query | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 文件上传状态检查


**接口描述**:


**接口地址**:`/app/file/upload/exist/{md5}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|md5| md5  | path | true |string  |    |

**响应示例**:

```json
{
	"id": 0,
	"name": "",
	"originalId": 0,
	"saveDir": "",
	"saveName": "",
	"sliceAmount": 0,
	"sliceFileSize": 0,
	"sliceIndex": [],
	"status": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|id| 文件ID  |integer(int64)  | integer(int64)   |
|name| 文件名  |string  |    |
|originalId| 原始文件ID,用于转码等操作后记录原始文件信息  |integer(int64)  | integer(int64)   |
|saveDir| 文件保存目录  |string  |    |
|saveName| 文件保存磁盘名称  |string  |    |
|sliceAmount| 分片总数量  |integer(int32)  | integer(int32)   |
|sliceFileSize| 分片文件大小,最后一片允许小于该值  |integer(int64)  | integer(int64)   |
|sliceIndex| 已上传的文件分片索引  |array  |    |
|status| 文件状态:-1: 不存在;0: 部分已上传,1: 分片已全部上传，但还未合并,2: 已存在  |integer(int32)  | integer(int32)   |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |FileExists|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 分片文件上传


**接口描述**:


**接口地址**:`/app/file/upload/slice`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|file| file  | formData | true |file  |    |
|index| index  | query | true |integer  |    |
|md5| md5  | query | true |string  |    |
|parentMd5| parentMd5  | query | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 分片上传前创建父文件记录，若已存在记录,则会清除已存在的分片数据和文件


**接口描述**:


**接口地址**:`/app/file/upload/slice/genParent/{md5}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|md5| md5  | path | true |string  |    |
|name| name  | query | true |string  |    |
|size| size  | query | true |integer  |    |
|sliceFileSize| sliceFileSize  | query | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 分片文件合并


**接口描述**:


**接口地址**:`/app/file/upload/slice/merge/{md5}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|md5| md5  | path | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 普通文件上传


**接口描述**:


**接口地址**:`/sys/file/upload/`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|file| file  | formData | true |file  |    |
|name| name  | query | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 文件上传状态检查


**接口描述**:


**接口地址**:`/sys/file/upload/exist/{md5}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|md5| md5  | path | true |string  |    |

**响应示例**:

```json
{
	"id": 0,
	"name": "",
	"originalId": 0,
	"saveDir": "",
	"saveName": "",
	"sliceAmount": 0,
	"sliceFileSize": 0,
	"sliceIndex": [],
	"status": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|id| 文件ID  |integer(int64)  | integer(int64)   |
|name| 文件名  |string  |    |
|originalId| 原始文件ID,用于转码等操作后记录原始文件信息  |integer(int64)  | integer(int64)   |
|saveDir| 文件保存目录  |string  |    |
|saveName| 文件保存磁盘名称  |string  |    |
|sliceAmount| 分片总数量  |integer(int32)  | integer(int32)   |
|sliceFileSize| 分片文件大小,最后一片允许小于该值  |integer(int64)  | integer(int64)   |
|sliceIndex| 已上传的文件分片索引  |array  |    |
|status| 文件状态:-1: 不存在;0: 部分已上传,1: 分片已全部上传，但还未合并,2: 已存在  |integer(int32)  | integer(int32)   |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |FileExists|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 分片文件上传


**接口描述**:


**接口地址**:`/sys/file/upload/slice`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|file| file  | formData | true |file  |    |
|index| index  | query | true |integer  |    |
|md5| md5  | query | true |string  |    |
|parentMd5| parentMd5  | query | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 分片上传前创建父文件记录，若已存在记录,则会清除已存在的分片数据和文件


**接口描述**:


**接口地址**:`/sys/file/upload/slice/genParent/{md5}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|md5| md5  | path | true |string  |    |
|name| name  | query | true |string  |    |
|size| size  | query | true |integer  |    |
|sliceFileSize| sliceFileSize  | query | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 分片文件合并


**接口描述**:


**接口地址**:`/sys/file/upload/slice/merge/{md5}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|md5| md5  | path | true |string  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 通用-文件下载相关
## 获取文件信息


**接口描述**:


**接口地址**:`/app/file/download/info/{fileId}/public`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|fileId| fileId  | path | true |integer  |    |

**响应示例**:

```json
{
	"id": 0,
	"md5": "",
	"name": "",
	"originalId": 0,
	"saveDir": "",
	"saveName": "",
	"size": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|id| 文件ID  |integer(int64)  | integer(int64)   |
|md5| 文件MD5  |string  |    |
|name| 文件名  |string  |    |
|originalId| 原始文件ID,用于转码等操作后记录原始文件信息  |integer(int64)  | integer(int64)   |
|saveDir| 文件保存目录  |string  |    |
|saveName| 文件保存磁盘名称  |string  |    |
|size| 文件大小  |integer(int64)  | integer(int64)   |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LocalFileDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 视频播放，支持断点续传拖动播放


**接口描述**:


**接口地址**:`/app/file/download/video/{fileId}/public`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|fileId| fileId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 普通文件下载


**接口描述**:


**接口地址**:`/app/file/download/{fileId}/public`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|fileId| fileId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取文件信息


**接口描述**:


**接口地址**:`/sys/file/download/info/{fileId}/public`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|fileId| fileId  | path | true |integer  |    |

**响应示例**:

```json
{
	"id": 0,
	"md5": "",
	"name": "",
	"originalId": 0,
	"saveDir": "",
	"saveName": "",
	"size": 0
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|id| 文件ID  |integer(int64)  | integer(int64)   |
|md5| 文件MD5  |string  |    |
|name| 文件名  |string  |    |
|originalId| 原始文件ID,用于转码等操作后记录原始文件信息  |integer(int64)  | integer(int64)   |
|saveDir| 文件保存目录  |string  |    |
|saveName| 文件保存磁盘名称  |string  |    |
|size| 文件大小  |integer(int64)  | integer(int64)   |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |LocalFileDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 视频播放，支持断点续传拖动播放


**接口描述**:


**接口地址**:`/sys/file/download/video/{fileId}/public`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|fileId| fileId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 普通文件下载


**接口描述**:


**接口地址**:`/sys/file/download/{fileId}/public`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|fileId| fileId  | path | true |integer  |    |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 通用接口

## 获取服务器当前时间


**接口描述**:


**接口地址**:`/time/public`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 验证码

## 生成图形一对验证码, 返回imageBase64


**接口描述**:


**接口地址**:`/base/captcha/img/base64`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"code": "",
	"token": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |string  |    |
|token|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |CaptchaDTO|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
