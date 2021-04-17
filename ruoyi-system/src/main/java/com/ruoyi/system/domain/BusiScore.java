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
 * 推荐得分对象 busi_score
 *
 * @author dfm
 * @date 2021-04-17
 */
@ApiModel("推荐得分对象")
public class BusiScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @ApiModelProperty("用户id")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long uid;

    /** $column.columnComment */
    @ApiModelProperty("item id")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long aid;

    /** $column.columnComment */
    @ApiModelProperty("偏好度 得分")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer score;

    /** $column.columnComment */
    @ApiModelProperty("时间")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date ts;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUid(Long uid)
    {
        this.uid = uid;
    }

    public Long getUid()
    {
        return uid;
    }
    public void setAid(Long aid)
    {
        this.aid = aid;
    }

    public Long getAid()
    {
        return aid;
    }
    public void setScore(Integer score)
    {
        this.score = score;
    }

    public Integer getScore()
    {
        return score;
    }
    public void setTs(Date ts)
    {
        this.ts = ts;
    }

    public Date getTs()
    {
        return ts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("aid", getAid())
            .append("score", getScore())
            .append("ts", getTs())
            .toString();
    }
}
