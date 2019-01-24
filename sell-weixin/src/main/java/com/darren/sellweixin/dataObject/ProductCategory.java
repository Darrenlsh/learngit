package com.darren.sellweixin.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @DynamicUpdate
 * 该注解可以帮助updateTime自动更新。避免更新数据的时候，只设置了其它值，
 * 而更新时间不变的时候，用save方法的情况下，导致更新后更新时间不变。
 *
 * @author: luosihao
 * @date: 2019/1/17 16:07
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    /**
     * 类目ID
     *
     * 主键由数据库生成
     * strategy = GenerationType.IDENTITY。有很多生成策略，但是推荐这个。
     * 不加生成策略的话，在插入数据的时候可能报错：Table 'sell_weixin.hibernate_sequence' doesn't exist
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    /**
     * 类目名字
     */
    private String categoryName;
    /**
     * 类目编号
     */
    private Integer categoryType;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {

    }
}
