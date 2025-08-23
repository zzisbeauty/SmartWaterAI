package com.hanwei.process.modelinvoking.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

/**
 * @Description: 模型调用实体
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelInvokingInfo {

	/**消息应用id*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	/**创建人*/
	@ExcelProperty("创建人")
	@ColumnWidth(15)
	private String createBy;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
	@ExcelProperty("更新人")
	@ColumnWidth(15)
	private String updateBy;
	/**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**tenementguid*/
	@ExcelProperty("tenementguid")
	@ColumnWidth(15)
	private String tenementguid;
}
