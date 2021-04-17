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
import com.ruoyi.system.domain.BusiOperation;
import com.ruoyi.system.service.IBusiOperationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户操作Controller
 *
 * @author dfm
 * @date 2021-04-17
 */
@Api("用户操作管理")
@RestController
@RequestMapping("/system/operation")
public class BusiOperationController extends BaseController
{
    @Autowired
    private IBusiOperationService busiOperationService;

    /**
     * 查询用户操作列表
     */
    @ApiOperation("查询用户操作列表")
    @PreAuthorize("@ss.hasPermi('system:operation:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusiOperation busiOperation)
    {
        startPage();
        List<BusiOperation> list = busiOperationService.selectBusiOperationList(busiOperation);
        return getDataTable(list);
    }

    /**
     * 导出用户操作列表
     */
    @ApiOperation("导出用户操作列表")
    @PreAuthorize("@ss.hasPermi('system:operation:export')")
    @Log(title = "用户操作", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusiOperation busiOperation)
    {
        List<BusiOperation> list = busiOperationService.selectBusiOperationList(busiOperation);
        ExcelUtil<BusiOperation> util = new ExcelUtil<BusiOperation>(BusiOperation.class);
        return util.exportExcel(list, "operation");
    }

    /**
     * 获取用户操作详细信息
     */
    @ApiOperation("获取用户操作详细信息")
    @PreAuthorize("@ss.hasPermi('system:operation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busiOperationService.selectBusiOperationById(id));
    }

    /**
     * 新增用户操作
     */
    @ApiOperation("新增用户操作")
    @PreAuthorize("@ss.hasPermi('system:operation:add')")
    @Log(title = "用户操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiOperation busiOperation)
    {
        return toAjax(busiOperationService.insertBusiOperation(busiOperation));
    }

    /**
     * 修改用户操作
     */
    @ApiOperation("修改用户操作")
    @PreAuthorize("@ss.hasPermi('system:operation:edit')")
    @Log(title = "用户操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiOperation busiOperation)
    {
        return toAjax(busiOperationService.updateBusiOperation(busiOperation));
    }

    /**
     * 删除用户操作
     */
    @ApiOperation("删除用户操作")
    @PreAuthorize("@ss.hasPermi('system:operation:remove')")
    @Log(title = "用户操作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busiOperationService.deleteBusiOperationByIds(ids));
    }
}
