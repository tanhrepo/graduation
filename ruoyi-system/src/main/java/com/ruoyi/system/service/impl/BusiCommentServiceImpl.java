package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusiCommentMapper;
import com.ruoyi.system.domain.BusiComment;
import com.ruoyi.system.service.IBusiCommentService;

/**
 * 【评论】Service业务层处理
 *
 * @author dfm
 * @date 2021-04-13
 */
@Service
public class BusiCommentServiceImpl implements IBusiCommentService
{
    @Autowired
    private BusiCommentMapper busiCommentMapper;

    /**
     * 查询【评论】
     *
     * @param id 【评论】ID
     * @return 【评论】
     */
    @Override
    public BusiComment selectBusiCommentById(Long id)
    {
        return busiCommentMapper.selectBusiCommentById(id);
    }

    /**
     * 查询【评论】列表
     *
     * @param busiComment 【评论】
     * @return 【评论】
     */
    @Override
    public List<BusiComment> selectBusiCommentList(BusiComment busiComment)
    {
        return busiCommentMapper.selectBusiCommentList(busiComment);
    }

    /**
     * 新增【评论】
     *
     * @param busiComment 【评论】
     * @return 结果
     */
    @Override
    public int insertBusiComment(BusiComment busiComment)
    {
        busiComment.setCreateTime(DateUtils.getNowDate());
        return busiCommentMapper.insertBusiComment(busiComment);
    }

    /**
     * 修改【评论】
     *
     * @param busiComment 【评论】
     * @return 结果
     */
    @Override
    public int updateBusiComment(BusiComment busiComment)
    {
        busiComment.setUpdateTime(DateUtils.getNowDate());
        return busiCommentMapper.updateBusiComment(busiComment);
    }

    /**
     * 批量删除【评论】
     *
     * @param ids 需要删除的【评论】ID
     * @return 结果
     */
    @Override
    public int deleteBusiCommentByIds(Long[] ids)
    {
        return busiCommentMapper.deleteBusiCommentByIds(ids);
    }

    /**
     * 删除【评论】信息
     *
     * @param id 【评论】ID
     * @return 结果
     */
    @Override
    public int deleteBusiCommentById(Long id)
    {
        return busiCommentMapper.deleteBusiCommentById(id);
    }
}
