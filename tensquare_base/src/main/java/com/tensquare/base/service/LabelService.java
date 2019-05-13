package com.tensquare.base.service;


import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签业务逻辑类
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 增加标签
     * @param label
     */
    public void save(Label label){
        //设置ID
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 构建查询条件
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，把条件封装的哪个对象中
             * @param criteriaQuery 封装的查询关键字
             * @param criteriaBuilder 用来封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //存放所有条件
                List<Predicate> list = new ArrayList<>();
                //对比labelname
                if(label.getLabelname()!= null && !"".equals(label.getLabelname())){
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                //对比state
                if(label.getState()!= null && !"".equals(label.getState())){
                    Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                //对比recommend
                if(label.getRecommend()!= null && !"".equals(label.getRecommend())){
                    Predicate predicate = criteriaBuilder.like(root.get("recommend").as(String.class), label.getRecommend());
                    list.add(predicate);
                }
                //创建数组作为返回值
                Predicate[] parr = new Predicate[list.size()];
                //list转换为数组
                parr = list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        });
    }

    /**
     * 分页条件查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1 , size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，把条件封装的哪个对象中
             * @param criteriaQuery 封装的查询关键字
             * @param criteriaBuilder 用来封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //存放所有条件
                List<Predicate> list = new ArrayList<>();
                //对比labelname
                if(label.getLabelname()!= null && !"".equals(label.getLabelname())){
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                //对比state
                if(label.getState()!= null && !"".equals(label.getState())){
                    Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                //对比recommend
                if(label.getRecommend()!= null && !"".equals(label.getRecommend())){
                    Predicate predicate = criteriaBuilder.like(root.get("recommend").as(String.class), label.getRecommend());
                    list.add(predicate);
                }
                //创建数组作为返回值
                Predicate[] parr = new Predicate[list.size()];
                //list转换为数组
                parr = list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        }, pageable);
    }
}
