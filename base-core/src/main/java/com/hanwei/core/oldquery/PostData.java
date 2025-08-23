package com.hanwei.core.oldquery;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

@Data
public class PostData {
    private int pageIndex=1 ;
    private int pageSize=10 ;
    private String orderby="";
    private String filter="";
    private List<FiltersParamSql> filterList = JSON.parseArray(filter,FiltersParamSql.class);

    public List<FiltersParamSql> getFilterList(){
        this.setFilterList();
        return filterList;
    }

    public  void setFilterList(){
        this.filterList = JSON.parseArray(filter,FiltersParamSql.class);
    }
}
