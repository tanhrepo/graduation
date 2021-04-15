package com.ruoyi.web.controller.busi;

import java.util.List;

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
import com.ruoyi.system.domain.BusiLable;
import com.ruoyi.system.service.IBusiLableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【标签】Controller
 *
 * @author dfm
 * @date 2021-04-15
 */
@Api("标签管理")
@RestController
@RequestMapping("/system/lable")
public class BusiLableController extends BaseController
{
    @Autowired
    private IBusiLableService busiLableService;

    /**
     * 查询【标签】列表
     */
    @ApiOperation("查询【标签】列表")
    @PreAuthorize("@ss.hasPermi('system:lable:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusiLable busiLable)
    {
        startPage();
        List<BusiLable> list = busiLableService.selectBusiLableList(busiLable);
        return getDataTable(list);
    }

    /**
     * 导出【标签】列表
     */
    @ApiOperation("导出【标签】列表")
    @PreAuthorize("@ss.hasPermi('system:lable:export')")
    @Log(title = "【标签】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusiLable busiLable)
    {
        List<BusiLable> list = busiLableService.selectBusiLableList(busiLable);
        ExcelUtil<BusiLable> util = new ExcelUtil<BusiLable>(BusiLable.class);
        return util.exportExcel(list, "lable");
    }

    /**
     * 获取【标签】详细信息
     */
    @ApiOperation("获取【标签】详细信息")
    @PreAuthorize("@ss.hasPermi('system:lable:query')")
    @GetMapping(value = "/{lableId}")
    public AjaxResult getInfo(@PathVariable("lableId") Long lableId)
    {
        return AjaxResult.success(busiLableService.selectBusiLableById(lableId));
    }

    /**
     * 新增【标签】
     */
    @ApiOperation("新增【标签】")
    @PreAuthorize("@ss.hasPermi('system:lable:add')")
    @Log(title = "【标签】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiLable busiLable)
    {
        return toAjax(busiLableService.insertBusiLable(busiLable));
    }

    /**
     * 修改【标签】
     */
    @ApiOperation("修改【标签】")
    @PreAuthorize("@ss.hasPermi('system:lable:edit')")
    @Log(title = "【标签】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiLable busiLable)
    {
        return toAjax(busiLableService.updateBusiLable(busiLable));
    }

    /**
     * 删除【标签】
     */
    @ApiOperation("删除【标签】")
    @PreAuthorize("@ss.hasPermi('system:lable:remove')")
    @Log(title = "【标签】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lableIds}")
    public AjaxResult remove(@PathVariable Long[] lableIds)
    {
        return toAjax(busiLableService.deleteBusiLableByIds(lableIds));
    }
}
