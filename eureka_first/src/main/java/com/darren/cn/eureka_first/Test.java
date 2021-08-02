package com.darren.cn.eureka_first;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luosihao
 * @date 2019/9/26 19:12
 */
public class Test {
    public static void main(String[] args) {

//        String s = " ";
//        Boolean is = StringUtils.isBlank(s);
//        Boolean has = StringUtils.isEmpty(s);
//        System.out.println(is);
//        System.out.println(has);
        // 老工单未流转的处理操作，需要排除
//        List<String> excludeProcessNameList = new ArrayList<>();
//        excludeProcessNameList.add("回复内容");
//        excludeProcessNameList.add("上传附件");
//        excludeProcessNameList.add("保存");
//
//        List<Model> models = new ArrayList<>();
//        Model model = new Model();
//        model.setAge(1);
//        model.setName("lili");
//        Model model1  = new Model();
//        model1.setName("ddd");
//        model1.setAge(22);
//
//        Integer num = models.stream().filter(mode -> mode.getName().equals("ee")).collect(Collectors.toList()).size();
//
//        System.out.println(num);
//        Boolean flag = !excludeProcessNameList.contains(null);
//        System.out.println(flag);
//
//        String ss = null;
//        Boolean f = "加油".equals(ss);
//        System.out.println(f);
//        double dvalue = 1.444;
//        double dd = (double)Math.round(dvalue*100)/100;
//        System.out.println(dd);
//        Map<String,String> map = new HashMap<>();
//        //map = null;
//        boolean flag = map.isEmpty();
//        System.out.println(flag);
//
//        double d = 50;
//        double ff = d/60;
//        System.out.println(ff);
//
//        List<Integer> list = new Vector<>();
//        list.add(1);
//        list.get(0);
//        list.remove(0);
//        System.out.println(list);
//
//        List<Integer> list1 = new LinkedList<>();
//        list1.add(1);
//        list1.get(0);
//        list1.remove(0);
//
//        Map<String,Object> map = new Hashtable<>();
//        map.put("rr",1);
//        Map<String,Object> map1 = new TreeMap<>();
//
//        Set<Integer> set = new HashSet<>();
//        Set<Integer> set1 = new TreeSet<>();
//        Set<Integer> set2 = new LinkedHashSet<>();
//        set.add(2);

//        List<String> list = ConfigModuleEnum.getMessageList();
//        System.out.println(list);
//
//        List<Model> models = new ArrayList<>();
//        Model model = new Model();
//        model.setAge(1);
//        model.setName("lili");
//        Model model1  = new Model();
//        model1.setName("ddd");
//        model1.setAge(22);
//        Model model2 = new Model();
//        model2.setAge(32);
//        model2.setName("sfs");
//        models.add(model);
//        models.add(model1);
//        models.add(model2);
//
//        System.out.println(models);
//        models.sort(Comparator.comparing(Model::getName));
//
//        System.out.println(models);

        double seconds = 20;
        double hours = seconds / 60 / 60;
        System.out.println(hours);


    }
}
