# AI数字助手API说明

## Base URL

- BASEPath `/smartwaterai`

【汉威服务器环境】

- HW WINDOWS LOACAL HOTS `http://10.0.15.40:8222`
- BASE_URL `http://10.0.15.40:8222/smartwaterai`

【汉威本地测试】

- HW WINDOWS LOACAL HOTS `http://10.30.30.97:8222`
- BASE_URL `http://10.30.30.97:8222/smartwaterai`





# API 响应结构

此结构来自hanwei工具类的统一封装

```json
{
	"success": true,
	"message": "",
	"code": 0,
	"result": {},
	"timestamp": 0
}
```

- 响应示例1

    ```json
    {  
    "success": true,  
    "message": "操作成功",  
    "code": 200,  
    "result": {  
        "description": "这里是具体的业务数据,根据不同API返回不同内容。可能是对象、数组或null"  
    },  
    "timestamp": 1699200000000  
    }
    ```

- 响应示例2

    ```json
    {'success': True, 'message': '保存成功', 'code': 200, 'result': '保存成功', 'timestamp': 1762401106104}
    ```



# 业务接口列表

## 知识库基础信息管理-分页列表查询

```json
{  
  "method": "GET",  
  "endpoint": "{BASE_URL}/tag/ragInfo/list",  
  "parameters": {  
    "pageNo": {  
      "type": "Integer",  
      "required": true,  
      "default": 1,  
      "description": "页码"  
    },  
    "pageSize": {  
      "type": "Integer",   
      "required": true,  
      "default": 10,  
      "description": "每页数量"  
    },  
    "id": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库主键ID"  
    },  
    "name": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库名称,支持模糊搜索"  
    },  
    "description": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库描述"  
    },  
    "avatar": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库缩略图"  
    },  
    "embdId": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "向量化模型ID"  
    },  
    "slicingMethod": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "切片方法"  
    },  
    "docParser": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "文档解析器"  
    },  
    "pagerank": {  
      "type": "Integer",  
      "required": false,  
      "default": null,  
      "description": "文本块大小"  
    }  
  }  
}
```


