package com.hanwei.core.common.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanwei.core.annotation.SensitiveField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collections;
import java.util.Date;

/**
 * <p>
 * @Description:登录用户信息
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties({"enabled","accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
//public class LoginUser implements UserDetails {
public class LoginUser{

	/**
	 * 登录人id
	 */
	@SensitiveField
	private String id;

	/**
	 * 登录人账号
	 */
	@SensitiveField
	private String username;

	/**
	 * 登录人名字
	 */
	@SensitiveField
	private String realname;

	/**
	 * 登录人密码
	 */
	@SensitiveField
	private String password;

     /**
      * 当前登录部门code
      */
    private String orgCode;

	/**
	 * 当前登录部门名称
	 */
	private String orgName;


	/**
	 * 头像
	 */
	@SensitiveField
	private String avatar;

	/**
	 * 生日
	 */
	@SensitiveField
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	/**
	 * 性别（1：男 2：女）
	 */
	private Integer sex;

	/**
	 * 电子邮件
	 */
	@SensitiveField
	private String email;

	/**
	 * 电话
	 */
	@SensitiveField
	private String phone;

	/**
	 * 状态(1：正常 2：冻结 ）
	 */
	private Integer status;

	private Integer delFlag;
	/**
     * 同步工作流引擎1同步0不同步
     */
    private Integer activitiSync;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 *  身份（1 普通员工 2 上级）
	 */
	private Integer userIdentity;

	/**
	 * 管理部门ids
	 */
	private String departIds;

	/**
	 * 职务，关联职务表
	 */
	@SensitiveField
	private String post;

	/**
	 * 座机号
	 */
	@SensitiveField
	private String telephone;

	/**多租户id配置，编辑用户的时候设置*/
	private String relTenantIds;

	/**设备id uniapp推送用*/
	private String clientId;


	/**ticket过期时间*/
	private String ticketExpire;
	/**
	 * 当前登录部门code
	 */
	private String tenementguid;

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// 这里可以定制化权限列表
//		return Collections.EMPTY_LIST;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return false;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return false;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return false;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
}
