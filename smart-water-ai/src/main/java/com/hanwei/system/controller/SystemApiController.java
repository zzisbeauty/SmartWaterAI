package com.hanwei.system.controller;

import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.vo.DictModel;
import com.hanwei.core.common.api.impl.SysBaseApiImpl;
import com.hanwei.core.common.api.vo.LoginUser;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.core.util.DictQueryBlackListHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 服务化 system模块 对外接口请求类
 * @author: zht
 */
@Slf4j
@RestController
@RequestMapping("/sys/api")
@AutoRegister
@ApiKind(value = "AI数字助手-系统管理-字典管理")
public class SystemApiController {

    @Autowired
    private SysBaseApiImpl sysBaseApi;

    @Autowired
    private DictQueryBlackListHandler dictQueryBlackListHandler;

    /**
     * 获取数据字典
     * @param code
     * @return
     */
    @GetMapping("/queryDictItemsByCode")
    Result queryDictItemsByCode(@RequestParam("code") String code,@RequestParam("table") String table,@RequestParam("text") String text,@RequestParam("keys") String keys){
        List<DictModel> list = sysBaseApi.queryDictItemsByCode(code,table,text,keys);
        return Result.OK(list);
    }

    /**
     * 获取有效的数据字典
     * @param code
     * @return
     */
    @GetMapping("/queryEnableDictItemsByCode")
    List<DictModel> queryEnableDictItemsByCode(@RequestParam("code") String code){
        return sysBaseApi.queryEnableDictItemsByCode(code);
    }


    /**
     * 48 普通字典的翻译，根据多个dictCode和多条数据，多个以逗号分割
     * @param dictCodes
     * @param keys
     * @return
     */
    @GetMapping("/translateManyDict")
    public Map<String, List<DictModel>> translateManyDict(@RequestParam("dictCodes") String dictCodes, @RequestParam("keys") String keys){
        return this.sysBaseApi.translateManyDict(dictCodes, keys);
    }



}
