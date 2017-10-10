package com.wbdp.bee.entity;

/**
 * 用户权限实体类模型
 * @author wangsaijun
 */
public class Wbl_Model_User {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  用户ID
	 */
	private Long userId;
	/**
	 *  模块ID
	 */
	private Long modelId;
	/**
	 * 模块状态,0为未启用
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
	 * 用户ID
	 * @param userId
	 */
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
    /**
     * 用户ID
     * @return
     */	
    public Long getUserId(){
    	return userId;
    }
	/**
	 * 模块ID
	 * @param modelId
	 */
	public void setModelId(Long modelId){
		this.modelId = modelId;
	}
	
    /**
     * 模块ID
     * @return
     */	
    public Long getModelId(){
    	return modelId;
    }
}