package com.ruoyi.web.controller.busi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BusiArticle;
import com.ruoyi.system.domain.BusiFollows;
import com.ruoyi.system.domain.BusiOperation;
import com.ruoyi.system.domain.BusiScore;
import com.ruoyi.system.service.*;
import com.ruoyi.web.controller.tool.RecommendUtils;
import com.ruoyi.common.constant.RedisConstans;
import com.ruoyi.web.controller.tool.StrUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【文章管理】Controller
 *
 * @author dfm
 * @date 2021-04-01
 */
@Api("文章管理")
@RestController
@RequestMapping("/system/article")
public class BusiArticleController extends BaseController
{
    @Autowired
    private IBusiArticleService busiArticleService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IBusiCommentService commentService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IBusiScoreService scoreService;

    @Autowired
    private IBusiOperationService operationService;

    @Autowired
    private IBusiFollowsService followsService;


    @ApiOperation("收藏")
    @GetMapping("/collect")
    public AjaxResult collect( Long articleId, Long userId){
        BusiOperation busiOperation = new BusiOperation();
        busiOperation.setStatus("1");
        busiOperation.setCreateTime(DateUtils.getNowDate());
        busiOperation.setOperationTime(DateUtils.getNowDate());
        busiOperation.setEntityType(5);
        busiOperation.setEntityType(1);
        busiOperation.setEntityId(articleId);
        busiOperation.setOperationUser(userId);
        operationService.insertBusiOperation(busiOperation);
        //同步打分
        BusiScore busiScore = new BusiScore();
        busiScore.setUid(userId);
        busiScore.setAid(articleId);
        //检测是否 有其他行为打分
        List<BusiScore> busiScores = scoreService.selectBusiScoreList(busiScore);//若成功 只会返回一个对象
        busiScore.setTs(DateUtils.getNowDate());//不能让 ts参与 查询
        if(busiScore != null && busiScores.size() == 1){//有一条记录 则 修改
            busiScore.setScore(busiScores.get(0).getScore()+5);// 原得分基础 +5
            scoreService.updateBusiScore(busiScore);
        }else {// 没有打分记录 则 新增
            busiScore.setScore(5);
            scoreService.insertBusiScore(busiScore);
        }
        return AjaxResult.success();
    }


    @ApiOperation("分享文章")
    @GetMapping("/share")
    public AjaxResult share(Long articleId,Long userId){
        //分享量 自增  若 没有 hk 会不会 报错？
        stringRedisTemplate.opsForHash().increment(RedisConstans.SHARE_COUNT,String.valueOf(articleId),1L);
        //同步打分
        BusiScore busiScore = new BusiScore();
        busiScore.setUid(userId);
        busiScore.setAid(articleId);
        //检测是否 有其他行为打分
        List<BusiScore> busiScores = scoreService.selectBusiScoreList(busiScore);//若成功 只会返回一个对象
        busiScore.setTs(DateUtils.getNowDate());//不能让 ts参与 查询
        if(busiScore != null && busiScores.size() == 1){//有一条记录 则 修改
            busiScore.setScore(busiScores.get(0).getScore()+3);// 原得分基础 +3
            scoreService.updateBusiScore(busiScore);
        }else {// 没有打分记录 则 新增
            busiScore.setScore(3);
            scoreService.insertBusiScore(busiScore);
        }
        return AjaxResult.success();
    }

