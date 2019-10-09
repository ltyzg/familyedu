package com.qishiyun.familyedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qishiyun.familyedu.entity.RecordBase;
import com.qishiyun.familyedu.entity.TRecord;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liutianyou
 * @since 2019-09-28
 */
public interface TRecordMapper extends BaseMapper<TRecord> {

    List<RecordBase> getPrimaryRecordList();

    List<RecordBase> getJuniorRecordList();
}