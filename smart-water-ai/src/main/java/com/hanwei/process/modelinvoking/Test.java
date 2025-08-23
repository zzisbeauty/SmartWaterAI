package com.hanwei.process.modelinvoking;

import com.alibaba.fastjson.JSON;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.vo.RankResult;
import com.hanwei.process.vo.ResultForFrontVo;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;


/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/10/25 13:47]
 * @updateUser : [CX]
 * @updateTime : [2024/10/25 13:47]
 * @updateRemark : [说明本次修改内容]
 */
public class Test {
    public static void main(String[] args) {
        int len = 0;
        int size = 2050;
        int step = 1024;
        while(len < size){

            if(len + step > size){
                step = size - len -1;
                len += step;
                break;
            }else{
                len += step;
            }
        }
    }
}
