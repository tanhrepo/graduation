package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户操作对象 busi_operation
 *
 * @author dfm
 * @date 2021-04-17
 */
@ApiModel("用户操作对象")
public class BusiOperation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 被操作对象id */
    @ApiModelProperty(" 被操作对象id ")
    @Excel(name = "被操作对象id")
    private Long entityId;

    /** 操作人id */
    @ApiModelProperty("操作人id")
    @Excel(name = "操作人id")
    private Long operationUser;

    /** 操作类型（5收藏4点赞3分享2评论1踩） */
    @ApiModelProperty(" 操作类型（5收藏4点赞3分享2评论1踩）")
    @Excel(name = "操作类型", readConverterExp = "5=收藏4点赞3分享2评论1踩")
    private Integer operationType;

    /** 操作时间 */
    @ApiModelProperty("操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /** 被操作对象类型（1文章2评论） */
    @ApiModelProperty("被操作对象类型（1文章2评论）")
    @Excel(name = "被操作对象类型", readConverterExp = "1=文章2评论")
    private Integer entityType;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setEntityId(Long entityId)
    {
        this.entityId = entityId;
    }

    public Long getEntityId()
    {
        return entityId;
    }
    public void setOperationUser(Long operationUser)
    {
        this.operationUser = operationUser;
    }

    public Long getOperationUser()
    {
        return operationUser;
    }
    public void setOperationType(Integer operationType)
    {
        this.operationType = operationType;
    }

    public Integer getOperationType()
    {
        return operationType;
    }
    public void setOperationTime(Date operationTime)
    {
        this.operationTime = operationTime;
    }

    public Date getOperationTime()
    {
        return operationTime;
    }
    public void setEntityType(Integer entityType)
    {
        this.entityType = entityType;
    }

    public Integer getEntityType()
    {
        return entityType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("entityId", getEntityId())
            .append("operationUser", getOperationUser())
            .append("operationType", getOperationType())
            .append("operationTime", getOperationTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("entityType", getEntityType())
            .toString();
    }
}
