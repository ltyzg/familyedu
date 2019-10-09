package com.qishiyun.familyedu.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishiyun.familyedu.entity.RecordBase;
import com.qishiyun.familyedu.entity.TRecord;
import com.qishiyun.familyedu.mapper.TRecordMapper;
import com.qishiyun.familyedu.service.TRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liutianyou
 * @since 2019-09-28
 */
@Service
public class TRecordServiceImpl extends ServiceImpl<TRecordMapper, TRecord> implements TRecordService {

    @Override
    public List<RecordBase> getPrimaryRecordList() {
       List<RecordBase> recordList = baseMapper.getPrimaryRecordList();
        return recordList;
    }

    @Override
    public List<RecordBase> getJuniorRecordList() {
        List<RecordBase> recordList = baseMapper.getJuniorRecordList();
        return recordList;
    }
}
