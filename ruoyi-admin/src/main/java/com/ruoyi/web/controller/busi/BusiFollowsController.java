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
import com.ruoyi.system.domain.BusiFollows;
import com.ruoyi.system.service.IBusiFollowsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 关注信息Controller
 *
 * @author dfm
 * @date 2021-04-26
 */
@Api("关注信息管理")
@RestController
@RequestMapping("/system/follows")
public class BusiFollowsController extends BaseController
{
    @Autowired
    private IBusiFollowsService busiFollowsService;

    /**
     * 查询关注信息列表
     */
    @ApiOperation("查询关注信息列表")
    @PreAuthorize("@ss.hasPermi('system:follows:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusiFollows busiFollows)
    {
        startPage();
        List<BusiFollows> list = busiFollowsService.selectBusiFollowsList(busiFollows);
        return getDataTable(list);
    }

    /**
     * 导出关注信息列表
     */
    @ApiOperation("导出关注信息列表")
    @PreAuthorize("@ss.hasPermi('system:follows:export')")
    @Log(title = "关注信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusiFollows busiFollows)
    {
        List<BusiFollows> list = busiFollowsService.selectBusiFollowsList(busiFollows);
        ExcelUtil<BusiFollows> util = new ExcelUtil<BusiFollows>(BusiFollows.class);
        return util.exportExcel(list, "follows");
    }

    /**
     * 获取关注信息详细信息
     */
    @ApiOperation("获取关注信息详细信息")
    @PreAuthorize("@ss.hasPermi('system:follows:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busiFollowsService.selectBusiFollowsById(id));
    }

    /**
     * 新增关注信息
     */
    @ApiOperation("新增关注信息")
    @PreAuthorize("@ss.hasPermi('system:follows:add')")
    @Log(title = "关注信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiFollows busiFollows)
    {
        return toAjax(busiFollowsService.insertBusiFollows(busiFollows));
    }

    /**
     * 修改关注信息
     */
    @ApiOperation("修改关注信息")
    @PreAuthorize("@ss.hasPermi('system:follows:edit')")
    @Log(title = "关注信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiFollows busiFollows)
    {
        return toAjax(busiFollowsService.updateBusiFollows(busiFollows));
    }

    /**
     * 删除关注信息
     */
    @ApiOperation("删除关注信息")
    @PreAuthorize("@ss.hasPermi('system:follows:remove')")
    @Log(title = "关注信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busiFollowsService.deleteBusiFollowsByIds(ids));
    }
}
