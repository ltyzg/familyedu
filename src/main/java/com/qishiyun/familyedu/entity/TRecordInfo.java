package com.qishiyun.familyedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liutianyou
 * @since 2019-09-28
 */
@TableName("t_record_info")
public class TRecordInfo implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="infoId", type= IdType.AUTO)
	private Integer infoId;
	private String recordId;
	private Integer questionId;
	private String questionUuid;
	private Integer version;
	private String answer;
	private Integer useTime;
	private Date createTime;
	private Integer status;


	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionUuid() {
		return questionUuid;
	}

	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getUseTime() {
		return useTime;
	}

	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TRecordInfo{" +
			"infoId=" + infoId +
			", recordId=" + recordId +
			", questionId=" + questionId +
			", questionUuid=" + questionUuid +
			", version=" + version +
			", answer=" + answer +
			", useTime=" + useTime +
			", createTime=" + createTime +
			", status=" + status +
			"}";
	}
}
