package com.wbdp.bee.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.dao.Wbl_AttributeDAO;
import com.wbdp.bee.dao.Wbl_AttributevalueDAO;
import com.wbdp.bee.dao.Wbl_GoodsDAO;
import com.wbdp.bee.dao.Wbl_Goods_AttributeDAO;
import com.wbdp.bee.dao.Wbl_GoodsimageDAO;
import com.wbdp.bee.dao.Wbl_GoodsskuDAO;
import com.wbdp.bee.dao.Wbl_SupplierDAO;
import com.wbdp.bee.entity.Wbl_AttributeEntity;
import com.wbdp.bee.entity.Wbl_AttributevalueEntity;
import com.wbdp.bee.entity.Wbl_GoodsEntity;
import com.wbdp.bee.entity.Wbl_GoodsSkuModelEntity;
import com.wbdp.bee.entity.Wbl_Goods_attributeEntity;
import com.wbdp.bee.entity.Wbl_GoodsimageEntity;
import com.wbdp.bee.entity.Wbl_GoodsskuEntity;
import com.wbdp.bee.entity.Wbl_SupplierEntity;
import com.wbdp.bee.service.Wbl_GoodsService;
/**
 * 商品业务逻辑实现类
 * @author 汪赛军
 * date:2017年7月13日上午9:44:07
 *
 */
@Service
public class Wbl_GoodsServiceImpl implements Wbl_GoodsService {

