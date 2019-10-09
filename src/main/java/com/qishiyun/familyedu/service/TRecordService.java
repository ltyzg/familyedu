package com.qishiyun.familyedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qishiyun.familyedu.entity.RecordBase;
import com.qishiyun.familyedu.entity.TRecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liutianyou
 * @since 2019-09-28
 */
public interface TRecordService extends IService<TRecord> {

    List<RecordBase> getPrimaryRecordList();

    List<RecordBase> getJuniorRecordList();
}