## 知识库基础信息管理-知识库召回测试

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/tag/ragInfo/ragRecall",  
  "headers": {  
    "Content-Type": {  
      "type": "String",  
      "required": true,  
      "default": "application/json",  
      "description": "请求内容类型"  
    }  
  },  
  "parameters": {  
    "ragId": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "知识库ID"  
    },  
    "question": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "召回问题"  
    },  
    "similarityThreshold": {  
      "type": "Double",  
      "required": false,  
      "default": 0.2,  
      "description": "相似度阈值,范围0.0-1.0"  
    },  
    "vectorSimilarityWeight": {  
      "type": "Double",  
      "required": false,  
      "default": 0.3,  
      "description": "向量相似度权重,范围0.0-1.0"  
    },  
    "useGraphRag": {  
      "type": "Boolean",  
      "required": false,  
      "default": false,  
      "description": "是否启用知识图谱"  
    },  
    "pageNo": {  
      "type": "Integer",  
      "required": false,  
      "default": 1,  
      "description": "页码"  
    },  
    "pageSize": {  
      "type": "Integer",  
      "required": false,  
      "default": 10,  
      "description": "分页条数"  
    },  
    "doc_ids": {  
      "type": "List<String>",  
      "required": false,  
      "default": null,  
      "description": "包含文档ID列表,暂时不用"  
    },  
    "search_method": {  
      "type": "String",  
      "required": false,  
      "default": "semantic_search",  
      "description": "检索方法(仅水务 dify 使用)"  
    },  
    "reranking_enable": {  
      "type": "Boolean",  
      "required": false,  
      "default": false,  
      "description": "是否启用重排序(仅水务 dify 使用)"  
    },  
    "score_threshold_enabled": {  
      "type": "Boolean",  
      "required": false,  
      "default": false,  
      "description": "是否启用相似度阈值(仅水务 dify 使用)"  
    },  
    "topN": {  
      "type": "Integer",  
      "required": false,  
      "default": 3,  
      "description": "返回结果数(仅水务 dify 使用)"  
    }  
  }  
}
```


## 知识库基础信息管理-编辑

一般更新的都是如下参数，传入新值即可：

- name

- description

- vector_similarity_weight

- top_n


```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/tag/ragInfo/edit",  
  "headers": {  
    "Content-Type": {  
      "type": "String",  
      "required": true,  
      "default": "application/json",  
      "description": "请求内容类型"  
    }  
  },  
  "parameters": {  
    "id": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "知识库主键ID"  
    },  
    "name": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库名称"  
    },  
    "description": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库描述"  
    },  
    "avatar": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库缩略图"  
    },  
    "embdId": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "向量化模型ID"  
    },  
    "docParser": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "文档解析器,如 'DeepDOC'"  
    },  
    "slicingMethod": {  
      "type": "String",  
      "required": true,  
      "default": "General",  
      "description": "切片方法,对应研究院 API 的 parser_id,必需参数。常见值: 'General'"  
    },  
    "pagerank": {  
      "type": "Integer",  
      "required": false,  
      "default": null,  
      "description": "文本块大小,如 512"  
    },  
    "isUseGraph": {  
      "type": "Boolean",  
      "required": false,  
      "default": null,  
      "description": "是否使用知识图谱"  
    },  
    "entityTypes": {  
      "type": "List",  
      "required": false,  
      "default": null,  
      "description": "实体类型列表"  
    },  
    "isResolution": {  
      "type": "Boolean",  
      "required": false,  
      "default": null,  
      "description": "是否实体归一化"  
    },  
    "methodGraph": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "图谱生成方法"  
    },  
    "isGeneralReport": {  
      "type": "Boolean",  
      "required": false,  
      "default": null,  
      "description": "是否生成社区报告"  
    },  
    "similarityThreshold": {  
      "type": "Double",  
      "required": false,  
      "default": null,  
      "description": "相似度阈值,范围 0.0-1.0"  
    },  
    "vectorSimilarityWeight": {  
      "type": "Double",  
      "required": false,  
      "default": null,  
      "description": "向量相似度权重,范围 0.0-1.0"  
    },  
    "topN": {  
      "type": "Integer",  
      "required": false,  
      "default": null,  
      "description": "返回结果数,如 8"  
    },  
    "indexingTechnique": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "索引技术(仅水务 dify 使用),如 'high_quality'"  
    },  
    "embeddingProviderName": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "embedding提供商名称(仅水务 dify 使用)"  
    },  
    "searchMethod": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "搜索方法(仅水务 dify 使用),如 'hybrid_search'"  
    },  
    "rerankingEnable": {  
      "type": "Boolean",  
      "required": false,  
      "default": null,  
      "description": "是否启用重排序(仅水务 dify 使用)"  
    },  
    "rerankingModelName": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "重排序模型名称(仅水务 dify 使用)"  
    },  
    "rerankingProviderName": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "重排序提供商名称(仅水务 dify 使用)"  
    },  
    "scoreThresholdEnabled": {  
      "type": "Boolean",  
      "required": false,  
      "default": null,  
      "description": "是否启用分数阈值(仅水务 dify 使用)"  
    }  
  }  
}
```



## 知识库基础信息管理-通过id删除

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/tag/ragInfo/delete",  
  "headers": {  
    "username": {  
      "type": "String",  
      "required": true,  
      "default": "H05583",  // 和创建 DB 接口时的 header 默认值相同，因此保持此默认值不变即可，但是需要构造 headers
      "description": "用户名,用于身份验证"  
    }  
  },  
  "parameters": {  
    "id": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "知识库主键ID"  
    }  
  }  
}
```

## 大模型基础信息-研究院:设置知识库

说明：用于配置大模型对话时使用的知识库和相关参数

此接口后端服务没有实现，因此暂时不需要有客户端。


## 知识库基础信息管理-添加

说明：前端调用此接口时，后端会默认构造请求头 （客户端无需构造 headers）

```json
headers = {
    "USERNAME": "UserName",
    "WORKNO": "H05583"
}
```

