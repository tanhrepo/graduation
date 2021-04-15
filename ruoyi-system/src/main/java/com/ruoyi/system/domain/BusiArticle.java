package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【文章】对象 busi_article
 *
 * @author dfm
 * @date 2021-04-01
 */
@ApiModel("文章对象实体")
public class BusiArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long articleId;

    /** 文章内容 */
    @ApiModelProperty("文章内容")
    @Excel(name = "文章内容")
    private String articleContent;

    /** 图片链接 */
    @ApiModelProperty("图片链接")
    @Excel(name = "图片链接")
    private String articleImgurls;

    /** 发布用户 */
    @ApiModelProperty("发布用户")
    @Excel(name = "发布用户")
    private String createUser;

    /** 文章标签 */
    @ApiModelProperty("文章标签")
    @Excel(name = "文章标签")
    private String articleLable;

    /** 文章标题 */
    @ApiModelProperty("文章标题")
    @Excel(name = "文章标题")
    private String articleTitle;

    /** 访问量 */
    @ApiModelProperty("访问量")
    @Excel(name = "访问量")
    private Long articleViewCount;

    /** 点赞数 */
    @ApiModelProperty("点赞数")
    @Excel(name = "点赞数")
    private Long articleCommentCount;

    /** 收藏数 */
    @ApiModelProperty("收藏数")
    @Excel(name = "收藏数")
    private Long articleCollectCount;

    /** 转发数 */
    @ApiModelProperty("转发数")
    @Excel(name = "转发数")
    private Long articleTransmitCount;

    /** 踩数 */
    @ApiModelProperty("踩数")
    @Excel(name = "踩数")
    private Long articleTrampleCount;

    /** 点赞量 */
    @ApiModelProperty("点赞量")
    @Excel(name = "点赞量")
    private Long praiseCount;

    @ApiModelProperty("图片url 数组作为参数")
    private String[] imgs;

    @ApiModelProperty("视频url数组 作为参数")
    private String[] vedios;

    /** 视频链接 */
    @ApiModelProperty("视频链接")
    @Excel(name = "视频链接")
    private String articleVediourls;

    /** 视频链接 */
    @ApiModelProperty("创建用户信息")
    @Excel(name = "创建用户信息")
    private SysUser user;

    public Long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public void setArticleId(Long articleId)
    {
        this.articleId = articleId;
    }

    public Long getArticleId()
    {
        return articleId;
    }
    public void setArticleContent(String articleContent)
    {
        this.articleContent = articleContent;
    }

    public String getArticleContent()
    {
        return articleContent;
    }
    public void setArticleImgurls(String articleImgurls)
    {
        this.articleImgurls = articleImgurls;
    }

    public String getArticleImgurls()
    {
        return articleImgurls;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getCreateUser()
    {
        return createUser;
    }
    public void setArticleLable(String articleLable)
    {
        this.articleLable = articleLable;
    }

    public String getArticleLable()
    {
        return articleLable;
    }
    public void setArticleTitle(String articleTitle)
    {
        this.articleTitle = articleTitle;
    }

    public String getArticleTitle()
    {
        return articleTitle;
    }
    public void setArticleViewCount(Long articleViewCount)
    {
        this.articleViewCount = articleViewCount;
    }

    public Long getArticleViewCount()
    {
        return articleViewCount;
    }
    public void setArticleCommentCount(Long articleCommentCount)
    {
        this.articleCommentCount = articleCommentCount;
    }

    public Long getArticleCommentCount()
    {
        return articleCommentCount;
    }
    public void setArticleCollectCount(Long articleCollectCount)
    {
        this.articleCollectCount = articleCollectCount;
    }

    public Long getArticleCollectCount()
    {
        return articleCollectCount;
    }
    public void setArticleTransmitCount(Long articleTransmitCount)
    {
        this.articleTransmitCount = articleTransmitCount;
    }

    public Long getArticleTransmitCount()
    {
        return articleTransmitCount;
    }
    public void setArticleTrampleCount(Long articleTrampleCount)
    {
        this.articleTrampleCount = articleTrampleCount;
    }

    public Long getArticleTrampleCount()
    {
        return articleTrampleCount;
    }
    public void setArticleVediourls(String articleVediourls)
    {
        this.articleVediourls = articleVediourls;
    }

    public String getArticleVediourls()
    {
        return articleVediourls;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public String[] getVedios() {
        return vedios;
    }

    public void setVedios(String[] vedios) {
        this.vedios = vedios;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("articleContent", getArticleContent())
            .append("articleImgurls", getArticleImgurls())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("articleLable", getArticleLable())
            .append("articleTitle", getArticleTitle())
            .append("articleViewCount", getArticleViewCount())
            .append("articleCommentCount", getArticleCommentCount())
            .append("articleCollectCount", getArticleCollectCount())
            .append("articleTransmitCount", getArticleTransmitCount())
            .append("articleTrampleCount", getArticleTrampleCount())
            .append("articleVediourls", getArticleVediourls())
            .toString();
    }
}
