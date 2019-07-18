package cn.itlou.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itlou.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query(value = "select * from tb_problem, tb_pl where id = problemid and labelid = ? order by replytime", nativeQuery = true)
    public Page<Problem> newlist(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem, tb_pl where id = problemid and labelid = ? order by reply", nativeQuery = true)
    public Page<Problem> hotlist(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem, tb_pl where id = problemid and reply = 0 and labelid = ? order by createtime", nativeQuery = true)
    public Page<Problem> waitlist(String labelid, Pageable pageable);

}
