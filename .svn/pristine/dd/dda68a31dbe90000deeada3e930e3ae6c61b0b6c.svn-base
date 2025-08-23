package com.hanwei.process.util;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [常用方法]
 * @createTime : [2024/11/20 16:24]
 * @updateUser : [CX]
 * @updateTime : [2024/11/20 16:24]
 * @updateRemark : [说明本次修改内容]
 */
@Component
@Slf4j
public class CommonUtils {

    /**
     * 随机获取唤醒回复词
     */
    public static final String getWakeUpReply() {
        if(WordUtils.wakeUpReplyList.size()==1){
            return WordUtils.wakeUpReplyList.get(0);
        }
        Integer random = RandomUtil.randomInt(0,WordUtils.wakeUpReplyList.size()-1);
        return WordUtils.wakeUpReplyList.get(random);
    }
    /**
     * 随机获取休眠回复词
     */
    public static final String getSleepReply() {
        if(WordUtils.sleepReplyList.size()==1){
            return WordUtils.sleepReplyList.get(0);
        }
        Integer random = RandomUtil.randomInt(0,WordUtils.sleepReplyList.size()-1);
        return WordUtils.sleepReplyList.get(random);
    }

    /**
     * 随机获取无解答回复词
     */
    public static final String getNoAnswerReply() {
        if(WordUtils.noAnswerReplyList.size()==1){
            return WordUtils.noAnswerReplyList.get(0);
        }
        Integer random = RandomUtil.randomInt(0,WordUtils.noAnswerReplyList.size()-1);
        return WordUtils.noAnswerReplyList.get(random);
    }

    /**
     * 判断message是否包含唤醒词
     */
    public static final Boolean preWakeUp(String message) {
        for(String keyWord : WordUtils.wakeUpKeyWordList){
            if(message.contains(keyWord)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断message是否包含唤醒词
     */
    public static final Boolean preSleep(String message) {
        for(String keyWord : WordUtils.sleepKeyWordList){
            if(message.contains(keyWord)){
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        System.out.println(getWakeUpReply());
        System.out.println(getSleepReply());
        System.out.println(getNoAnswerReply());
    }
}
