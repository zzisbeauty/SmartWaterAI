package com.hanwei.core.common.api.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.base.mapper.SysDictMapper;
import com.hanwei.core.base.vo.DictModel;
import com.hanwei.core.base.vo.DictModelMany;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.dto.FileDTO;
import com.hanwei.core.common.api.vo.LoginUser;
import com.hanwei.core.common.database.DataBaseConstant;
import com.hanwei.core.util.RedisUtil;
import com.hanwei.core.util.SqlInjectionUtil;
import com.hanwei.core.util.oConvertUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/***
 * @Description 底层共通业务API，提供其他独立模块调用
 * @author zhuht
 * @Date 2024/1/30 13:48
 * @Since version-1.0
 */
@Slf4j
@Service
public class SysBaseApiImpl implements CommonAPI {

	@Autowired
	@Lazy
	private SysDictMapper sysDictMapper;

	@Resource
	private ObjectMapper objectMapper;

	public static final String SYS_DICT_CACHE = "sys:cache:dict";

	/**
	 * 字典信息缓存 status为有效的
	 */
	public static final String SYS_ENABLE_DICT_CACHE = "sys:cache:dictEnable";
	/**
	 * 表字典信息缓存
	 */
	public static final String SYS_DICT_TABLE_CACHE = "sys:cache:dictTable";

	@Value(value = "${file-server.update:}")
	private String FILE_UPDATE_URL;
	@Value(value = "${file-server.list:}")
	private String FILE_LIST_URL;

