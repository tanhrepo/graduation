package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BusiLableNotice;

/**
 * 通知公告Service接口
 * 
 * @author dfm
 * @date 2021-04-15
 */
public interface IBusiLableNoticeService 
{
    /**
     * 查询通知公告
     * 
     * @param noticeId 通知公告ID
     * @return 通知公告
     */
    public BusiLableNotice selectBusiLableNoticeById(Integer noticeId);

    /**
     * 查询通知公告列表
     * 
     * @param busiLableNotice 通知公告
     * @return 通知公告集合
     */
    public List<BusiLableNotice> selectBusiLableNoticeList(BusiLableNotice busiLableNotice);

    /**
     * 新增通知公告
     * 
     * @param busiLableNotice 通知公告
     * @return 结果
     */
    public int insertBusiLableNotice(BusiLableNotice busiLableNotice);

    /**
     * 修改通知公告
     * 
     * @param busiLableNotice 通知公告
     * @return 结果
     */
    public int updateBusiLableNotice(BusiLableNotice busiLableNotice);

    /**
     * 批量删除通知公告
     * 
     * @param noticeIds 需要删除的通知公告ID
     * @return 结果
     */
    public int deleteBusiLableNoticeByIds(Integer[] noticeIds);

    /**
     * 删除通知公告信息
     * 
     * @param noticeId 通知公告ID
     * @return 结果
     */
    public int deleteBusiLableNoticeById(Integer noticeId);
}
