package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BusiComment;

/**
 * 【评论】Mapper接口
 *
 * @author dfm
 * @date 2021-04-13
 */
public interface BusiCommentMapper
{
    /**
     * 查询【评论】
     *
     * @param id 【评论】ID
     * @return 【评论】
     */
    public BusiComment selectBusiCommentById(Long id);

    /**
     * 查询【评论】列表
     *
     * @param busiComment 【评论】
     * @return 【评论】集合
     */
    public List<BusiComment> selectBusiCommentList(BusiComment busiComment);

    /**
     * 新增【评论】
     *
     * @param busiComment 【评论】
     * @return 结果
     */
    public int insertBusiComment(BusiComment busiComment);

    /**
     * 修改【评论】
     *
     * @param busiComment 【评论】
     * @return 结果
     */
    public int updateBusiComment(BusiComment busiComment);

    /**
     * 删除【评论】
     *
     * @param id 【评论】ID
     * @return 结果
     */
    public int deleteBusiCommentById(Long id);

    /**
     * 批量删除【评论】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusiCommentByIds(Long[] ids);
}
