package cn.itlou.service;

import cn.itlou.dao.LabelDao;
import cn.itlou.pojo.Label;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 有分页了就不需要全局搜索了
     *
     * @param label
     * @param page
     * @param size
     * @return
     */
//    public List<Label> findByLabel(Label label) {
//        List<Predicate> predicateList = Lists.newArrayList();
//        return labelDao.findAll((a, b, c) -> {
//            if (!Objects.equals(label.getLabelname(), null) && !Objects.equals(label.getLabelname(), "") ) {
//                Predicate predicate = c.like(a.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
//                predicateList.add(predicate);
//            }
//            if (!Objects.equals(label.getState(), null) && !Objects.equals(label.getState(), "") ) {
//                Predicate predicate = c.equal(a.get("state").as(String.class), "%" + label.getState() + "%");
//                predicateList.add(predicate);
//            }
//            Predicate[] predicates = new Predicate[predicateList.size()];
//            predicateList.toArray(predicates);
//            return c.and(predicates);
//        });
//    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page -1, size);
        List<Predicate> predicateList = Lists.newArrayList();
        return labelDao.findAll((a, b, c) -> {
            if (!Objects.equals(label.getLabelname(), null) && !Objects.equals(label.getLabelname(), "") ) {
                Predicate predicate = c.like(a.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                predicateList.add(predicate);
            }
            if (!Objects.equals(label.getState(), null) && !Objects.equals(label.getState(), "") ) {
                Predicate predicate = c.equal(a.get("state").as(String.class), "%" + label.getState() + "%");
                predicateList.add(predicate);
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            predicateList.toArray(predicates);
            return c.and(predicates);
        }, pageable);
    }

    public Page<Label> toplist(int page, int rows){
        Pageable pageable = PageRequest.of(page -1, rows);
        return labelDao.toplist(pageable);
    }

}
