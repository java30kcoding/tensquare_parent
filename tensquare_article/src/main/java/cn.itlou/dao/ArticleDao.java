package cn.itlou.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itlou.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    //增删改必须加注解
    @Modifying
    @Query(value = "update tb_article set state = 1 where id = ?", nativeQuery = true)
    public void updateState(String id);

    @Modifying
    @Query(value = "update tb_article set thumbup = thumbup + 1 where id = ?", nativeQuery = true)
    public void addThumbup(String id);

}