API与参数信息

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/tag/ragInfo/add",  
  "parameters": {  
    "name": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "知识库名称"  
    },  
    "description": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库描述"  
    },  
    "avatar": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "知识库缩略图"  
    },  
    "iconUrl": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "图片地址"  
    },  
    "fileId": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "图标文件ID"  
    }  
  }  
}
```



## 知识库基础信息管理-通过id查询

```json
{  
  "method": "GET",  
  "endpoint": "{BASE_URL}/tag/ragInfo/queryById",  
  "parameters": {  
    "id": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "知识库主键ID"  
    }  
  }  
}
```

响应示例

```json
{  
  "success": true,  
  "message": "操作成功",  
  "code": 200,  
  "result": {  
    "id": "a26e10d8-c37e-44a5-afbc-e21b2314c1e2",  
    "name": "知识库名称",  
    "description": "知识库描述",  
    "avatar": "知识库缩略图",  
    "embdId": "向量化模型ID",  
    "slicingMethod": "切片方法",  
    "docParser": "文档解析器",  
    "pagerank": 1000,  
    "chunkNum": 100,  
    "isUseGraph": false,  
    "entityTypes": "实体类型",  
    "isResolution": false,  
    "methodGraph": "图谱方法",  
    "isGeneralReport": false  
  },  
  "timestamp": 1699200000000  
}
```


## 知识库基础信息管理-获取知识库知识图谱

此服务后端未实现。暂时不需要客户端



## 知识库文档管理-研究院:文档上传

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/rag/ragFileInfo/uploadFileByYanjiuyuan",  
  "headers": {  
    "Content-Type": {  
      "type": "String",  
      "required": true,  
      "default": "multipart/form-data",  
      "description": "请求内容类型,文件上传必须使用 multipart/form-data,由 requests 库自动设置"  
    }  
  },  
  "parameters": {  
    "ragId": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "知识库ID,指定文件要上传到哪个知识库"  
    },  
    "file": {  
      "type": "File",  
      "required": true,  
      "default": null,  
      "description": "要上传的文件流,支持多种格式(PDF、TXT、DOCX等)"  
    }  
  },  
  "response": {  
    "success": {  
      "type": "Boolean",  
      "description": "成功标志,true表示上传成功,false表示失败"  
    },  
    "message": {  
      "type": "String",  
      "description": "返回处理消息,成功时为'文件上传成功',失败时包含具体错误原因"  
    },  
    "code": {  
      "type": "Integer",  
      "description": "返回代码,200表示成功,500表示服务器错误"  
    },  
    "result": {  
      "type": "Object",  
      "description": "返回数据对象,上传成功时通常为null"  
    },  
    "timestamp": {  
      "type": "Long",  
      "description": "时间戳,表示响应生成的时间(毫秒)"  
    }  
  }  
}
```


## 知识库文档管理-研究院:文档查询

```json
{  
  "method": "GET",  
  "endpoint": "{BASE_URL}/rag/ragFileInfo/getFileListByYanjiuyuan",  
  "parameters": {  
    "ragId": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "知识库ID,指定要查询哪个知识库的文件"  
    },  
    "pageNo": {  
      "type": "String",  
      "required": false,  
      "default": "1",  
      "description": "页码"  
    },  
    "pageSize": {  
      "type": "String",  
      "required": false,  
      "default": "10",  
      "description": "每页数量"  
    },  
    "fileName": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "文件名称,用于模糊搜索"  
    }  
  }  
}
```


## 知识库文档管理-研究院:文档解析

说明：文档上传完毕后，需要调用此接口执行文旦解析

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/rag/ragFileInfo/ragFileParsingByYanjiuyuan",  
  "headers": {  
    "Content-Type": {  
      "type": "String",  
      "required": true,  
      "default": "application/json",  
      "description": "请求内容类型"  
    }  
  },  
  "parameters": {  
    "docIdList": {  
      "type": "List<String>", 
      "required": true,  
      "default": null,  
      "description": "文档ID列表,支持批量解析多个文档"  
    },  
    "deleteFlag": {  
      "type": "Boolean",  
      "required": false,  
      "default": true,  
      "description": "是否删除文档之前的分块数据,true表示删除旧分块重新解析"  
    },  
    "runModel": {  
      "type": "Integer",  
      "required": false,  
      "default": 1,  
      "description": "运行模式: 1=启动文档解析, 2=终止解析"  
    }  
  }  
}
```


## 知识库文档管理-研究院:文档下载

```json
{  
  "method": "GET",  
  "endpoint": "{BASE_URL}/rag/ragFileInfo/downLoadRagFileByYanjiuyuan",  
  "parameters": {  
    "fileId": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "文档ID,指定要下载的文档"  
    }  
  }  
}
```



## 知识库文档管理-知识库文件状态切换

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/rag/ragFileInfo/ragFileSwitch",  
  "headers": {  
    "Content-Type": {  
      "type": "String",  
      "required": true,  
      "default": "application/json",  
      "description": "请求内容类型"  
    }  
  },  
  "parameters": {  
    "docId": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "文档ID,指定要切换状态的文档"  
    },  
    "status": {  
      "type": "Integer",  
      "required": true,  
      "default": null,  
      "description": "状态值: 1=启用文档, 0=禁用文档"  
    }  
  }  
}
```


