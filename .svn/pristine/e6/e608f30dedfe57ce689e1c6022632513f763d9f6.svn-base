package com.hanwei.core.base.mapper;


import com.github.yulichang.base.MPJBaseMapper;
import com.hanwei.core.base.entity.SysDict;
import com.hanwei.core.base.vo.DictModel;
import com.hanwei.core.base.vo.DictModelMany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface SysDictMapper extends MPJBaseMapper<SysDict> {


	/**
	 * 查询字典表的数据
	 * @param table 表名
	 * @param text   显示字段名
	 * @param code   存储字段名
	 * @param filterSql 条件sql
	 * @param codeValues 存储字段值 作为查询条件in
	 * @return
	 */

	List<DictModel> queryTableDictByKeysAndFilterSql(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("filterSql") String filterSql, @Param("codeValues") List<String> codeValues);

	/**
	 * 可通过多个字典code查询翻译文本
	 * @param dictCodeList 多个字典code
	 * @param keys 数据列表
	 * @return
	 */
	List<DictModelMany> queryManyDictByKeys(@Param("dictCodeList") List<String> dictCodeList, @Param("keys") List<String> keys);

	/**
	 * 通过字典code获取字典数据
	 * @param code
	 * @param key
	 * @return
	 */
	public String queryDictTextByKey(@Param("code") String code,@Param("key") String key);

	/**
	 * 通过字典code获取字典数据
	 * @param code 字典code
	 * @return  List<DictModel>
	 */
	public List<DictModel> queryDictItemsByCode(@Param("code") String code);

	/**
	 * 查询有效的数据字典项
	 * @param code
	 * @return
	 */
	List<DictModel> queryEnableDictItemsByCode(@Param("code") String code);


	/**
	 * 通过多个字典code获取字典数据
	 *
	 * @param dictCodeList
	 * @return
	 */
	public List<DictModelMany> queryDictItemsByCodeList(@Param("dictCodeList") List<String> dictCodeList);

	/**
	 * 根据字典code和text获取key
	 * @param code
	 * @param text
	 * @return
	 */
    String translateDictTextToKey(String code, String text);
}
