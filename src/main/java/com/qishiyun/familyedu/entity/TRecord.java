package com.qishiyun.familyedu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liutianyou
 * @since 2019-09-28
 */
@TableName("t_record")
public class TRecord implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="recordId", type= IdType.AUTO)
	private String recordId;
	private Integer examId;
	private String examUuid;
	private Integer userId;
	private String userUuid;
	private Date createTime;
	private Date finishTime;
	private Integer usedTime;
	private String scoreInfo;
	private String chainInfo;
	private String userInfo;
	private Integer errorCount;
	private Integer status;
	private String orgUuid;
	private String uUuid;
	private String orgName;
	private String uName;
	private String reportUrl;
	private String callbackInfo;
	private Integer lastModule;


	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamUuid() {
		return examUuid;
	}

	public void setExamUuid(String examUuid) {
		this.examUuid = examUuid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Integer usedTime) {
		this.usedTime = usedTime;
	}

	public String getScoreInfo() {
		return scoreInfo;
	}

	public void setScoreInfo(String scoreInfo) {
		this.scoreInfo = scoreInfo;
	}

	public String getChainInfo() {
		return chainInfo;
	}

	public void setChainInfo(String chainInfo) {
		this.chainInfo = chainInfo;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrgUuid() {
		return orgUuid;
	}

	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	public String getuUuid() {
		return uUuid;
	}

	public void setuUuid(String uUuid) {
		this.uUuid = uUuid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String getCallbackInfo() {
		return callbackInfo;
	}

	public void setCallbackInfo(String callbackInfo) {
		this.callbackInfo = callbackInfo;
	}

	public Integer getLastModule() {
		return lastModule;
	}

	public void setLastModule(Integer lastModule) {
		this.lastModule = lastModule;
	}

	@Override
	public String toString() {
		return "TRecord{" +
			"recordId=" + recordId +
			", examId=" + examId +
			", examUuid=" + examUuid +
			", userId=" + userId +
			", userUuid=" + userUuid +
			", createTime=" + createTime +
			", finishTime=" + finishTime +
			", usedTime=" + usedTime +
			", scoreInfo=" + scoreInfo +
			", chainInfo=" + chainInfo +
			", userInfo=" + userInfo +
			", errorCount=" + errorCount +
			", status=" + status +
			", orgUuid=" + orgUuid +
			", uUuid=" + uUuid +
			", orgName=" + orgName +
			", uName=" + uName +
			", reportUrl=" + reportUrl +
			", callbackInfo=" + callbackInfo +
			", lastModule=" + lastModule +
			"}";
	}
}