	@Autowired
	private Wbl_GoodsDAO wbl_GoodsDAO;
	@Autowired
	private Wbl_AttributevalueDAO wbl_AttributevalueDAO;
	@Autowired
	private Wbl_AttributeDAO wbl_AttributeDAO;
	@Autowired
	private Wbl_Goods_AttributeDAO wbl_Goods_AttributeDAO;
	@Autowired
	private Wbl_GoodsskuDAO wbl_GoodsskuDAO;
	@Autowired
	private Wbl_GoodsimageDAO wbl_GoodsimageDAO;
	@Autowired
	private Wbl_SupplierDAO wbl_SupplierDAO;
	//添加商品，存入sku表中的属性值串默认以品牌、型号、颜色、内存、运营商的顺序存入
	@Override
	public Map<String, Object> addGoods(String json,HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//解析json数据
			JSONObject obj = JSON.parseObject(json);
			//商品名称
			String goodsname = obj.getString("goodsname");
			//品牌
			String brand = obj.getString("brand");
			//型号
			String model = obj.getString("model");
			//颜色
			String color = obj.getString("color");
			//内存
			String memory = obj.getString("memory");
			//运营商
			String operator = obj.getString("operator");
			//价格
			String price = obj.getString("price");
			//图片路径
			String imgUrl = obj.getString("imgUrl");
			/*if(imgUrl!=null||"".equals(imgUrl)){
				Map<String,Object> jsonMap= new HashMap<String,Object>();
				jsonMap.put("firstPath", imgUrl);
				imgUrl = JSON.toJSON(jsonMap).toString();
			}*/
			//供应商id
			String supID = obj.getString("supid");
			
			//sku表中属性值ID串
			StringBuffer valueIDstr = new StringBuffer();
			//获取sku列表，有相同数据则不插入
			List<String> skuList = wbl_GoodsskuDAO.listWbl_GoodsskuAll();
			//获取属性关联列表，有相同数据则不插入
			List<String> goods_attributeList = wbl_Goods_AttributeDAO.getGoods_AttributeStr();
			//存入商品表，查询是否有当前商品信息，有则获取id，无则执行插入操作
			Wbl_GoodsEntity wbl_GoodsEntity = wbl_GoodsDAO.getWbl_GoodsEntity(goodsname,Long.parseLong(supID));
			Long goodsID = null;
			//当商品对象为空时则执行插入操作并获取id
			if(wbl_GoodsEntity==null){
				Wbl_GoodsskuEntity goodssku = new Wbl_GoodsskuEntity();
				Wbl_GoodsEntity goods = new Wbl_GoodsEntity();
				goods.setGoodsname(goodsname);
				goods.setGoodsstate(0);
				goods.setCreatby(session.getAttribute("username").toString());
				goods.setCreatdate(new Date());
				goods.setGoodstypeid(Long.parseLong(brand));
				goods.setSupid(Long.parseLong(supID));
				//将商品保存至商品表
				wbl_GoodsDAO.insertWbl_GoodsEntity(goods);
				//获取商品id
				goodsID = goods.getId();
				//将商品id加入sku实体类中
				goodssku.setGoodsid(goodsID);
				//查询品牌对应的属性名ID
				Wbl_AttributeEntity brandAttribute = wbl_AttributeDAO.getWbl_Attribute("品牌");
				//保存品牌与商品关系关联数据(wbl_goods_attribute)
				//判断是否有重复数据
				if(goods_attributeList.contains(goodsID.toString()+","+brand)){
					System.out.println("存在重复型号关联数据");
				}else{
					Wbl_Goods_attributeEntity brand_Goods_attribute = new Wbl_Goods_attributeEntity();
					brand_Goods_attribute.setAttributeid(brandAttribute.getId());
					brand_Goods_attribute.setValueid(Long.parseLong(brand));
					brand_Goods_attribute.setGoodsid(goodsID);
					brand_Goods_attribute.setCreatdate(new Date());
					wbl_Goods_AttributeDAO.insertGoods_Attribute(brand_Goods_attribute);
				}
				
				//查询颜色对应属性名id以及颜色值id
				Wbl_AttributevalueEntity color_Attributevalue = wbl_AttributevalueDAO.selectWbl_AttributevalueEntity(color);
				//判断是否为空，为空时则执行插入操作，否则将颜色属性值id拼接到sku属性id串中
				Long colorID = null;
				if(color_Attributevalue==null){
					Wbl_AttributevalueEntity attributeValue = new Wbl_AttributevalueEntity();
					 //查询出颜色对应的属性名对象
					Wbl_AttributeEntity color_Attribute = wbl_AttributeDAO.getWbl_Attribute("颜色");
					attributeValue.setAttributeid(color_Attribute.getId());
					attributeValue.setAttributevalue(color);
					attributeValue.setCreatdate(new Date());
					//新增颜色属性值数据
					wbl_AttributevalueDAO.insertWbl_Attributevalue(attributeValue);
					//获取插入数据的id
					colorID = attributeValue.getId();
					//保存颜色与商品关系关联数据(wbl_goods_attribute)
					//判断是否有重复数据
					if(goods_attributeList.contains(goodsID.toString()+","+colorID.toString())){
						System.out.println("存在重复颜色关联数据");
					}else{
						Wbl_Goods_attributeEntity color_Goods_attribute = new Wbl_Goods_attributeEntity();
						color_Goods_attribute.setAttributeid(color_Attribute.getId());
						color_Goods_attribute.setGoodsid(goodsID);
						color_Goods_attribute.setValueid(colorID);
						color_Goods_attribute.setCreatdate(new Date());
						wbl_Goods_AttributeDAO.insertGoods_Attribute(color_Goods_attribute);
					}
					//将颜色属性值id拼接到sku属性值id串中
					valueIDstr.append(colorID);
					valueIDstr.append(",");
					//保存商品图片路径
					Wbl_GoodsimageEntity goodsImage = new Wbl_GoodsimageEntity();
					goodsImage.setGoodsid(goodsID);
					goodsImage.setValueid(colorID);
					goodsImage.setUrl(imgUrl);
					goodsImage.setCreatdate(new Date());
					wbl_GoodsimageDAO.insertImage(goodsImage);
				}else{//不为空时
					//保存颜色与商品关系关联数据(wbl_goods_attribute)
					//判断是否有重复数据
					if(goods_attributeList.contains(goodsID.toString()+","+color_Attributevalue.getId().toString())){
						System.out.println("存在重复颜色关联数据");
					}else{
						Wbl_Goods_attributeEntity color_Goods_attribute = new Wbl_Goods_attributeEntity();
						color_Goods_attribute.setAttributeid(color_Attributevalue.getAttributeid());
						color_Goods_attribute.setGoodsid(goodsID);
						color_Goods_attribute.setValueid(color_Attributevalue.getId());
						color_Goods_attribute.setCreatdate(new Date());
						wbl_Goods_AttributeDAO.insertGoods_Attribute(color_Goods_attribute);
					}
					//将颜色属性值id拼接到sku属性值id串中
					valueIDstr.append(color_Attributevalue.getId());
					valueIDstr.append(",");
					//保存商品图片路径
					Wbl_GoodsimageEntity goodsImage = new Wbl_GoodsimageEntity();
					goodsImage.setGoodsid(goodsID);
					goodsImage.setValueid(color_Attributevalue.getId());
					goodsImage.setUrl(imgUrl);
					goodsImage.setCreatdate(new Date());
					wbl_GoodsimageDAO.insertImage(goodsImage);
				}
				
				
				//存储容量
				//将存储容量属性值ID拼接到sku属性值id串中
				valueIDstr.append(memory);
				valueIDstr.append(",");
				//查询存储对应的属性名ID
				Wbl_AttributeEntity memoryAttribute = wbl_AttributeDAO.getWbl_Attribute("存储");
				//保存存储容量与商品关系关联数据(wbl_goods_attribute)
				//判断是否有重复数据
				if(goods_attributeList.contains(goodsID.toString()+","+memory)){
					
				}else{
					Wbl_Goods_attributeEntity memory_Goods_attribute = new Wbl_Goods_attributeEntity();
					memory_Goods_attribute.setAttributeid(memoryAttribute.getId());
					memory_Goods_attribute.setValueid(Long.parseLong(memory));
					memory_Goods_attribute.setGoodsid(goodsID);
					memory_Goods_attribute.setCreatdate(new Date());
					wbl_Goods_AttributeDAO.insertGoods_Attribute(memory_Goods_attribute);
				}
				
				//运营商
				//将运营商属性值ID拼接到sku属性值id串中
				valueIDstr.append(operator);
				valueIDstr.append(",");
				//查询运营商对应的属性名ID
				Wbl_AttributeEntity operatorAttribute = wbl_AttributeDAO.getWbl_Attribute("运营商");
				//保存运营商与商品关系关联数据(wbl_goods_attribute)
				//判断是否有重复数据
				if(goods_attributeList.contains(goodsID.toString()+","+operator)){
					System.out.println("存在重复运营商关联数据");
				}else{
					Wbl_Goods_attributeEntity operator_Goods_attribute = new Wbl_Goods_attributeEntity();
					operator_Goods_attribute.setAttributeid(operatorAttribute.getId());
					operator_Goods_attribute.setValueid(Long.parseLong(operator));
					operator_Goods_attribute.setGoodsid(goodsID);
					operator_Goods_attribute.setCreatdate(new Date());
					wbl_Goods_AttributeDAO.insertGoods_Attribute(operator_Goods_attribute);
				}
				
				//将品牌属性值ID拼接到sku属性值id串中
				valueIDstr.append(brand);
				valueIDstr.append(",");
				
				//查询型号对应属性名id以及型号值id
				Wbl_AttributevalueEntity model_Attributevalue = wbl_AttributevalueDAO.selectWbl_AttributevalueEntity(model);
				//判断是否为空，为空时则执行插入操作，否则将型号属性值id拼接到sku属性id串中
				Long modelID = null;
				if(model_Attributevalue==null){
					Wbl_AttributevalueEntity attributeValue = new Wbl_AttributevalueEntity();
					 //查询出型号对应的属性名id
					Wbl_AttributeEntity model_Attribute = wbl_AttributeDAO.getWbl_Attribute("型号");
					attributeValue.setAttributeid(model_Attribute.getId());
					attributeValue.setAttributevalue(model);
					attributeValue.setCreatdate(new Date());
					//新增品牌属性值数据
					wbl_AttributevalueDAO.insertWbl_Attributevalue(attributeValue);
					modelID = attributeValue.getId();
					//保存型号与商品关系关联数据(wbl_goods_attribute)
					//判断是否有重复数据
					if(goods_attributeList.contains(goodsID.toString()+","+modelID.toString())){
						System.out.println("存在重复型号关联数据");
					}else{
						Wbl_Goods_attributeEntity model_Goods_attribute = new Wbl_Goods_attributeEntity();
						model_Goods_attribute.setAttributeid(model_Attribute.getId());
						model_Goods_attribute.setGoodsid(goodsID);
						model_Goods_attribute.setValueid(modelID);
						model_Goods_attribute.setCreatdate(new Date());
						wbl_Goods_AttributeDAO.insertGoods_Attribute(model_Goods_attribute);
					}
					//将型号属性值id拼接到sku属性值id串中
					valueIDstr.append(modelID);
				}else{//不为空时
					//保存型号与商品关系关联数据(wbl_goods_attribute)
					//判断是否有重复数据
					if(goods_attributeList.contains(goodsID.toString()+","+model_Attributevalue.getId().toString())){
						System.out.println("存在重复型号关联数据");
					}else{
						Wbl_Goods_attributeEntity model_Goods_attribute = new Wbl_Goods_attributeEntity();
						model_Goods_attribute.setAttributeid(model_Attributevalue.getAttributeid());
						model_Goods_attribute.setGoodsid(goodsID);
						model_Goods_attribute.setValueid(model_Attributevalue.getId());
						model_Goods_attribute.setCreatdate(new Date());
						wbl_Goods_AttributeDAO.insertGoods_Attribute(model_Goods_attribute);
					}
					//将型号属性值id拼接到sku属性值id串中
					valueIDstr.append(model_Attributevalue.getId());
				}
				
				//sku
				if(skuList.contains(valueIDstr.toString())){
					System.out.println("存在相同sku数据");
				}else{
					//将sku属性值id串放入sku实体中
					goodssku.setValueidstr(valueIDstr.toString());
					goodssku.setPrice(Integer.parseInt(price));
					goodssku.setCreatedate(new Date());
					goodssku.setSupid(Long.parseLong(supID));
					goodssku.setSkustatus(0);
					wbl_GoodsskuDAO.insertWbl_GoodsskuEntity(goodssku);
				}
			}else{
				Wbl_GoodsskuEntity goodssku = new Wbl_GoodsskuEntity();
				//获取商品id
				goodsID = wbl_GoodsEntity.getId();
				//将商品id加入sku实体类中
				goodssku.setGoodsid(goodsID);
				//查询品牌对应的属性名ID
				Wbl_AttributeEntity brandAttribute = wbl_AttributeDAO.getWbl_Attribute("品牌");
				//保存品牌与商品关系关联数据(wbl_goods_attribute)
				//判断是否有重复数据
				if(goods_attributeList.contains(goodsID.toString()+","+brand)){
					System.out.println("存在相同品牌关联数据");
				}else{
					Wbl_Goods_attributeEntity brand_Goods_attribute = new Wbl_Goods_attributeEntity();
					brand_Goods_attribute.setAttributeid(brandAttribute.getId());
					brand_Goods_attribute.setValueid(Long.parseLong(brand));
					brand_Goods_attribute.setGoodsid(goodsID);
					brand_Goods_attribute.setCreatdate(new Date());
					wbl_Goods_AttributeDAO.insertGoods_Attribute(brand_Goods_attribute);
				}
				
				//查询颜色对应属性名id以及颜色值id
				Wbl_AttributevalueEntity color_Attributevalue = wbl_AttributevalueDAO.selectWbl_AttributevalueEntity(color);
				//判断是否为空，为空时则执行插入操作，否则将颜色属性值id拼接到sku属性id串中
				Long colorID = null;
				if(color_Attributevalue==null){
					Wbl_AttributevalueEntity attributeValue = new Wbl_AttributevalueEntity();
					 //查询出颜色对应的属性名对象
					Wbl_AttributeEntity color_Attribute = wbl_AttributeDAO.getWbl_Attribute("颜色");
					attributeValue.setAttributeid(color_Attribute.getId());
					attributeValue.setAttributevalue(color);
					attributeValue.setCreatdate(new Date());
					//新增颜色属性值数据
					wbl_AttributevalueDAO.insertWbl_Attributevalue(attributeValue);
					//获取插入数据的id
					colorID = attributeValue.getId();
					//保存颜色与商品关系关联数据(wbl_goods_attribute)
					//判断是否有重复数据
					if(goods_attributeList.contains(goodsID.toString()+","+colorID.toString())){
						System.out.println("存在相同颜色关联数据");
					}else{
						Wbl_Goods_attributeEntity color_Goods_attribute = new Wbl_Goods_attributeEntity();
						color_Goods_attribute.setAttributeid(color_Attribute.getId());
						color_Goods_attribute.setGoodsid(goodsID);
						color_Goods_attribute.setValueid(colorID);
						color_Goods_attribute.setCreatdate(new Date());
						wbl_Goods_AttributeDAO.insertGoods_Attribute(color_Goods_attribute);
					}
					//将颜色属性值id拼接到sku属性值id串中
					valueIDstr.append(colorID);
					valueIDstr.append(",");
					//保存商品图片路径
					Wbl_GoodsimageEntity goodsImage = new Wbl_GoodsimageEntity();
					goodsImage.setGoodsid(goodsID);
					goodsImage.setValueid(colorID);
					goodsImage.setUrl(imgUrl);
					goodsImage.setCreatdate(new Date());
					wbl_GoodsimageDAO.insertImage(goodsImage);
				}else{//不为空时
					//保存型号与商品关系关联数据(wbl_goods_attribute)
					//判断是否有重复数据
					if(goods_attributeList.contains(goodsID.toString()+","+color_Attributevalue.getId().toString())){
						System.out.println("存在相同颜色关联数据");
					}else{
						Wbl_Goods_attributeEntity color_Goods_attribute = new Wbl_Goods_attributeEntity();
						color_Goods_attribute.setAttributeid(color_Attributevalue.getAttributeid());
						color_Goods_attribute.setGoodsid(goodsID);
						color_Goods_attribute.setValueid(color_Attributevalue.getId());
						color_Goods_attribute.setCreatdate(new Date());
						wbl_Goods_AttributeDAO.insertGoods_Attribute(color_Goods_attribute);
					}
					//将型号属性值id拼接到sku属性值id串中
					valueIDstr.append(color_Attributevalue.getId());
					valueIDstr.append(",");
					//保存商品图片路径
					Wbl_GoodsimageEntity goodsImage = new Wbl_GoodsimageEntity();
					goodsImage.setGoodsid(goodsID);
					goodsImage.setValueid(color_Attributevalue.getId());
					goodsImage.setUrl(imgUrl);
					goodsImage.setCreatdate(new Date());
					wbl_GoodsimageDAO.insertImage(goodsImage);
				}
				
				//存储容量
				//将存储容量属性值ID拼接到sku属性值id串中
				valueIDstr.append(memory);
				valueIDstr.append(",");
				//查询存储对应的属性名ID
				Wbl_AttributeEntity memoryAttribute = wbl_AttributeDAO.getWbl_Attribute("存储容量");
				System.out.println("存储容量："+memoryAttribute.getId());
				//保存存储容量与商品关系关联数据(wbl_goods_attribute)
				//判断是否有重复数据
				if(goods_attributeList.contains(goodsID.toString()+","+memory)){
						System.out.println("存在相同存储容量关联数据");
				}else{
					Wbl_Goods_attributeEntity memory_Goods_attribute = new Wbl_Goods_attributeEntity();
					memory_Goods_attribute.setAttributeid(memoryAttribute.getId());
					memory_Goods_attribute.setValueid(Long.parseLong(memory));
					memory_Goods_attribute.setGoodsid(goodsID);
					memory_Goods_attribute.setCreatdate(new Date());
					wbl_Goods_AttributeDAO.insertGoods_Attribute(memory_Goods_attribute);
				}
				
				//运营商
				//将运营商属性值ID拼接到sku属性值id串中
				valueIDstr.append(operator);
				valueIDstr.append(",");
				//查询运营商对应的属性名ID
				Wbl_AttributeEntity operatorAttribute = wbl_AttributeDAO.getWbl_Attribute("运营商");
				//保存运营商与商品关系关联数据(wbl_goods_attribute)
				//判断是否有重复数据
				if(goods_attributeList.contains(goodsID.toString()+","+operator)){
					System.out.println("存在相同运营商数据");
				}else{
					Wbl_Goods_attributeEntity operator_Goods_attribute = new Wbl_Goods_attributeEntity();
					operator_Goods_attribute.setAttributeid(operatorAttribute.getId());
					operator_Goods_attribute.setValueid(Long.parseLong(operator));
					operator_Goods_attribute.setGoodsid(goodsID);
					operator_Goods_attribute.setCreatdate(new Date());
					wbl_Goods_AttributeDAO.insertGoods_Attribute(operator_Goods_attribute);
				}
				
				//将品牌属性值ID拼接到sku属性值id串中
				valueIDstr.append(brand);
				valueIDstr.append(",");
				
				//查询型号对应属性名id以及型号值id
				Wbl_AttributevalueEntity model_Attributevalue = wbl_AttributevalueDAO.selectWbl_AttributevalueEntity(model);
				//判断是否为空，为空时则执行插入操作，否则将型号属性值id拼接到sku属性id串中
				Long modelID = null;
				if(model_Attributevalue==null){
					Wbl_AttributevalueEntity attributeValue = new Wbl_AttributevalueEntity();
					 //查询出型号对应的属性名id
					Wbl_AttributeEntity model_Attribute = wbl_AttributeDAO.getWbl_Attribute("型号");
					attributeValue.setAttributeid(model_Attribute.getId());
					attributeValue.setAttributevalue(model);
					attributeValue.setCreatdate(new Date());
					//新增型号属性值数据
					wbl_AttributevalueDAO.insertWbl_Attributevalue(attributeValue);
					modelID = attributeValue.getId();
					//保存型号与商品关系关联数据(wbl_goods_attribute)
					//判断是否有重复数据
					if(goods_attributeList.contains(goodsID.toString()+","+modelID.toString())){
						System.out.println("存在相同型号关联数据");
					}else{
						Wbl_Goods_attributeEntity model_Goods_attribute = new Wbl_Goods_attributeEntity();
						model_Goods_attribute.setAttributeid(model_Attribute.getId());
						model_Goods_attribute.setGoodsid(goodsID);
						model_Goods_attribute.setValueid(modelID);
						model_Goods_attribute.setCreatdate(new Date());
						wbl_Goods_AttributeDAO.insertGoods_Attribute(model_Goods_attribute);
					}
					//将型号属性值id拼接到sku属性值id串中
					valueIDstr.append(modelID);
				}else{//不为空时
					//保存型号与商品关系关联数据(wbl_goods_attribute)
					//判断是否有重复数据
					if(goods_attributeList.contains(goodsID.toString()+","+model_Attributevalue.getId().toString())){
						System.out.println("存在相同型号关联数据");
					}else{
						Wbl_Goods_attributeEntity model_Goods_attribute = new Wbl_Goods_attributeEntity();
						model_Goods_attribute.setAttributeid(model_Attributevalue.getAttributeid());
						model_Goods_attribute.setGoodsid(goodsID);
						model_Goods_attribute.setValueid(model_Attributevalue.getId());
						model_Goods_attribute.setCreatdate(new Date());
						wbl_Goods_AttributeDAO.insertGoods_Attribute(model_Goods_attribute);
					}
					//将型号属性值id拼接到sku属性值id串中
					valueIDstr.append(model_Attributevalue.getId());
				}
				
				//sku
				if(skuList.contains(valueIDstr.toString())){
					System.out.println("存在相同sku数据");
				}else{
					//将sku属性值id串放入sku实体中
					goodssku.setValueidstr(valueIDstr.toString());
					goodssku.setPrice(Integer.parseInt(price));
					goodssku.setCreatedate(new Date());
					goodssku.setSupid(Long.parseLong(supID));
					goodssku.setSkustatus(0);
					wbl_GoodsskuDAO.insertWbl_GoodsskuEntity(goodssku);
				}
			}
			outMap.put("status", "SUCCESS");
			outMap.put("goodsID", Integer.parseInt(goodsID.toString()));
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("goodsID", "");
			return outMap;
		}
		
	}
	
	//商品列表
	@Override
	public Map<String, Object> goodsList(Integer pageNum,HttpSession session) {
		List<Wbl_GoodsSkuModelEntity> modelList = new ArrayList<Wbl_GoodsSkuModelEntity>();
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			Integer page = (pageNum-1)*10;
			//获取数据总条数,算出页数
			Integer count = wbl_GoodsskuDAO.getCount();
			//存入总页数
			session.setAttribute("pages", ((count+10)-1)/10);
			//获取商品sku列表
			List<Wbl_GoodsskuEntity> skuList = wbl_GoodsskuDAO.listWbl_Goodssku(page);
			//获取商品属性值列表
			List<Wbl_AttributevalueEntity> valueList = wbl_AttributevalueDAO.getWbl_Attributevalue();
			//获取手机供应商列表
			List<Wbl_SupplierEntity> supList =  wbl_SupplierDAO.listSupplier();
			for(Wbl_GoodsskuEntity sku:skuList){
				Wbl_GoodsSkuModelEntity skuModel = new Wbl_GoodsSkuModelEntity();
				String valueStr = sku.getValueidstr();
				String[] array = valueStr.split(",");
				int length = array.length;
				//遍历属性值列表
				//当属性值串数组长度大于五时开始匹配
			if(length>=5){
				for(Wbl_AttributevalueEntity value:valueList){
						//当属性值串中的id与属性值列表中的id匹配时，将值放入商品sku模型实体类中
						if(Long.parseLong(array[3])==value.getId()){
							skuModel.setBrand(value.getAttributevalue());
						}
						if(Long.parseLong(array[4])==value.getId()){
							skuModel.setModel(value.getAttributevalue());
						}
						if(Long.parseLong(array[0])==value.getId()){
							skuModel.setColor(value.getAttributevalue());
						}
						if(Long.parseLong(array[1])==value.getId()){
							skuModel.setMemory(value.getAttributevalue());
						}
						if(Long.parseLong(array[2])==value.getId()){
							skuModel.setOperator(value.getAttributevalue());
						}
						int price = sku.getPrice();
						skuModel.setPrice(price);
						skuModel.setSkustatus(sku.getSkustatus());
						skuModel.setId(sku.getId());
					}
				for(Wbl_SupplierEntity s:supList){
					if(s.getId()==sku.getSupid()){
						skuModel.setOperator(s.getSupplier());
					}
				}
				modelList.add(skuModel);
				}
				/*String valueStr = sku.getValueidstr();
				String[] array = valueStr.split(",");
				int length = array.length;
				if(length>=5){
					String brand = wbl_AttributevalueDAO.selectWbl_AttributevalueforID(Long.parseLong(array[0]));
					skuModel.setBrand(brand);
					String model = wbl_AttributevalueDAO.selectWbl_AttributevalueforID(Long.parseLong(array[1]));
					skuModel.setModel(model);
					String color = wbl_AttributevalueDAO.selectWbl_AttributevalueforID(Long.parseLong(array[2]));
					skuModel.setColor(color);
					String memory = wbl_AttributevalueDAO.selectWbl_AttributevalueforID(Long.parseLong(array[3]));
					skuModel.setMemory(memory);
					String operator = wbl_AttributevalueDAO.selectWbl_AttributevalueforID(Long.parseLong(array[4]));
					skuModel.setOperator(operator);
					int price = sku.getPrice();
					skuModel.setPrice(price);
					modelList.add(skuModel);
				}*/
			}
			outMap.put("status", "SUCCESS");
			outMap.put("data", modelList);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("data", "");
			return outMap;
		}
	}

	/**
	 * 修改商品sku上下架状态
	 */
	@Override
	public Map<String, Object> updateStatus(String json) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//解析json
			JSONObject obj = JSON.parseObject(json);
			String id = obj.getString("id");
			Integer status = obj.getInteger("status");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", Long.parseLong(id));
			map.put("status", status);
			Integer type = wbl_GoodsskuDAO.updateStatus(map);
			outMap.put("status", "SUCCESS");
			outMap.put("type", type);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("type", 0);
			return outMap;
		}
	}

}
