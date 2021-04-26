package com.ruoyi.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 关注信息对象 busi_follows
 *
 * @author dfm
 * @date 2021-04-26
 */
@ApiModel("关注信息对象")
public class BusiFollows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 被关注用户 */
    @Excel(name = "被关注用户")
    @ApiModelProperty("被关注用户")
    private Long userId;

    /** 关注用户 */
    @ApiModelProperty("关注用户")
    @Excel(name = "关注用户")
    private Long followId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setFollowId(Long followId)
    {
        this.followId = followId;
    }

    public Long getFollowId()
    {
        return followId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("followId", getFollowId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
