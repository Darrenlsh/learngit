package com.darren.sellweixin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目）
 * @author: luosihao
 * @date: 2019/1/18 15:53
 */
@Data
public class ProductVo {

    /**
     * 该注解可以使返回前端的字段名为name
     */
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
