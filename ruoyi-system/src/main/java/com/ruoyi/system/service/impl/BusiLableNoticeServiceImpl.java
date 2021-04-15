package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusiLableNoticeMapper;
import com.ruoyi.system.domain.BusiLableNotice;
import com.ruoyi.system.service.IBusiLableNoticeService;

/**
 * 通知公告Service业务层处理
 * 
 * @author dfm
 * @date 2021-04-15
 */
@Service
public class BusiLableNoticeServiceImpl implements IBusiLableNoticeService 
{
    @Autowired
    private BusiLableNoticeMapper busiLableNoticeMapper;

    /**
     * 查询通知公告
     * 
     * @param noticeId 通知公告ID
     * @return 通知公告
     */
    @Override
    public BusiLableNotice selectBusiLableNoticeById(Integer noticeId)
    {
        return busiLableNoticeMapper.selectBusiLableNoticeById(noticeId);
    }

    /**
     * 查询通知公告列表
     * 
     * @param busiLableNotice 通知公告
     * @return 通知公告
     */
    @Override
    public List<BusiLableNotice> selectBusiLableNoticeList(BusiLableNotice busiLableNotice)
    {
        return busiLableNoticeMapper.selectBusiLableNoticeList(busiLableNotice);
    }

    /**
     * 新增通知公告
     * 
     * @param busiLableNotice 通知公告
     * @return 结果
     */
    @Override
    public int insertBusiLableNotice(BusiLableNotice busiLableNotice)
    {
        busiLableNotice.setCreateTime(DateUtils.getNowDate());
        return busiLableNoticeMapper.insertBusiLableNotice(busiLableNotice);
    }

    /**
     * 修改通知公告
     * 
     * @param busiLableNotice 通知公告
     * @return 结果
     */
    @Override
    public int updateBusiLableNotice(BusiLableNotice busiLableNotice)
    {
        busiLableNotice.setUpdateTime(DateUtils.getNowDate());
        return busiLableNoticeMapper.updateBusiLableNotice(busiLableNotice);
    }

    /**
     * 批量删除通知公告
     * 
     * @param noticeIds 需要删除的通知公告ID
     * @return 结果
     */
    @Override
    public int deleteBusiLableNoticeByIds(Integer[] noticeIds)
    {
        return busiLableNoticeMapper.deleteBusiLableNoticeByIds(noticeIds);
    }

    /**
     * 删除通知公告信息
     * 
     * @param noticeId 通知公告ID
     * @return 结果
     */
    @Override
    public int deleteBusiLableNoticeById(Integer noticeId)
    {
        return busiLableNoticeMapper.deleteBusiLableNoticeById(noticeId);
    }
}
