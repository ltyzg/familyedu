package com.qishiyun.familyedu.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishiyun.familyedu.entity.RecordInfoBase;
import com.qishiyun.familyedu.entity.TRecordInfo;
import com.qishiyun.familyedu.mapper.TRecordInfoMapper;
import com.qishiyun.familyedu.service.TRecordInfoService;
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
public class TRecordInfoServiceImpl extends ServiceImpl<TRecordInfoMapper, TRecordInfo> implements TRecordInfoService {

    @Override
    public List<RecordInfoBase> getRecordInfoList(String recordId) {
        List<RecordInfoBase> recordInfoList=  baseMapper.getRecordInfoList(recordId);
        return recordInfoList;
    }
}
