package com.hanwei.core.oldquery;

import com.alibaba.fastjson.JSON;
import com.hanwei.core.common.api.vo.HttpResult;
import com.hanwei.core.util.APIHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PublicHelper {
    private static SqlSessionFactory factory;

    public static HttpResult GetGridDbDataNocheck(String mapperStr, String functionName, PostData oPostData) throws Exception {

        HttpResult httpResult = new HttpResult();


        //3.执行
        Map mapparam = new HashMap();
        mapparam.put("postdata", SqlHelper.ConvertToSqlFilter(oPostData.getFilterList()));
        SqlHelper.AddFilterSqlInParameter(mapparam, oPostData);

        SqlSession sqlsession = factory.openSession();
        List<LinkedHashMap> list = sqlsession.selectList(getSqlId(mapperStr, functionName), mapparam);
        sqlsession.close();
        Map mapResult = new HashMap();
        mapResult.put("rows", list);
        mapResult.put("total", list.size());

        httpResult.setResult(true);
        httpResult.setKeyValue(JSON.toJSONString(mapResult));
        return httpResult;

    }

    @Autowired
    public void setFactory(SqlSessionFactory factory) {
        PublicHelper.factory = factory;
    }

    /****
     * 获取要调用的mapper方法
     * @param mapperStr       要调用的mapper类
     * @param functionName    要调用的方法
     * @return sqlId          返回sqlId路径
     */
    public static String getSqlId(String mapperStr, String functionName) {
        String sqlId = mapperStr + "." + functionName;
        return sqlId;
    }


    /**
     * 获取列表
     *
     * @param mapperStr
     * @param functionName
     * @param oPostData
     * @param ticket
     * @return
     * @throws Exception
     */
    public static HttpResult GetGridDbDataNoPage(String mapperStr, String functionName, PostData oPostData, String ticket) throws Exception {
        HttpResult httpResult = new HttpResult();
        //1.参数校验
        if (StringUtils.isEmpty(ticket)) {
            httpResult.setResult(false);
            httpResult.setKeyValue(null);
            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.GetGridDbDataNoPage]的输入参数[ticket]为NULL!");
            return httpResult;
        }
        //2.解析参数
        httpResult = APIHelper.GetTenementGUID(ticket);
        if (!httpResult.getResult()) {
            httpResult.setKeyValue(null);
            return httpResult;
        }
        String tenementguid = httpResult.getKeyValue();

        //3.执行
        Map mapparam = new HashMap();
        mapparam.put("postdata", SqlHelper.ConvertToSqlFilter(oPostData.getFilterList()));
        mapparam.put("tenementguid", tenementguid);
        SqlHelper.AddFilterSqlInParameter(mapparam, oPostData);

        SqlSession sqlsession = factory.openSession();
        List<LinkedHashMap> list = sqlsession.selectList(getSqlId(mapperStr, functionName), mapparam);
        sqlsession.close();
        Map mapResult = new HashMap();
        mapResult.put("rows", list);
        mapResult.put("total", list.size());

        httpResult.setResult(true);
        httpResult.setKeyValue(JSON.toJSONString(mapResult));
        return httpResult;
    }

    public static HttpResult GetGridDbDataNoPageNoTicket(String mapperStr, String functionName) throws Exception {
        HttpResult httpResult = new HttpResult();

        String tenementguid = httpResult.getKeyValue();


        SqlSession sqlsession = factory.openSession();
        List<LinkedHashMap> list = sqlsession.selectList(getSqlId(mapperStr, functionName));
        sqlsession.close();
        Map mapResult = new HashMap();
        mapResult.put("rows", list);
        mapResult.put("total", list.size());

        httpResult.setResult(true);
        httpResult.setKeyValue(JSON.toJSONString(mapResult));
        return httpResult;
    }

    /**
     * 获取列表
     *
     * @param mapperStr
     * @param functionName
     * @param oPostData
     * @param ticket
     * @return
     * @throws Exception
     */
    public static List<Map> GetGridDbDataNoPageList(String mapperStr, String functionName, PostData oPostData, String ticket) throws Exception {
        HttpResult httpResult = new HttpResult();
        List<Map> list = new ArrayList<>();

        //2.解析参数
        httpResult = APIHelper.GetTenementGUID(ticket);
        if (!httpResult.getResult()) {
            httpResult.setKeyValue(null);
            return list;
        }
        String tenementguid = httpResult.getKeyValue();

        //3.执行
        Map mapparam = new HashMap();
        mapparam.put("postdata", SqlHelper.ConvertToSqlFilter(oPostData.getFilterList()));
        mapparam.put("tenementguid", tenementguid);
        SqlHelper.AddFilterSqlInParameter(mapparam, oPostData);

        SqlSession sqlsession = factory.openSession();
        list = sqlsession.selectList(getSqlId(mapperStr, functionName), mapparam);
        sqlsession.close();
        return list;
    }

    /**
     * 获取分页列表
     *
     * @param mapperStr
     * @param functionName
     * @param oPostData
     * @param ticket
     * @return
     * @throws Exception
     */
    public static HttpResult GetGridDbDataForPage(String mapperStr, String functionName, PostData oPostData, String ticket) throws Exception {
        HttpResult httpResult = new HttpResult();
//        //1.参数校验
//        if (StringUtils.isEmpty(ticket)) {
//            httpResult.setResult(false);
//            httpResult.setKeyValue(null);
//            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.GetGridDbDataForPage]的输入参数[ticket]为NULL!");
//            return httpResult;
//        }
//        //2.解析参数
//        httpResult = APIHelper.GetTenementGUID(ticket);
//        if (!httpResult.getResult()) {
//            httpResult.setKeyValue(null);
//            return httpResult;
//        }
//        String tenementguid = httpResult.getKeyValue();

        //3.执行
        SqlSession sqlsession = factory.openSession();
        Map mapparam = new HashMap();
        mapparam.put("postdata", SqlHelper.ConvertToSqlFilter(oPostData.getFilterList()));
//        mapparam.put("tenementguid", tenementguid);
        mapparam.put("orderby", oPostData.getOrderby());
        var pageSize = oPostData.getPageSize();
        var pageIndex = oPostData.getPageIndex();
        mapparam.put("pageIndex", (pageIndex - 1) * pageSize + 1);
        mapparam.put("pageSize", pageSize * pageIndex);
        SqlHelper.AddFilterSqlInParameter(mapparam, oPostData);

        List<LinkedHashMap> list = sqlsession.selectList(getSqlId(mapperStr, functionName), mapparam);
        sqlsession.close();
        Map mapResult = new HashMap();
        mapResult.put("rows", list);
        if (list.size() > 0) {
            HashMap total = list.get(0);
            mapResult.put("total", total.get("totalnum"));
        } else {
            mapResult.put("total", 0);
        }

        httpResult.setResult(true);
        httpResult.setKeyValue(JSON.toJSONString(mapResult));
        return httpResult;
    }


    /**
     * 获取表单数据
     *
     * @param mapperStr
     * @param functionName
     * @param oid
     * @param ticket
     * @return
     * @throws Exception
     */
    public static HttpResult GetFormDbData(String mapperStr, String functionName, String oid, String ticket) throws Exception {
        HttpResult httpResult = new HttpResult();
        SqlSession sqlsession = factory.openSession();
        //1.参数校验
        if (StringUtils.isEmpty(ticket)) {
            httpResult.setResult(false);
            httpResult.setKeyValue(null);
            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.GetFormDbData]的输入参数[oid]为NULL!");
            return httpResult;
        }
        //2.执行
        Map mapparam = new HashMap();
        mapparam.put("oid", oid);

        List<LinkedHashMap> list = sqlsession.selectList(getSqlId(mapperStr, functionName), mapparam);


        sqlsession.close();
        Map mapResult = new HashMap();
        mapResult.put("rows", list);
        mapResult.put("total", list.size());

        httpResult.setResult(true);
        httpResult.setKeyValue(JSON.toJSONString(mapResult));
        return httpResult;
    }

    /**
     * 获取表单数据
     *
     * @param mapperStr
     * @param functionName
     * @param ticket
     * @return
     * @throws Exception
     */
    public static HttpResult GetDataByParam(String mapperStr, String functionName, Map oPostData, String ticket) throws Exception {
        HttpResult httpResult = new HttpResult();
        SqlSession sqlsession = factory.openSession();
        //1.参数校验
        if (StringUtils.isEmpty(ticket)) {
            httpResult.setResult(false);
            httpResult.setKeyValue(null);
            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.GetGridDbData]的输入参数[oid]为NULL!");
            return httpResult;
        }

        List<LinkedHashMap> list = sqlsession.selectList(getSqlId(mapperStr, functionName), oPostData);

        sqlsession.close();
        Map mapResult = new HashMap();
        mapResult.put("rows", list);
        mapResult.put("total", list.size());

        httpResult.setResult(true);
        httpResult.setKeyValue(JSON.toJSONString(mapResult));
        return httpResult;
    }

    /**
     * 获取表单数据
     *
     * @param mapperStr
     * @param functionName
     * @param ticket
     * @return
     * @throws Exception
     */
    public static List<Map> GetListBydate(String mapperStr, String functionName, Map oPostData, String ticket) throws Exception {
        HttpResult httpResult = new HttpResult();
        SqlSession sqlsession = factory.openSession();


        List<LinkedHashMap> list = sqlsession.selectList(getSqlId(mapperStr, functionName), oPostData);

        sqlsession.close();
        List<Map> listResult = new ArrayList<Map>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        String currentTime = dateFormat.format(date1);

        for (Map map : list) {
            map.put("ddatadate", currentTime + " " + map.get("ddatadate").toString().substring(11, 19));
            listResult.add(map);
        }

        return listResult;
    }

    /**
     * 获取表单数据
     *
     * @param mapperStr
     * @param functionName
     * @param oid
     * @param ticket
     * @return
     * @throws Exception
     */
    public static HttpResult GetGridDbData(String mapperStr, String functionName, String oid, String ticket) throws Exception {
        HttpResult httpResult = new HttpResult();
        SqlSession sqlsession = factory.openSession();
        //1.参数校验
        if (StringUtils.isEmpty(ticket)) {
            httpResult.setResult(false);
            httpResult.setKeyValue(null);
            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.GetGridDbData]的输入参数[oid]为NULL!");
            return httpResult;
        }
        //2.解析参数
        httpResult = APIHelper.GetUserGUIDAndTenementGUID(ticket);
        if (!httpResult.getResult()) {
            httpResult.setKeyValue(null);
            return httpResult;
        }
        Map mapUser = new HashMap();
        mapUser = JSON.parseObject(httpResult.getKeyValue());
        String tenementguid = mapUser.get("tenementguid").toString();
        String userguid = mapUser.get("userguid").toString();
        Map mapparam = new HashMap();
        mapparam.put("oid", oid);
        mapparam.put("tenementguid", tenementguid);

        List<LinkedHashMap> list = sqlsession.selectList(getSqlId(mapperStr, functionName), mapparam);


        sqlsession.close();
        Map mapResult = new HashMap();
        mapResult.put("rows", list);
        mapResult.put("total", list.size());

        httpResult.setResult(true);
        httpResult.setKeyValue(JSON.toJSONString(mapResult));
        return httpResult;
    }

    /**
     * 插入(Slq语句)
     *
     * @param oPostData
     * @return
     */
    public static HttpResult insert(String mapperStr, String functionName, LinkedHashMap oPostData) {
        HttpResult httpResult = new HttpResult();
        //1.参数校验
        if (StringUtils.isEmpty(mapperStr)) {
            httpResult.setResult(false);
            httpResult.setKeyValue(null);
            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.insert]的输入参数[mapperStr]为NULL!");
            return httpResult;
        }
        if (StringUtils.isEmpty(functionName)) {
            httpResult.setResult(false);
            httpResult.setKeyValue(null);
            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.insert]的输入参数[functionName]为NULL!");
            return httpResult;
        }
        SqlSession sqlsession = factory.openSession();
        try {
            int result = sqlsession.insert(getSqlId(mapperStr, functionName), oPostData);
            if (result <= 0) {
                httpResult.setErrorMessage("操作失败!");
                httpResult.setResult(false);
            }
        } catch (Exception e) {
            httpResult.setErrorMessage(e.toString());
            httpResult.setResult(false);
            e.printStackTrace();
        } finally {
            sqlsession.close();
        }
        return httpResult;
    }

    /**
     * 修改(Slq语句)
     *
     * @param oPostData
     * @return
     */
    public static HttpResult update(String mapperStr, String functionName, LinkedHashMap oPostData) {
        HttpResult httpResult = new HttpResult();
        //1.参数校验
        if (StringUtils.isEmpty(mapperStr)) {
            httpResult.setResult(false);
            httpResult.setKeyValue(null);
            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.update]的输入参数[mapperStr]为NULL!");
            return httpResult;
        }
        if (StringUtils.isEmpty(functionName)) {
            httpResult.setResult(false);
            httpResult.setKeyValue(null);
            httpResult.setErrorMessage("运行时异常：函数[PublicHelper.update]的输入参数[functionName]为NULL!");
            return httpResult;
        }
        SqlSession sqlsession = factory.openSession();
        try {
            int result = sqlsession.update(getSqlId(mapperStr, functionName), oPostData);
            if (result <= 0) {
                httpResult.setErrorMessage("操作失败!");
                httpResult.setResult(false);
            }
        } catch (Exception e) {
            httpResult.setErrorMessage(e.toString());
            httpResult.setResult(false);
            e.printStackTrace();
        } finally {
            sqlsession.close();
        }
        return httpResult;
    }


    public static List<Map> GetGridDbDataNoPageListMap(String mapperStr, String functionName, PostData oPostData, String ticket) throws Exception {

        //3.执行
        Map mapparam = new HashMap();
        mapparam.put("postdata", SqlHelper.ConvertToSqlFilter(oPostData.getFilterList()));
        SqlHelper.AddFilterSqlInParameter(mapparam, oPostData);
        SqlSession sqlsession = factory.openSession();
        List<Map> list = sqlsession.selectList(getSqlId(mapperStr, functionName), mapparam);
        sqlsession.close();

        return list;
    }


    public static int GetGridDbDataNoPagecount(String mapperStr, String functionName, PostData oPostData, String ticket) throws Exception {

        //3.执行
        Map mapparam = new HashMap();
        mapparam.put("postdata", SqlHelper.ConvertToSqlFilter(oPostData.getFilterList()));
        SqlHelper.AddFilterSqlInParameter(mapparam, oPostData);
        SqlSession sqlsession = factory.openSession();
        int count = sqlsession.selectOne(getSqlId(mapperStr, functionName), mapparam);
        sqlsession.close();

        return count;
    }


}
