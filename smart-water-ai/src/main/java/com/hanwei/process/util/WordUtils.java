package com.hanwei.process.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [各类提示词工具类]
 * @createTime : [2024/11/20 15:59]
 * @updateUser : [CX]
 * @updateTime : [2024/11/20 15:59]
 * @updateRemark : [说明本次修改内容]
 */
@Component
@Slf4j
public class WordUtils {
    /**唤醒词*/
    public static final List<String> wakeUpKeyWordList;
    /**唤醒回复词*/
    public static final List<String> wakeUpReplyList;

    /**休眠词*/
    public static final List<String> sleepKeyWordList;
    /**休眠回复词*/
    public static final List<String> sleepReplyList;

    /**无解答回复词*/
    public static final List<String> noAnswerReplyList;

    static {
        //初始化唤醒词
        wakeUpKeyWordList = new ArrayList<>();
        wakeUpKeyWordList.add("小威");

        //初始化唤醒回复词
        wakeUpReplyList = new ArrayList<>();
        wakeUpReplyList.add("您好,小威在呢~");
        wakeUpReplyList.add("我在~");
        wakeUpReplyList.add("请吩咐");
        wakeUpReplyList.add("有什么可以帮您");

        //初始化休眠词
        sleepKeyWordList = new ArrayList<>();
        sleepKeyWordList.add("结束对话");
        sleepKeyWordList.add("对话结束");
        sleepKeyWordList.add("再见");
        sleepKeyWordList.add("没有其他问题");

        //初始化休眠回复词
        sleepReplyList = new ArrayList<>();
        sleepReplyList.add("好的,再见");
        sleepReplyList.add("感谢您的使用，祝您生活愉快，再见");
        sleepReplyList.add("好的，我就等在这里，随时为你效劳，有需要尽管吩咐");
        sleepReplyList.add("好的，期待下次的相见");

        //初始化无解答回复词
        noAnswerReplyList = new ArrayList<>();
        noAnswerReplyList.add("可以请您详细描述您的问题吗?");
        noAnswerReplyList.add("对不起，我没有理解您的意思，请您换个方式问一下");
        noAnswerReplyList.add("不好意思，这个问题不在我的掌握范围内");
    }

}
