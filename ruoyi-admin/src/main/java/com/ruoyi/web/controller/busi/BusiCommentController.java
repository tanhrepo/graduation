package com.ruoyi.web.controller.busi;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BusiOperation;
import com.ruoyi.system.domain.BusiScore;
import com.ruoyi.system.service.IBusiOperationService;
import com.ruoyi.system.service.IBusiScoreService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.tool.StrUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BusiComment;
import com.ruoyi.system.service.IBusiCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【评论】Controller
 *
 * @author dfm
 * @date 2021-04-13
 */
@Api("评论模块")
@RestController
@RequestMapping("/system/comment")
public class BusiCommentController extends BaseController
{
    @Autowired
    private IBusiCommentService busiCommentService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IBusiOperationService operationService;

    @Autowired
    private IBusiScoreService scoreService;

    /**
     * 查询【评论】列表
     */
    @ApiOperation("查询【评论】列表")
    @PreAuthorize("@ss.hasPermi('system:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody  BusiComment busiComment)
    {
        startPage();
        List<BusiComment> list = busiCommentService.selectBusiCommentList(busiComment);
        for (BusiComment comment:list
             ) {
            //createBy 一定不为null
            SysUser user = userService.selectUserByUserName(comment.getCreateBy());
            comment.setCreateUser(user);

            //填充 ansUser
            if(comment.getAnswerUser()!=null){
                SysUser ans = userService.selectUserById(comment.getAnswerUser());
                comment.setAnsUser(ans);
            }

            //如果有 图片
            if(StringUtils.isNotEmpty(comment.getImgurls())){
                String[] strings = StrUtils.stringToStringArray(comment.getImgurls());
                comment.setImgs(strings);
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出【评论】列表
     */
    @ApiOperation("导出【评论】列表")
    @PreAuthorize("@ss.hasPermi('system:comment:export')")
    @Log(title = "【评论】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusiComment busiComment)
    {
        List<BusiComment> list = busiCommentService.selectBusiCommentList(busiComment);
        ExcelUtil<BusiComment> util = new ExcelUtil<BusiComment>(BusiComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取【评论】详细信息
     */
    @ApiOperation("获取【评论】详细信息")
    @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        BusiComment comment = busiCommentService.selectBusiCommentById(id);
        //createBy 一定不为null
        SysUser user = userService.selectUserByUserName(comment.getCreateBy());
        comment.setCreateUser(user);
        //填充 ansUser
        if(comment.getAnswerUser()!=null){
            SysUser ans = userService.selectUserById(comment.getAnswerUser());
            comment.setAnsUser(ans);
        }
        //如果有 图片
        if(StringUtils.isNotEmpty(comment.getImgurls())){
            String[] strings = StrUtils.stringToStringArray(comment.getImgurls());
            comment.setImgs(strings);
        }
        return AjaxResult.success();
    }

    /**
     * 新增【评论】
     */
    @ApiOperation("新增【评论】")
    @PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "【评论】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiComment busiComment)
    {
        if(busiComment !=null){
            //得到 user 创建用户
            SysUser user = userService.selectUserByUserName(busiComment.getCreateBy());
            //记录操作
            BusiOperation operation = new BusiOperation();
            operation.setEntityId(busiComment.getId());
            operation.setEntityType(2);//1 文章 2 评论
            operation.setOperationTime(busiComment.getCreateTime());
            operation.setOperationType(2);
            operation.setOperationUser(user.getUserId());
            operation.setCreateBy(user.getUserName());
            operationService.insertBusiOperation(operation);

            //记录评论得分
            BusiScore busiScore = new BusiScore();
            busiScore.setUid(user.getUserId());
            busiScore.setAid(busiComment.getArticleId());
            //检测是否 有其他行为打分
            List<BusiScore> busiScores = scoreService.selectBusiScoreList(busiScore);//若成功 只会返回一个对象
            busiScore.setTs(busiComment.getCreateTime());//不能让 ts参与 查询
            if(busiScore != null && busiScores.size() == 1){//有一条记录 则 修改
                 busiScore.setScore(busiScores.get(0).getScore()+2);// 原得分基础 +2
            }else {// 没有打分记录 则 新增
                busiScore.setScore(2);
                scoreService.insertBusiScore(busiScore);
            }
        }
        //如果有图片
        if(StringUtils.isNotEmpty(busiComment.getImgs())){
            String str = StrUtils.stringArrayToString(busiComment.getImgs());
            busiComment.setImgurls(str);
        }
        return toAjax(busiCommentService.insertBusiComment(busiComment));
    }

    /**
     * 修改【评论】
     */
    @ApiOperation(" 修改【评论】")
    @PreAuthorize("@ss.hasPermi('system:comment:edit')")
    @Log(title = "【评论】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiComment busiComment)
    {
        //如果有图片
        if(StringUtils.isNotEmpty(busiComment.getImgs())){
            String str = StrUtils.stringArrayToString(busiComment.getImgs());
            busiComment.setImgurls(str);
        }
        return toAjax(busiCommentService.updateBusiComment(busiComment));
    }

    /**
     * 删除【评论】
     */
    @ApiOperation(" 删除【评论】")
    @PreAuthorize("@ss.hasPermi('system:comment:remove')")
    @Log(title = "【评论】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busiCommentService.deleteBusiCommentByIds(ids));
    }
}
