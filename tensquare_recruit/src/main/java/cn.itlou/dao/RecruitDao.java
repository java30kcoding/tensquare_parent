package cn.itlou.dao;

import cn.itlou.pojo.Enterprise;
import cn.itlou.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * 查找最新职位
     *
     * @param state
     * @return
     */
    List<Recruit> findTop10ByStateOrderByCreatetimeDesc(String state);

    /**
     * 查找职位
     *
     * @param state
     * @return
     */
    List<Recruit> findTop10ByStateNotOrderByCreatetimeDesc(String state);

}
