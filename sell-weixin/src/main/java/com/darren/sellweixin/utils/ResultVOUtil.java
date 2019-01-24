package com.darren.sellweixin.utils;

import com.darren.sellweixin.vo.ResultVo;

/**
 * @author: luosihao
 * @date: 2019/1/22 10:43
 */
public class ResultVOUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg("成功");
        resultVo.setCode(0);
        resultVo.setData(object);
        return resultVo;
    }
    // 有可能什么数据都不传
    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        return resultVo;
    }
}