    /**
     * 查询【文章】列表
     */
    @ApiOperation(" 查询【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusiArticle busiArticle)
    {
        startPage();
        List<BusiArticle> list = busiArticleService.selectBusiArticleList(busiArticle);
        for (BusiArticle article:list
        ) {
            //检验是否 empty
            if(StringUtils.isNotEmpty(article.getArticleImgurls())){
                String articleImgurls = article.getArticleImgurls();
                article.setImgs(StrUtils.stringToStringArray(articleImgurls));
            }
            //检验是否 empty
            if(StringUtils.isNotEmpty(article.getArticleVediourls())){
                String vediourls = article.getArticleVediourls();
                article.setVedios(StrUtils.stringToStringArray(vediourls));
            }
            //创建用户不能为空
            SysUser user = userService.selectUserByUserName(article.getCreateUser());
            article.setUser(user);

            //填充 点赞量
            Object praiseCount = stringRedisTemplate.opsForHash().get(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY, String.valueOf(article.getArticleId()));
            if(praiseCount != null){
                article.setPraiseCount(Long.valueOf(String.valueOf(praiseCount)));
            }else article.setPraiseCount(0L);

        }
        return getDataTable(list);
    }
    /**
     * 查询关注【文章】列表
     */
    @ApiOperation(" 查询关注【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/watchList/{userId}")
    public TableDataInfo watchList(@PathVariable("userId") Long userId)
    {
        startPage();
        List<BusiArticle> res = new ArrayList<>();
        //获取关注 列表 ids
        BusiFollows follows = new BusiFollows();
        follows.setFollowId(userId);
        List<BusiFollows> busiFollows = followsService.selectBusiFollowsList(follows);
        for (BusiFollows follow:busiFollows
             ) {
            SysUser user = userService.selectUserById(follow.getUserId());
            BusiArticle article = new BusiArticle();
            article.setCreateUser(user.getUserName());
            List<BusiArticle> busiArticles = busiArticleService.selectBusiArticleList(article);
            res.addAll(busiArticles);
        }
        for (BusiArticle article:res
        ) {
            //检验是否 empty
            if(StringUtils.isNotEmpty(article.getArticleImgurls())){
                String articleImgurls = article.getArticleImgurls();
                article.setImgs(StrUtils.stringToStringArray(articleImgurls));
            }
            //检验是否 empty
            if(StringUtils.isNotEmpty(article.getArticleVediourls())){
                String vediourls = article.getArticleVediourls();
                article.setVedios(StrUtils.stringToStringArray(vediourls));
            }
            //创建用户不能为空
            SysUser user = userService.selectUserByUserName(article.getCreateUser());
            article.setUser(user);

            //填充 点赞量
            Object praiseCount = stringRedisTemplate.opsForHash().get(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY, String.valueOf(article.getArticleId()));
            if(praiseCount != null){
                article.setPraiseCount(Long.valueOf(String.valueOf(praiseCount)));
            }else article.setPraiseCount(0L);
        }
        //根据创建时间排序
        //Collections.sort(res,(x,y)->x.getCreateTime().compareTo(y.getCreateTime()));
        return getDataTable(res);
    }

    /**
     * 查询推荐【文章】列表
     */
    @ApiOperation(" 查询推荐【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/recommendList")
    public TableDataInfo watchList(Long userId, Integer howmany) throws SQLException, TasteException {
        startPage();
        List<RecommendedItem> recommends = RecommendUtils.recommend(userId, howmany);
        List<BusiArticle>  result = new ArrayList<>();
        List<Long>  resIds = new ArrayList<>();
        Integer remain = 0;//剩余 填充
        if(recommends != null && recommends.size() == howmany){
            //把 id 得到的 article  加入 result
            recommends.forEach(x->result.add(busiArticleService.selectBusiArticleById(x.getItemID())));
        }else if(recommends == null){
            remain = howmany;
        }else {
            remain = howmany - recommends.size();
        }
        //推荐结果不足，需要填充 res Ids
        if(remain > 0 && remain < howmany){// 推荐结果 不足 howmany
            recommends.forEach(x->result.add(busiArticleService.selectBusiArticleById(x.getItemID())));
            List<BusiArticle> remainList = busiArticleService.selectBusiArticleTop(Long.valueOf(remain));
            result.addAll(remainList);
        }else if(remain == howmany){// recommends 推荐 无结果
            List<BusiArticle> remainList = busiArticleService.selectBusiArticleTop(Long.valueOf(remain));
            result.addAll(remainList);
        }
       //处理 img,video url
        for (BusiArticle article:result
        ) {
            //检验是否 empty
            if(StringUtils.isNotEmpty(article.getArticleImgurls())){
                String articleImgurls = article.getArticleImgurls();
                article.setImgs(StrUtils.stringToStringArray(articleImgurls));
            }
            //检验是否 empty
            if(StringUtils.isNotEmpty(article.getArticleVediourls())){
                String vediourls = article.getArticleVediourls();
                article.setVedios(StrUtils.stringToStringArray(vediourls));
            }
            //创建用户不能为空
            SysUser user = userService.selectUserByUserName(article.getCreateUser());
            article.setUser(user);
            //填充 点赞量
            Object praiseCount = stringRedisTemplate.opsForHash().get(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY, String.valueOf(article.getArticleId()));
            if(praiseCount != null){
                article.setPraiseCount(Long.valueOf(String.valueOf(praiseCount)));
            }else article.setPraiseCount(0L);
        }
        return getDataTable(result);
    }


    /**
     * 查询图文区【文章】列表
     */
    @ApiOperation(" 查询图文区【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/photoList")
    public TableDataInfo photoList()
    {
        startPage();
        List<BusiArticle> list = busiArticleService.selectPhotoBusiArticleList();
        for (BusiArticle busiArticle:list
        ) {
            //检验是否 empty
            if(StringUtils.isNotEmpty(busiArticle.getArticleImgurls())){
                String articleImgurls = busiArticle.getArticleImgurls();
                busiArticle.setImgs(StrUtils.stringToStringArray(articleImgurls));
            }
            //检验是否 empty
            if(StringUtils.isNotEmpty(busiArticle.getArticleVediourls())){
                String vediourls = busiArticle.getArticleVediourls();
                busiArticle.setVedios(StrUtils.stringToStringArray(vediourls));
            }
            //创建用户不能为空
            SysUser user = userService.selectUserByUserName(busiArticle.getCreateUser());
            busiArticle.setUser(user);
            //点赞量
            //填充 点赞量
            Object praiseCount = stringRedisTemplate.opsForHash().get(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY, String.valueOf(busiArticle.getArticleId()));
            if(praiseCount != null){
                busiArticle.setPraiseCount(Long.valueOf(String.valueOf(praiseCount)));
            }else busiArticle.setPraiseCount(0L);
        }
        return getDataTable(list);
    }

    /**
     * 查询视频区【文章】列表
     */
    @ApiOperation(" 查询视频区【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/vedioList")
    public TableDataInfo vedioList()
    {
        startPage();
        List<BusiArticle> list = busiArticleService.selectVedioBusiArticleList();
        for (BusiArticle busiArticle:list
             ) {
            //检验是否 empty
            if(StringUtils.isNotEmpty(busiArticle.getArticleImgurls())){
                String articleImgurls = busiArticle.getArticleImgurls();
                busiArticle.setImgs(StrUtils.stringToStringArray(articleImgurls));
            }
            //检验是否 empty
            if(StringUtils.isNotEmpty(busiArticle.getArticleVediourls())){
                String vediourls = busiArticle.getArticleVediourls();
                busiArticle.setVedios(StrUtils.stringToStringArray(vediourls));
            }
            //创建用户不能为空
            SysUser user = userService.selectUserByUserName(busiArticle.getCreateUser());
            busiArticle.setUser(user);
            //填充 点赞量
            Object praiseCount = stringRedisTemplate.opsForHash().get(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY, String.valueOf(busiArticle.getArticleId()));
            if(praiseCount != null){
                busiArticle.setPraiseCount(Long.valueOf(String.valueOf(praiseCount)));
            }else busiArticle.setPraiseCount(0L);
        }
        return getDataTable(list);
    }

    /**
     * 导出【文章】列表
     */
    @ApiOperation(" 导出【文章】列表")
    @PreAuthorize("@ss.hasPermi('system:article:export')")
    @Log(title = "【导出【文章】列表】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusiArticle busiArticle)
    {
        List<BusiArticle> list = busiArticleService.selectBusiArticleList(busiArticle);
        ExcelUtil<BusiArticle> util = new ExcelUtil<BusiArticle>(BusiArticle.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 获取【文章】详细信息
     */
    @ApiOperation("获取【文章】详细信息")
    @PreAuthorize("@ss.hasPermi('system:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId)
    {
        BusiArticle busiArticle = busiArticleService.selectBusiArticleById(articleId);
        //检验是否 empty
        if(StringUtils.isNotEmpty(busiArticle.getArticleImgurls())){
            String articleImgurls = busiArticle.getArticleImgurls();
            busiArticle.setImgs(StrUtils.stringToStringArray(articleImgurls));
        }
        //检验是否 empty
        if(StringUtils.isNotEmpty(busiArticle.getArticleVediourls())){
            String vediourls = busiArticle.getArticleVediourls();
            busiArticle.setVedios(StrUtils.stringToStringArray(vediourls));
        }
        //填充 点赞量
        Object praiseCount = stringRedisTemplate.opsForHash().get(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY, String.valueOf(busiArticle.getArticleId()));
        if(praiseCount != null){
            busiArticle.setPraiseCount(Long.valueOf(String.valueOf(praiseCount)));
        }else busiArticle.setPraiseCount(0L);
        return AjaxResult.success(busiArticle);
    }

    /**
     * 新增【文章】
     */
    @ApiOperation("新增【文章】")
    @PreAuthorize("@ss.hasPermi('system:article:add')")
    @Log(title = "【新增【文章】】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiArticle busiArticle)
    {
        //参数imgs 校验
        if(StringUtils.isNotEmpty(busiArticle.getImgs())){
            busiArticle.setArticleImgurls(StrUtils.stringArrayToString(busiArticle.getImgs()));
        }
        //参数vedios 校验
        if(StringUtils.isNotEmpty(busiArticle.getVedios())){
            busiArticle.setArticleVediourls(StrUtils.stringArrayToString(busiArticle.getVedios()));
        }
        return toAjax(busiArticleService.insertBusiArticle(busiArticle));
    }

    /**
     * 修改【文章】
     */
    @ApiOperation("修改文章")
    @PreAuthorize("@ss.hasPermi('system:article:edit')")
    @Log(title = "【 修改【文章】】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiArticle busiArticle)
    {
        //参数 校验
        if(StringUtils.isNotEmpty(busiArticle.getImgs())){
            busiArticle.setArticleImgurls(StrUtils.stringArrayToString(busiArticle.getImgs()));
        }
        //参数 校验
        if(StringUtils.isNotEmpty(busiArticle.getVedios())){
            busiArticle.setArticleVediourls(StrUtils.stringArrayToString(busiArticle.getVedios()));
        }
        return toAjax(busiArticleService.updateBusiArticle(busiArticle));
    }

    /**
     * 删除【文章】
     */
    @ApiOperation("删除文章")
    @PreAuthorize("@ss.hasPermi('system:article:remove')")
    @Log(title = "【删除【文章】】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        return toAjax(busiArticleService.deleteBusiArticleByIds(articleIds));
    }
}
