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
import com.ruoyi.system.domain.BusiScore;
import com.ruoyi.system.service.IBusiScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 推荐得分Controller
 *
 * @author dfm
 * @date 2021-04-17
 */
@Api("推荐得分对象")
@RestController
@RequestMapping("/system/score")
public class BusiScoreController extends BaseController
{
    @Autowired
    private IBusiScoreService busiScoreService;

    /**
     * 查询推荐得分列表
     */
    @ApiOperation("查询推荐得分列表")
    @PreAuthorize("@ss.hasPermi('system:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusiScore busiScore)
    {
        startPage();
        List<BusiScore> list = busiScoreService.selectBusiScoreList(busiScore);
        return getDataTable(list);
    }

    /**
     * 导出推荐得分列表
     */
    @ApiOperation("导出推荐得分列表")
    @PreAuthorize("@ss.hasPermi('system:score:export')")
    @Log(title = "推荐得分", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusiScore busiScore)
    {
        List<BusiScore> list = busiScoreService.selectBusiScoreList(busiScore);
        ExcelUtil<BusiScore> util = new ExcelUtil<BusiScore>(BusiScore.class);
        return util.exportExcel(list, "score");
    }

    /**
     * 获取推荐得分详细信息
     */
    @ApiOperation("获取推荐得分详细信息")
    @PreAuthorize("@ss.hasPermi('system:score:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busiScoreService.selectBusiScoreById(id));
    }

    /**
     * 新增推荐得分
     */
    @ApiOperation("新增推荐得分")
    @PreAuthorize("@ss.hasPermi('system:score:add')")
    @Log(title = "推荐得分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiScore busiScore)
    {
        return toAjax(busiScoreService.insertBusiScore(busiScore));
    }

    /**
     * 修改推荐得分
     */
    @ApiOperation("修改推荐得分")
    @PreAuthorize("@ss.hasPermi('system:score:edit')")
    @Log(title = "推荐得分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiScore busiScore)
    {
        return toAjax(busiScoreService.updateBusiScore(busiScore));
    }

    /**
     * 删除推荐得分
     */
    @ApiOperation("删除推荐得分")
    @PreAuthorize("@ss.hasPermi('system:score:remove')")
    @Log(title = "推荐得分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busiScoreService.deleteBusiScoreByIds(ids));
    }
}
