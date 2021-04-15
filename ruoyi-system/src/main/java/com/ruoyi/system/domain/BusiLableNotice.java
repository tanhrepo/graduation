package com.ruoyi.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 话题公告对象 busi_lable_notice
 *
 * @author dfm
 * @date 2021-04-15
 */
@ApiModel("话题公告")
public class BusiLableNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private Integer noticeId;

    /** 公告标题 */
    @ApiModelProperty("公告标题")
    @Excel(name = "公告标题")
    private String noticeTitle;

    /** 公告类型（1通知 2公告3话题公告） */
    @ApiModelProperty("公告类型（1通知 2公告3话题公告）")
    @Excel(name = "公告类型", readConverterExp = "1=通知,2=公告3话题公告")
    private String noticeType;

    /** 公告内容 */
    @ApiModelProperty("公告内容")
    @Excel(name = "公告内容")
    private String noticeContent;

    /** 公告状态（0正常 1关闭） */
    @ApiModelProperty("公告状态")
    @Excel(name = "公告状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 对应话题id */
    @ApiModelProperty("对应话题id")
    @Excel(name = "对应话题id")
    private Long lableId;

    public void setNoticeId(Integer noticeId)
    {
        this.noticeId = noticeId;
    }

    public Integer getNoticeId()
    {
        return noticeId;
    }
    public void setNoticeTitle(String noticeTitle)
    {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeTitle()
    {
        return noticeTitle;
    }
    public void setNoticeType(String noticeType)
    {
        this.noticeType = noticeType;
    }

    public String getNoticeType()
    {
        return noticeType;
    }
    public void setNoticeContent(String noticeContent)
    {
        this.noticeContent = noticeContent;
    }

    public String getNoticeContent()
    {
        return noticeContent;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setLableId(Long lableId)
    {
        this.lableId = lableId;
    }

    public Long getLableId()
    {
        return lableId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("noticeTitle", getNoticeTitle())
            .append("noticeType", getNoticeType())
            .append("noticeContent", getNoticeContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("lableId", getLableId())
            .toString();
    }
}
