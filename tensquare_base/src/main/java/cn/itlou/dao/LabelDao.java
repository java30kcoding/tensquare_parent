package cn.itlou.dao;

import cn.itlou.pojo.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {

    @Query(value = "select * from tb_label order by id", nativeQuery = true)
    public Page<Label> toplist(Pageable pageable);

}
