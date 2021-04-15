package com.ruoyi.web.controller.busi;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BusiArticle;
import com.ruoyi.system.domain.BusiComment;
import com.ruoyi.system.service.IBusiArticleService;
import com.ruoyi.system.service.IBusiCommentService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【文章管理】Controller
 *
 * @author dfm
 * @date 2021-04-01
 */
@Api("文章管理")
@RestController
@RequestMapping("/system/article")
public class BusiArticleController extends BaseController
{
    @Autowired
    private IBusiArticleService busiArticleService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IBusiCommentService commentService;

    /**
     * 查询【文章】列表
     */
    @ApiOperation(" 查询【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusiArticle busiArticle)
    {
        startPage();
        List<BusiArticle> list = busiArticleService.selectBusiArticleList(busiArticle);
        for (BusiArticle article:list
        ) {
            //检验是否 empty
            if(StringUtils.isNotEmpty(article.getArticleImgurls())){
                String articleImgurls = article.getArticleImgurls();
                article.setImgs(StrUtils.stringToStringArray(articleImgurls));
            }
            //检验是否 empty
            if(StringUtils.isNotEmpty(article.getArticleVediourls())){
                String vediourls = article.getArticleVediourls();
                article.setVedios(StrUtils.stringToStringArray(vediourls));
            }
            //创建用户不能为空
            SysUser user = userService.selectUserByUserName(article.getCreateUser());
            article.setUser(user);
        }
        return getDataTable(list);
    }

    /**
     * 查询图文区【文章】列表
     */
    @ApiOperation(" 查询图文区【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/photoList")
    public TableDataInfo photoList()
    {
        startPage();
        List<BusiArticle> list = busiArticleService.selectPhotoBusiArticleList();
        for (BusiArticle busiArticle:list
        ) {
            //检验是否 empty
            if(StringUtils.isNotEmpty(busiArticle.getArticleImgurls())){
                String articleImgurls = busiArticle.getArticleImgurls();
                busiArticle.setImgs(StrUtils.stringToStringArray(articleImgurls));
            }
            //检验是否 empty
            if(StringUtils.isNotEmpty(busiArticle.getArticleVediourls())){
                String vediourls = busiArticle.getArticleVediourls();
                busiArticle.setVedios(StrUtils.stringToStringArray(vediourls));
            }
            //创建用户不能为空
            SysUser user = userService.selectUserByUserName(busiArticle.getCreateUser());
            busiArticle.setUser(user);
            //点赞量

            //评论量
//            BusiComment comment = new BusiComment();
//            comment.setArticleId(busiArticle.getArticleId();
//            List<BusiComment> busiComments = commentService.selectBusiCommentList(comment);
//            busiArticle.setArticleCommentCount(Long.valueOf(busiComments.size()) );
        }
        return getDataTable(list);
    }

    /**
     * 查询视频区【文章】列表
     */
    @ApiOperation(" 查询视频区【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/vedioList")
    public TableDataInfo vedioList()
    {
        startPage();
        List<BusiArticle> list = busiArticleService.selectVedioBusiArticleList();
        for (BusiArticle busiArticle:list
             ) {
            //检验是否 empty
            if(StringUtils.isNotEmpty(busiArticle.getArticleImgurls())){
                String articleImgurls = busiArticle.getArticleImgurls();
                busiArticle.setImgs(StrUtils.stringToStringArray(articleImgurls));
            }
            //检验是否 empty
            if(StringUtils.isNotEmpty(busiArticle.getArticleVediourls())){
                String vediourls = busiArticle.getArticleVediourls();
                busiArticle.setVedios(StrUtils.stringToStringArray(vediourls));
            }
            //创建用户不能为空
            SysUser user = userService.selectUserByUserName(busiArticle.getCreateUser());
            busiArticle.setUser(user);
        }
        return getDataTable(list);
    }

    /**
     * 导出【文章】列表
     */
    @ApiOperation(" 导出【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:export')")
    @Log(title = "【导出【文章】列表】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusiArticle busiArticle)
    {
        List<BusiArticle> list = busiArticleService.selectBusiArticleList(busiArticle);
        ExcelUtil<BusiArticle> util = new ExcelUtil<BusiArticle>(BusiArticle.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 获取【文章】详细信息
     */
    @ApiOperation("获取【文章】详细信息")
    @PreAuthorize("@ss.hasPermi('system:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId)
    {
        BusiArticle busiArticle = busiArticleService.selectBusiArticleById(articleId);
        //检验是否 empty
        if(StringUtils.isNotEmpty(busiArticle.getArticleImgurls())){
            String articleImgurls = busiArticle.getArticleImgurls();
            busiArticle.setImgs(StrUtils.stringToStringArray(articleImgurls));
        }
        //检验是否 empty
        if(StringUtils.isNotEmpty(busiArticle.getArticleVediourls())){
            String vediourls = busiArticle.getArticleVediourls();
            busiArticle.setVedios(StrUtils.stringToStringArray(vediourls));
        }
        return AjaxResult.success(busiArticle);
    }

    /**
     * 新增【文章】
     */
    @ApiOperation("新增【文章】")
    @PreAuthorize("@ss.hasPermi('system:article:add')")
    @Log(title = "【新增【文章】】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiArticle busiArticle)
    {
        //参数imgs 校验
        if(StringUtils.isNotEmpty(busiArticle.getImgs())){
            busiArticle.setArticleImgurls(StrUtils.stringArrayToString(busiArticle.getImgs()));
        }
        //参数vedios 校验
        if(StringUtils.isNotEmpty(busiArticle.getVedios())){
            busiArticle.setArticleVediourls(StrUtils.stringArrayToString(busiArticle.getVedios()));
        }
        return toAjax(busiArticleService.insertBusiArticle(busiArticle));
    }

    /**
     * 修改【文章】
     */
    @ApiOperation("修改文章")
    @PreAuthorize("@ss.hasPermi('system:article:edit')")
    @Log(title = "【 修改【文章】】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiArticle busiArticle)
    {
        //参数 校验
        if(StringUtils.isNotEmpty(busiArticle.getImgs())){
            busiArticle.setArticleImgurls(StrUtils.stringArrayToString(busiArticle.getImgs()));
        }
        //参数 校验
        if(StringUtils.isNotEmpty(busiArticle.getVedios())){
            busiArticle.setArticleVediourls(StrUtils.stringArrayToString(busiArticle.getVedios()));
        }
        return toAjax(busiArticleService.updateBusiArticle(busiArticle));
    }

    /**
     * 删除【文章】
     */
    @ApiOperation("删除文章")
    @PreAuthorize("@ss.hasPermi('system:article:remove')")
    @Log(title = "【删除【文章】】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        return toAjax(busiArticleService.deleteBusiArticleByIds(articleIds));
    }
}
