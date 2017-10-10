package com.wbdp.wx.mapper;

import com.wbdp.wx.model.ColorMode;
import com.wbdp.wx.model.CommodityDetails;
import com.wbdp.wx.model.GoodsModel;
import com.wbdp.wx.model.PhonePackage;
import com.wbdp.wx.model.SKU;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {

    List<GoodsModel> selectGoodsByLimit(Map<String, Object> map);

    List<GoodsModel> selectGoodsByLimitAndGoodsTypeID(Map<String, Object> map);

    List<GoodsModel>  selectGoodsByOperatorTypeSort(Map<String, Object> map);

    List<CommodityDetails> selectCommodityDetailsByGoodID(@Param("period") long period, @Param("goodID") long goodID);

    List<ColorMode> selectGoodsColorType(@Param("goodID") long goodID, @Param("sortType") int sortType);

    SKU selectGoodPiceByCSO(Map<String, Object> map);

    CommodityDetails selectOneCommodityDetailsByGoodID(@Param("goodID") long goodID);

    List<GoodsModel> selectGoodsByLimitAndCity(Map<String, Object> map);
    
    List<PhonePackage> selectpackageBySKUID(@Param("skuID") long skuID);
    
    List<GoodsModel> getGoodsByCityAndOtype(Map<String, Object> map);
    
    List<GoodsModel> selectGoodsByLimitAndGoodsTypeIDAndSupplier(Map<String, Object> map);
}