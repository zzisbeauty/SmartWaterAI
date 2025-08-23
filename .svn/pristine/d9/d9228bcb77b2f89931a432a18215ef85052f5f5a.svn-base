package com.hanwei.core.common.api;



import com.alibaba.fastjson.JSONObject;
import com.hanwei.core.base.vo.DictModel;
import com.hanwei.core.common.api.dto.FileDTO;
import com.hanwei.core.common.api.vo.LoginUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * @Description 公共方法API
 * @author zhuht
 * @Date 2024/1/30 13:41
 * @Since version-1.0
 */
public interface CommonAPI {

    LoginUser getLoginUser();

    /**
     * 查询用户角色信息
     * @param username
     * @return
     */
    Set<String> queryUserRoles(String username);


    /**
     * 查询用户权限信息
     * @param username
     * @return
     */
    Set<String> queryUserAuths(String username);


    /**
     * 根据用户账号查询用户信息
     * @param username
     * @return
     */
    public LoginUser getUserByName(String username);


    /**
     * 普通字典的翻译
     * @param code
     * @param key
     * @return
     */
    String translateDict(String code, String key);

    /**
     * 获取数据字典
     * @param code
     * @return
     */
    public List<DictModel> queryDictItemsByCode(String code,String table,String text,String keys);

    /**
     * 获取有效的数据字典项
     * @param code
     * @return
     */
    public List<DictModel> queryEnableDictItemsByCode(String code);


    /**
     * 普通字典的翻译，根据多个dictCode和多条数据，多个以逗号分割
     * @param dictCodes 例如：user_status,sex
     * @param keys 例如：1,2,0
     * @return
     */
    Map<String, List<DictModel>> translateManyDict(String dictCodes, String keys);

    /**
     * 字典表的 翻译，可批量
     * @param table
     * @param text
     * @param code
     * @param keys 多个用逗号分割
     * @return
     */
    List<DictModel> translateDictFromTableByKeys(String table, String text, String code, String keys);

    /**
     *  list结果集实体类按DICT标签字典进行转义
     * @param records 需要转义的LIST
     */
    List translateResultByDict(List records);

    /**
     * 根据字典code和text获取key
     * @param code
     * @param text
     * @return
     */
    String translateDictTextToKey(String code, String text);

    /**
     * 更新文件关联
     * @param dataId 数据id
     * @param fileIds 文件id，多个用逗号隔开
     * @throws Exception ex
     */
    String updateFileRelation(String dataId, String fileIds) throws Exception;

    /**
     * 根据数据id获取文件列表
     * @param dataId 数据id，多个用逗号隔开
     * @return list
     * @throws Exception exception
     */
    List<FileDTO> findFileList(String dataId) throws Exception;
}