    /**
     * 查询登录用户信息
     *
     * @param
     * @return
     */
    @Override
    public LoginUser getLoginUser() {
		LoginUser sysUser = new LoginUser();
		sysUser.setUsername("anonymousUser");
        sysUser.setTenementguid("anonymousUser");
		if(Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).isPresent() ){
			Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(Optional.ofNullable(object).isPresent() &&  object instanceof LoginUser){
				sysUser = (LoginUser)object;
			}
		}
        return sysUser;
    }

    /**
     * 查询用户拥有的角色集合
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> queryUserRoles(String username) {
		//TODO
		return null;
    }

	/**
	 * 查询用户拥有的权限集合
	 *
	 * @param username
	 * @return
	 */
	@Override
	public Set<String> queryUserAuths(String username) {
		//TODO
		return null;
	}

	/**
	 * 根据用户账号查询用户信息
	 * @param username
	 * @return
	 */
	@Override
	public LoginUser getUserByName(String username) {
		if (oConvertUtils.isEmpty(username)) {
			return null;
		}
		//TODO
		return null;
	}

	/**
	 * 普通字典的翻译
	 * @param code
	 * @param key
	 * @return
	 */
    @Override
	@Cacheable(value = SYS_DICT_CACHE,key = "#code+':'+#key", unless = "#result == null ")
    public String translateDict(String code, String key) {
        return queryDictTextByKey(code, key);
    }


	/**
	 * 获取数据字典
	 * @param code
	 * @return
	 */
	@Override
	@Cacheable(value = SYS_DICT_CACHE, key = "#code", unless = "#result == null ")
	public List<DictModel> queryDictItemsByCode(String code,String table,String text,String keys) {
		log.debug("无缓存dictCache的时候调用这里！");
		if(StringUtils.isEmpty(table) && StringUtils.isEmpty(text)){
			return sysDictMapper.queryDictItemsByCode(code);
		}

		return translateDictFromTableByKeys(table,text,code,keys);

	}

	/**
	 * 获取有效的数据字典项
	 * @param code
	 * @return
	 */
	@Override
	@Cacheable(value = SYS_ENABLE_DICT_CACHE, key = "#code", unless = "#result == null ")
	public List<DictModel> queryEnableDictItemsByCode(String code) {
		log.debug("无缓存dictCache的时候调用这里！");
		return sysDictMapper.queryEnableDictItemsByCode(code);
	}



	/**
	 * 普通字典的翻译，根据多个dictCode和多条数据，多个以逗号分割
	 * @param dictCodes 例如：user_status,sex
	 * @param keys 例如：1,2,0
	 * @return
	 */
	@Override
	public Map<String, List<DictModel>> translateManyDict(String dictCodes, String keys) {
		List<String> dictCodeList = Arrays.asList(dictCodes.split(","));
		List<DictModelMany> list = sysDictMapper.queryManyDictByKeys(dictCodeList, Arrays.asList(keys.split(",")));
		Map<String, List<DictModel>> dictMap = new HashMap(5);
		for (DictModelMany dict : list) {
			List<DictModel> dictItemList = dictMap.computeIfAbsent(dict.getDictCode(), i -> new ArrayList<>());
			dictItemList.add(new DictModel(dict.getValue(), dict.getText()));
		}
		return dictMap;
	}

	/**
	 * 字典表的 翻译，可批量
	 * @param table
	 * @param text
	 * @param code
	 * @param keys 多个用逗号分割
	 * @return
	 */
	@Override
	public List<DictModel> translateDictFromTableByKeys(String table, String text, String code, String keys) {
		//@dict注解支持 dicttable 设置where条件
		String filterSql = null;
		if(table.toLowerCase().indexOf(DataBaseConstant.SQL_WHERE)>0){
			String[] arr = table.split(" (?i)where ");
			table = arr[0];
			filterSql = arr[1];
		}
		String[] tableAndFields = new String[]{table, text, code};
		SqlInjectionUtil.filterContent(tableAndFields);
		SqlInjectionUtil.specialFilterContentForDictSql(filterSql);

		return sysDictMapper.queryTableDictByKeysAndFilterSql(table, text, code, filterSql, Arrays.asList(keys.split(",")));
	}

	/**
	 *  list结果集实体类按DICT标签字典进行转义
	 * @param records 需要转义的LIST
	 */
	@Override
	public List<T> translateResultByDict(List records) {
		if (records instanceof List) {
			List<JSONObject> items = new ArrayList<>();

			//step.1 筛选出加了 Dict 注解的字段列表
			List<Field> dictFieldList = new ArrayList<>();
			// 字典数据列表， key = 字典code，value=数据列表
			Map<String, List<String>> dataListMap = new HashMap<>(5);

			//判断是否含有字典注解,没有注解返回
			Boolean hasDict= checkHasDict(records);
			if(!hasDict){
				return records;
			}

			log.debug(" __ 进入字典翻译切面 DictAspect —— " );

			for (Object record : records) {
				String json="{}";
				try {
					//DictAspect Jackson序列化报错-----
					//解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
					json = objectMapper.writeValueAsString(record);
					//DictAspect Jackson序列化报错-----
				} catch (JsonProcessingException e) {
					log.error("json解析失败"+e.getMessage(),e);
				}
				//解决restcontroller返回json数据后key顺序错乱 -----
				JSONObject item = JSONObject.parseObject(json, Feature.OrderedField);

				//解决继承实体字段无法翻译问题------
				// 遍历所有字段，把字典Code取出来，放到 map 里
				for (Field field : oConvertUtils.getAllFields(record)) {
					String value = item.getString(field.getName());
					if (oConvertUtils.isEmpty(value)) {
						continue;
					}
					if (field.getAnnotation(Dict.class) != null) {
						if (!dictFieldList.contains(field)) {
							dictFieldList.add(field);
						}
						String code = field.getAnnotation(Dict.class).dicCode();
						String text = field.getAnnotation(Dict.class).dicText();
						String table = field.getAnnotation(Dict.class).dictTable();

						List<String> dataList;
						String dictCode = code;
						if (!StringUtils.isEmpty(table)) {
							dictCode = String.format("%s,%s,%s", table, text, code);
						}
						dataList = dataListMap.computeIfAbsent(dictCode, k -> new ArrayList<>());
						this.listAddAllDeduplicate(dataList, Arrays.asList(value.split(",")));
					}
				}
				items.add(item);
			}

			//step.2 调用翻译方法，一次性翻译
			Map<String, List<DictModel>> translText = this.translateAllDict(dataListMap);

			//step.3 将翻译结果填充到返回结果里
			for (JSONObject record : items) {
				for (Field field : dictFieldList) {
					String code = field.getAnnotation(Dict.class).dicCode();
					String text = field.getAnnotation(Dict.class).dicText();
					String table = field.getAnnotation(Dict.class).dictTable();

					String fieldDictCode = code;
					if (!StringUtils.isEmpty(table)) {
						fieldDictCode = String.format("%s,%s,%s", table, text, code);
					}

					String value = record.getString(field.getName());
					if (oConvertUtils.isNotEmpty(value)) {
						List<DictModel> dictModels = translText.get(fieldDictCode);
						if(dictModels==null || dictModels.size()==0){
							continue;
						}

						String textValue = this.translDictText(dictModels, value);

						record.put(field.getName() + CommonConstant.DICT_TEXT_SUFFIX, textValue);
					}
				}
			}
			records = items;
		}
		return records;
	}


	/**
	 * 根据字典code和text获取key
	 * @param code
	 * @param text
	 * @return
	 */
	@Override
	public String translateDictTextToKey(String code, String text) {
		return sysDictMapper.translateDictTextToKey(code,text);
	}

	/**
	 * 通过查询指定code 获取字典值text
	 * @param code
	 * @param key
	 * @return
	 */
	public String queryDictTextByKey(String code, String key) {
		log.debug("无缓存dictText的时候调用这里！");
		return sysDictMapper.queryDictTextByKey(code, key);
	}

	/**
	 * 检测返回结果集中是否包含Dict注解
	 * @param records
	 * @return
	 */
	private Boolean checkHasDict(List<T> records){
		if(oConvertUtils.isNotEmpty(records) && records.size()>0){
			for (Field field : oConvertUtils.getAllFields(records.get(0))) {
				if (oConvertUtils.isNotEmpty(field.getAnnotation(Dict.class))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * list 去重添加
	 */
	private void listAddAllDeduplicate(List<String> dataList, List<String> addList) {
		// 筛选出dataList中没有的数据
		List<String> filterList = addList.stream().filter(i -> !dataList.contains(i)).collect(Collectors.toList());
		dataList.addAll(filterList);
	}

	/**
	 * 一次性把所有的字典都翻译了
	 * 1.  所有的普通数据字典的所有数据只执行一次SQL
	 * 2.  表字典相同的所有数据只执行一次SQL
	 * @param dataListMap
	 * @return
	 */
	private Map<String, List<DictModel>> translateAllDict(Map<String, List<String>> dataListMap) {
		// 翻译后的字典文本，key=dictCode
		Map<String, List<DictModel>> translText = new HashMap<>(5);
		// 需要翻译的数据（有些可以从redis缓存中获取，就不走数据库查询）
		List<String> needTranslData = new ArrayList<>();
		//step.1 先通过redis中获取缓存字典数据
		for (String dictCode : dataListMap.keySet()) {
			List<String> dataList = dataListMap.get(dictCode);
			if (dataList.size() == 0) {
				continue;
			}
			// 表字典需要翻译的数据
			List<String> needTranslDataTable = new ArrayList<>();
			for (String s : dataList) {
				String data = s.trim();
				if (data.length() == 0) {
					continue; //跳过循环
				}
				if (dictCode.contains(",")) {
					String keyString = String.format("sys:cache:dictTable::SimpleKey [%s,%s]", dictCode, data);
					if (RedisUtil.hasKey(keyString)) {
						try {
							String text = oConvertUtils.getString(RedisUtil.get(keyString));
							List<DictModel> list = translText.computeIfAbsent(dictCode, k -> new ArrayList<>());
							list.add(new DictModel(data, text));
						} catch (Exception e) {
							log.warn(e.getMessage());
						}
					} else if (!needTranslDataTable.contains(data)) {
						// 去重添加
						needTranslDataTable.add(data);
					}
				} else {
					String keyString = String.format("sys:cache:dict::%s:%s", dictCode, data);
					if (RedisUtil.hasKey(keyString)) {
						try {
							String text = oConvertUtils.getString(RedisUtil.get(keyString));
							List<DictModel> list = translText.computeIfAbsent(dictCode, k -> new ArrayList<>());
							list.add(new DictModel(data, text));
						} catch (Exception e) {
							log.warn(e.getMessage());
						}
					} else if (!needTranslData.contains(data)) {
						// 去重添加
						needTranslData.add(data);
					}
				}

			}
			//step.2 调用数据库翻译表字典
			if (needTranslDataTable.size() > 0) {
				String[] arr = dictCode.split(",");
				String table = arr[0], text = arr[1], code = arr[2];
				String values = String.join(",", needTranslDataTable);
				log.debug("translateDictFromTableByKeys.dictCode:" + dictCode);
				log.debug("translateDictFromTableByKeys.values:" + values);
				List<DictModel> texts = translateDictFromTableByKeys(table, text, code, values);
				log.debug("translateDictFromTableByKeys.result:" + texts);
				List<DictModel> list = translText.computeIfAbsent(dictCode, k -> new ArrayList<>());
				list.addAll(texts);

				// 做 redis 缓存
				for (DictModel dict : texts) {
					String redisKey = String.format("sys:cache:dictTable::SimpleKey [%s,%s]", dictCode, dict.getValue());
					try {
						// 保留5分钟
						RedisUtil.set(redisKey, dict.getText(), 300);
					} catch (Exception e) {
						log.warn(e.getMessage(), e);
					}
				}
			}
		}

		//step.3 调用数据库进行翻译普通字典
		if (needTranslData.size() > 0) {
			List<String> dictCodeList = Arrays.asList(dataListMap.keySet().toArray(new String[]{}));
			// 将不包含逗号的字典code筛选出来，因为带逗号的是表字典，而不是普通的数据字典
			List<String> filterDictCodes = dictCodeList.stream().filter(key -> !key.contains(",")).collect(Collectors.toList());
			String dictCodes = String.join(",", filterDictCodes);
			String values = String.join(",", needTranslData);
			log.debug("translateManyDict.dictCodes:" + dictCodes);
			log.debug("translateManyDict.values:" + values);
			Map<String, List<DictModel>> manyDict = translateManyDict(dictCodes, values);
			log.debug("translateManyDict.result:" + manyDict);
			for (String dictCode : manyDict.keySet()) {
				List<DictModel> list = translText.computeIfAbsent(dictCode, k -> new ArrayList<>());
				List<DictModel> newList = manyDict.get(dictCode);
				list.addAll(newList);

				// 做 redis 缓存
				for (DictModel dict : newList) {
					String redisKey = String.format("sys:cache:dict::%s:%s", dictCode, dict.getValue());
					try {
						RedisUtil.set(redisKey, dict.getText());
					} catch (Exception e) {
						log.warn(e.getMessage(), e);
					}
				}
			}
		}
		return translText;
	}

	/**
	 * 字典值替换文本
	 *
	 * @param dictModels
	 * @param values
	 * @return
	 */
	private String translDictText(List<DictModel> dictModels, String values) {
		List<String> result = new ArrayList<>();

		// 允许多个逗号分隔，允许传数组对象
		String[] splitVal = values.split(",");
		for (String val : splitVal) {
			String dictText = val;
			for (DictModel dict : dictModels) {
				if (val.equals(dict.getValue())) {
					dictText = dict.getText();
					break;
				}
			}
			result.add(dictText);
		}
		return String.join(",", result);
	}

	/**
	 * 更新文件关联
	 * @param dataId 数据id
	 * @param fileIds 文件id，多个用逗号隔开
	 * @throws Exception ex
	 */
	@Override
	public String updateFileRelation(String dataId, String fileIds) throws Exception {
		try {
			if(StrUtil.isEmpty(FILE_UPDATE_URL)){
				return "未配置文件服务相关参数";
			}
			JSONObject params = new JSONObject();
			params.put("dataId", dataId);
			params.put("fileIds", fileIds);
			String result = HttpUtil.post(FILE_UPDATE_URL, params.toJSONString());
			log.info("文件更新结果:{}", result);
			JSONObject jsonObject = JSONObject.parseObject(result);
			if (!jsonObject.getBoolean("success")) {
				return "文件更新失败 " + jsonObject.getString("message");
			}
			return "文件更新成功";
		} catch (Exception e) {
			log.error("文件更新失败", e);
			throw new Exception("文件更新失败");
		}
	}

	/**
	 * 根据数据id获取文件列表
	 * @param dataId 数据id，多个用逗号隔开
	 * @return list
	 * @throws Exception exception
	 */
	@Override
	public List<FileDTO> findFileList(String dataId) throws Exception {
		try {
			if(StrUtil.isEmpty(FILE_LIST_URL)){
				throw new Exception("未配置文件服务相关参数 ");
			}
			String url = FILE_LIST_URL + "?dataId=" + dataId;
			String result = HttpUtil.get(url);

			log.info("文件列表结果:{}", result);
			JSONObject jsonObject = JSONObject.parseObject(result);
			if (!jsonObject.getBoolean("success")) {
				throw new Exception("文件列表获取失败 " + jsonObject.getString("message"));
			}

			return jsonObject.getJSONArray("content").toJavaList(FileDTO.class);
		} catch (Exception e) {
			log.error("文件列表获取失败", e);
			throw new Exception("文件列表获取失败");
		}
	}

	public static void main(String[] args) throws Exception {
		String url = "http://10.0.15.40:9008/file/getFileList" + "?dataId=" + "123456";
		String result = HttpUtil.get(url);

		log.info("文件列表结果:{}", result);
		JSONObject jsonObject = JSONObject.parseObject(result);
		if (!jsonObject.getBoolean("success")) {
			throw new Exception("文件列表获取失败 " + jsonObject.getString("message"));
		}

		System.out.println( jsonObject.getJSONArray("content").toJavaList(FileDTO.class));
	}
}
