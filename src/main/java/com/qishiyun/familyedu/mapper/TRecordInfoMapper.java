package com.qishiyun.familyedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qishiyun.familyedu.entity.RecordInfoBase;
import com.qishiyun.familyedu.entity.TRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liutianyou
 * @since 2019-09-28
 */
public interface TRecordInfoMapper extends BaseMapper<TRecordInfo> {

    List<RecordInfoBase> getRecordInfoList(@Param("recordId") String recordId);
}