package com.hanwei.core.oldquery;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SqlHelper {
    /**
     * 附加参数
     * @param map
     * @param postData
     * @throws Exception
     */
    public static void AddFilterSqlInParameter(Map map, PostData postData) throws Exception{
        List<FiltersParamSql> fliterlist= postData.getFilterList();
        if (fliterlist==null || fliterlist.isEmpty()){
            return ;
        }
        int k=1;
        for (FiltersParamSql o:fliterlist) {
            if(o != null && !StringUtils.isBlank(o.getV()) && !o.getV().equals("")  ){
                String f=o.getF();
                f = f.substring(f.indexOf(".")+1)+String.valueOf(k);
                map.put(f,o.getV());
                k++;
            }
        }
    }



    /***
     * 拼接where sql
     * @param fliterlist
     * @return
     */
    public static String ConvertToSqlFilter(List<FiltersParamSql> fliterlist){
        if (fliterlist==null || fliterlist.isEmpty()){
            return "";
        }
        //获取循环分组条件
        List<List<CommonWhere>> outlist = new ArrayList<List<CommonWhere>>();
        List<CommonWhere> inlist = new ArrayList<CommonWhere>();
        List<String> grouplist = new ArrayList<String>();
        for (FiltersParamSql o:fliterlist) {
            if(o != null && !StringUtils.isBlank(o.getV()) && !o.getV().equals("")  ){
                //设置默认值
                if(o.getG()==null){
                    o.setG("group100");
                    o.setW("and");
                }

                if(!grouplist.contains(o.getG().toString())){
                    grouplist.add(o.getG().toString());
                }
                CommonWhere model = new CommonWhere();
                model.setData_flag(o.getW());
                model.setData_group(o.getG());
                model.setData_name(o.getF());
                model.setData_op(o.getOp());
                model.setData_type(o.getT());
                model.setData_value(o.getV());
                if(o.getPara()!=null && !StringUtils.isBlank(o.getPara()) && StringUtils.isNotEmpty(o.getPara())){
                    model.setPara_name(o.getPara());
                }else {
                    model.setPara_name("");
                }
                inlist.add(model);

            }
        }
//        inlist.stream().collect(Collectors.groupingBy(CommonWhere::getData_group, Collectors.toList()))
//                .forEach((group,value) -> {
//                    outlist.add(value);
//                });

        for(int k=0;k<grouplist.size();k++){
            String groupstr = grouplist.get(k);
            List<CommonWhere>  list =inlist.stream().filter(s->s.getData_group().equals(groupstr)).collect(Collectors.toList());

            //inlist.forEach(e->e.setData_group(groupstr));
            //List<CommonWhere> listNew =inlist;
            if(list != null&&list.size()>0){
                outlist.add(list);
            }
        }
        //拼接sql
        StringBuffer sqlWhere = new StringBuffer();
        if(outlist != null && outlist.size()>0){
            int iout = 1;
            int m =1;

            for (List<CommonWhere> item:outlist) {
                StringBuffer _sqlWhere = new StringBuffer();
                _sqlWhere.append(" (");
                if(item !=null && item.size() >0){
                    int k = 1;
                    for (CommonWhere initem:item)
                    {
                        if(k < item.size()){
                            _sqlWhere.append(initem.getData_name());
                            _sqlWhere.append("  ");
                            _sqlWhere.append(ConvertToSqlOp(initem.getData_op(),initem.getData_value(),initem.getData_type(),initem.getData_name()+String.valueOf(m),initem.getPara_name()));
                            _sqlWhere.append(" "+initem.getData_flag()+" ");
                        }else {
                            _sqlWhere.append(initem.getData_name());
                            _sqlWhere.append("");
                            _sqlWhere.append(ConvertToSqlOp(initem.getData_op(),initem.getData_value(),initem.getData_type(),initem.getData_name()+String.valueOf(m),initem.getPara_name()));
                        }
                        k++;
                        m++;
                    }
                }
                _sqlWhere.append(") ");
                if(iout < outlist.size()){
                    sqlWhere.append(_sqlWhere+" and ");
                }else {
                    sqlWhere.append(_sqlWhere);
                }
                iout++;
            }
            //sqlWhere.append(" and (99=99)");
        }else {
            //sqlWhere.append(" (99=99)  ");
        }
        return sqlWhere.toString();
    }




    /***
     * 替换SQL运算符判断条件 eq 等于，ne 不等于,gt 大于，ge大于等于，lt小于，le小于等于 like 模糊，in 包含；
     * @param op
     * @param v  字段值
     * @param t  字段类型 s是字符串 d是时间格式
     * @param f  字段名称
     * @param p  参数
     * @return
     */
    public static String ConvertToSqlOp(String op, String v, String t, String f, String p){

        f = f.substring(f.indexOf(".")+1);
        if (StringUtils.isNotEmpty(p) && !StringUtils.isBlank(p) && !p.equals("")){
            f=p;
        }
        switch (op)
        {
            case "eq"://等于
                op = "=";
                break;
            case "ne"://不等于
                op = "<>";
                break;
            case "gt"://大于
                op = ">";
                break;
            case "ge"://大于等于
                op = ">=";
                break;
            case "lt"://小于
                op = "<";
                break;
            case "le"://小于等于
                op = "<=";
                break;
            default:
                break;
        }
        //当操作符为LIKE时返回like语句并添加金相应的值V
        if(op.trim().toUpperCase().equals("LIKE")){
             return  " like '%'+"+"#{"+ f+"}"+"+'%'";

//           return  " like concat('%',"+"#{"+f+"}"+",'%')";
        }
        //当操作符为in是返回In语句并将V值添加
        if(op.trim().toUpperCase().equals("IN")){
            return " IN ("+v+")";
        }

        return op  +" #{"+ f+"}";
    }

}
