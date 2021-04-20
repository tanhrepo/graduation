package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Arrays;

/**
 * 【评论】对象 busi_comment
 *
 * @author dfm
 * @date 2021-04-13
 */
@ApiModel("评论对象实体")
public class BusiComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 上级评论 */
    @ApiModelProperty("上级评论")
    @Excel(name = "上级评论")
    private Long parentId;

    /** 评论文章 */
    @ApiModelProperty("评论文章")
    @Excel(name = "评论文章")
    private Long articleId;

    /** 评论内容 */
    @ApiModelProperty("评论内容")
    @Excel(name = "评论内容")
    private String content;

    /** 图片链接 */
    @ApiModelProperty("图片链接")
    @Excel(name = "图片链接")
    private String imgurls;

    /**  点赞量 */
    @ApiModelProperty("点赞量")
    @Excel(name = " 点赞量")
    private Long praiseCount;

    /** 踩一踩 量 */
    @ApiModelProperty("踩一踩 量")
    @Excel(name = "踩一踩 量")
    private Long trampleCount;

    /** 回复用户（二级评论） */
    @ApiModelProperty("回复用户（二级评论）")
    @Excel(name = "回复用户", readConverterExp = "二=级评论")
    private Long answerUser;

    @ApiModelProperty("创建用户具体信息")
    private SysUser createUser;

    @ApiModelProperty("回复用户具体信息")
    private SysUser ansUser;

    @ApiModelProperty("图片链接数组 传参")
    private String[] imgs;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setArticleId(Long articleId)
    {
        this.articleId = articleId;
    }

    public Long getArticleId()
    {
        return articleId;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setImgurls(String imgurls)
    {
        this.imgurls = imgurls;
    }

    public String getImgurls()
    {
        return imgurls;
    }
    public void setPraiseCount(Long praiseCount)
    {
        this.praiseCount = praiseCount;
    }

    public Long getPraiseCount()
    {
        return praiseCount;
    }
    public void setTrampleCount(Long trampleCount)
    {
        this.trampleCount = trampleCount;
    }

    public Long getTrampleCount()
    {
        return trampleCount;
    }
    public void setAnswerUser(Long answerUser)
    {
        this.answerUser = answerUser;
    }

    public Long getAnswerUser()
    {
        return answerUser;
    }

    public SysUser getCreateUser() {
        return createUser;
    }

    public void setCreateUser(SysUser createUser) {
        this.createUser = createUser;
    }

    public SysUser getAnsUser() {
        return ansUser;
    }

    public void setAnsUser(SysUser ansUser) {
        this.ansUser = ansUser;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    @Override
    public String toString() {
        return "BusiComment{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", imgurls='" + imgurls + '\'' +
                ", praiseCount=" + praiseCount +
                ", trampleCount=" + trampleCount +
                ", answerUser=" + answerUser +
                ", createUser=" + createUser +
                ", ansUser=" + ansUser +
                ", imgs=" + Arrays.toString(imgs) +
                '}';
    }
}
