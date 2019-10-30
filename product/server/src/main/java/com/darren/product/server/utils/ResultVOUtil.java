package com.darren.product.server.utils;


import com.darren.product.server.VO.ResultVO;

/**
 * @author luosihao
 * @date 2019/9/30 20:55
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }
}
