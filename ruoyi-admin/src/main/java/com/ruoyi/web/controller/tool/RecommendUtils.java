package com.ruoyi.web.controller.tool;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.sql.SQLException;
import java.util.List;

/**
 * @author dfm
 * @create 2021-04-15 19:41
 */
public class RecommendUtils {

    public static List<RecommendedItem> recommend(Long userID,int howmany) throws SQLException, TasteException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("" +
                "jdbc:mysql://localhost:3306/ry_vue?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8"
        );
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("root");
        mysqlDataSource.setDatabaseName("ry_vue");
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setCharacterEncoding("utf-8");

        // 使用mysql 作为数据源
        MySQLJDBCDataModel model = new MySQLJDBCDataModel(mysqlDataSource,
                "busi_score",
                "uid",
                "aid",
                "score",
                "ts");

        //用户相似度，使用基于皮尔逊相关系数计算相似度
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        //选择邻居用户，使用NearestNUserNeighborhood实现UserNeighborhood接口，选择邻近的4个用户
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(4,similarity,model);

        Recommender recommender = new GenericBooleanPrefUserBasedRecommender(model,neighborhood,similarity);

        List<RecommendedItem> recommend = recommender.recommend(userID, howmany);

        return recommend;
    }
}
