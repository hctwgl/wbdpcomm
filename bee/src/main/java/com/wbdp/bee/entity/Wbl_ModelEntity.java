package com.wbdp.bee.entity;

/**
 * 权限模块实体类
 * @author wangsaijun
 */
public class Wbl_ModelEntity {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  模块名称
	 */
	private String modelName;
	/**
	 *  进入模块路径
	 */
	private String modelUrl;
	/**
	 *  图标字符
	 */
	private String iconFont;
	/**
	 * 模块状态
	 */
	private Integer modelStatus;
	
	
	public Integer getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(Integer modelStatus) {
		this.modelStatus = modelStatus;
	}

	/**
	 * ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
    /**
     * ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 模块名称
	 * @param modelName
	 */
	public void setModelName(String modelName){
		this.modelName = modelName;
	}
	
    /**
     * 模块名称
     * @return
     */	
    public String getModelName(){
    	return modelName;
    }
	/**
	 * 进入模块路径
	 * @param modelUrl
	 */
	public void setModelUrl(String modelUrl){
		this.modelUrl = modelUrl;
	}
	
    /**
     * 进入模块路径
     * @return
     */	
    public String getModelUrl(){
    	return modelUrl;
    }
	/**
	 * 图标字符
	 * @param iconFont
	 */
	public void setIconFont(String iconFont){
		this.iconFont = iconFont;
	}
	
    /**
     * 图标字符
     * @return
     */	
    public String getIconFont(){
    	return iconFont;
    }
}