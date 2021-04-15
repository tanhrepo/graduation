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
import com.ruoyi.system.domain.BusiLableNotice;
import com.ruoyi.system.service.IBusiLableNoticeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 话题公告Controller
 *
 * @author dfm
 * @date 2021-04-15
 */
@Api("话题公告管理")
@RestController
@RequestMapping("/system/lableNotice")
public class BusiLableNoticeController extends BaseController
{
    @Autowired
    private IBusiLableNoticeService busiLableNoticeService;

    /**
     * 查询话题公告列表
     */
    @ApiOperation("查询话题公告列表")
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusiLableNotice busiLableNotice)
    {
        startPage();
        List<BusiLableNotice> list = busiLableNoticeService.selectBusiLableNoticeList(busiLableNotice);
        return getDataTable(list);
    }

    /**
     * 导出话题公告列表
     */
    @ApiOperation("导出话题公告列表")
    @PreAuthorize("@ss.hasPermi('system:notice:export')")
    @Log(title = "话题公告", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusiLableNotice busiLableNotice)
    {
        List<BusiLableNotice> list = busiLableNoticeService.selectBusiLableNoticeList(busiLableNotice);
        ExcelUtil<BusiLableNotice> util = new ExcelUtil<BusiLableNotice>(BusiLableNotice.class);
        return util.exportExcel(list, "notice");
    }

    /**
     * 获取话题公告详细信息
     */
    @ApiOperation("获取话题公告详细信息")
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Integer noticeId)
    {
        return AjaxResult.success(busiLableNoticeService.selectBusiLableNoticeById(noticeId));
    }

    /**
     * 新增话题公告
     */
    @ApiOperation("新增话题公告")
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "话题公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiLableNotice busiLableNotice)
    {
        return toAjax(busiLableNoticeService.insertBusiLableNotice(busiLableNotice));
    }

    /**
     * 修改话题公告
     */
    @ApiOperation("修改话题公告")
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "话题公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiLableNotice busiLableNotice)
    {
        return toAjax(busiLableNoticeService.updateBusiLableNotice(busiLableNotice));
    }

    /**
     * 删除话题公告
     */
    @ApiOperation("删除话题公告")
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "话题公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Integer[] noticeIds)
    {
        return toAjax(busiLableNoticeService.deleteBusiLableNoticeByIds(noticeIds));
    }
}
