package com.ruoyi.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【标签】对象 busi_lable
 *
 * @author dfm
 * @date 2021-04-15
 */
@ApiModel("标签对象实体")
public class BusiLable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long lableId;

    /** 标签名字 */
    @ApiModelProperty("标签名字")
    @Excel(name = "标签名字")
    private String lableName;

    /** 标签描述 */
    @Excel(name = "标签描述")
    @ApiModelProperty("标签描述")
    private String lableDescription;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    @Excel(name = "图片地址")
    private String lableImg;

    public void setLableId(Long lableId)
    {
        this.lableId = lableId;
    }

    public Long getLableId()
    {
        return lableId;
    }
    public void setLableName(String lableName)
    {
        this.lableName = lableName;
    }

    public String getLableName()
    {
        return lableName;
    }
    public void setLableDescription(String lableDescription)
    {
        this.lableDescription = lableDescription;
    }

    public String getLableDescription()
    {
        return lableDescription;
    }
    public void setLableImg(String lableImg)
    {
        this.lableImg = lableImg;
    }

    public String getLableImg()
    {
        return lableImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("lableId", getLableId())
            .append("lableName", getLableName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("lableDescription", getLableDescription())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("lableImg", getLableImg())
            .toString();
    }
}
