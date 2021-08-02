package com.darren.cn.eureka_first;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置操作监控 模块枚举
 */
public enum ConfigModuleEnum {
    PROCESS_MANAGEMENT_BASIC_INFO("process_Management_Basic_Info","流程管理-基本信息"),
    PROCESS_MANAGEMENT_PROCESS_SETTING("process_Management_Process_Setting","流程管理-流程设置"),
    PROCESS_MANAGEMENT_FORM_SETTINGS("process_Management_Form_Settings","流程管理-表单设置"),
    PROCESS_MANAGEMENT_LIST_CONFIGURATION("process_Management_List_Configuration","流程管理-列表配置"),
    PROCESS_MANAGEMENT_INTERFACE_MAPPING("process_Management_Interface_Mapping","流程管理-接口映射"),
    PROCESS_MANAGEMENT_BUTTON_SETTING("process_Management_Button_Setting","流程管理-按钮设置"),
    CATEGORY_MANAGEMENT("category_Management","类别管理"),
    APPROVAL_LINE_MANAGEMENT("approval_Line_management","审批线管理")
    ;

    private String type;
    private String message;

    ConfigModuleEnum(String type, String message) {
        this.type = type;
        this.message = message;
    }


    /**
     * 将数据缓存到map中
     */
    private static final Map<String,String> map = new HashMap<>(16);

    static {
        for(ConfigModuleEnum configModule : ConfigModuleEnum.values()){
            map.put(configModule.getType(),configModule.getMessage());
        }
    }

    /**
     * 根据type查询message
     * @param type
     * @return
     */
    public static String getMessageByType(String type){
        return map.get(type);
    }

    /**
     * 获取message列表
     * @return
     */
    public static List<String> getMessageList(){
        return (List<String>) map.values();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