## 知识库文档管理-研究院:文档切片查询

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/rag/ragFileInfo/getDocumentSlicingListByYanjiuyuan",  
  "headers": {  
    "Content-Type": {  
      "type": "String",  
      "required": false,  
      "default": "application/x-www-form-urlencoded",  
      "description": "请求内容类型,参数通过URL查询字符串传递"  
    }  
  },  
  "parameters": {  
    "pageNo": {  
      "type": "String",  
      "required": false,  
      "default": "1",  
      "description": "页码"  
    },  
    "pageSize": {  
      "type": "String",  
      "required": false,  
      "default": "10",  
      "description": "分页大小"  
    },  
    "docId": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "文档ID,指定要查询哪个文档的切片"  
    },  
    "keyWord": {  
      "type": "String",  
      "required": false,  
      "default": null,  
      "description": "查询词语,用于在切片内容中进行关键词搜索"  
    }  
  }  
}
```



## 知识库文档管理-研究院:文档切片状态切换

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/rag/ragFileInfo/documentSlicingStatusSwitchByYanjiuyuan",  
  "headers": {  
    "Content-Type": {  
      "type": "String",  
      "required": false,  
      "default": "application/x-www-form-urlencoded",  
      "description": "请求内容类型,参数通过URL查询字符串传递"  
    }  
  },  
  "parameters": {  
    "status": {  
      "type": "Integer",  
      "required": true,  
      "default": null,  
      "description": "状态值: 1=启用切片, 0=禁用切片"  
    },  
    "docId": {  
      "type": "String",  
      "required": true,  
      "default": null,  
      "description": "文档ID,指定要操作哪个文档的切片"  
    },  
    "slicingIdList": {  
      "type": "List<String>",  
      "required": false,  
      "default": null,  
      "description": "切片ID列表,指定要切换状态的具体切片。如果不提供,则操作文档的所有切片"  
    }  
  }  
}
```


## 知识库文档管理-通过id删除

```json
{  
  "method": "POST",  
  "endpoint": "{BASE_URL}/rag/ragFileInfo/deleteRagFileByYanjiuyuan",  
  "headers": {  
    "Content-Type": {  
      "type": "String",  
      "required": true,  
      "default": "application/json",  
      "description": "请求内容类型"  
    }  
  },  
  "parameters": {  
    "docIdLis": {  
      "type": "List<String>",  
      "required": true,  
      "default": null,  
      "description": "文档ID列表,支持批量删除多个文档"  
    }  
  }  
}
```




# 对话接口

- url `ws://10.0.15.40:8222/smartwaterai/SmartWaterModelServer/test_channel/test_user/test_code`

## 请求信息分为三种

1. 用户发起闲聊性质的对话

    `"午饭吃什么呢"`

    model response json 如下：

    ```json
    {"resultTo":"system","resultType":1,"textResult":{"speechText":"您好！您的问题不是水务相关的，我可以帮助您解决水务相关的问题。关于午饭吃什么，这取决于您的个人口味和饮食习惯，您可以选择自己喜欢的健康食物。如果您有其他水务方面的问题，欢迎随时向我咨询！","text":"您好！您的问题不是水务相关的，我可以帮助您解决水务相关的问题。关于午饭吃什么，这取决于您的个人口味和饮食习惯，您可以选择自己喜欢的健康食物。如果您有其他水务方面的问题，欢迎随时向我咨询！"}}
    ```
