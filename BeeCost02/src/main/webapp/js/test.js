$(function(){
    /**
     * 发送验证码
     */
    $("#sbmit1").click(function(){
       $.ajax({
       	        url: 'sentcode',
       	        type: 'post',
       	        dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
       	        data: {phone:'18475525887'},
       	        success: function(data) {
       	         var json = JSON.stringify(data);
       	        	alert("success..."+json);
       	        },
       	        error: function(data) {
       	        	alert("error...");
       	        }
        });
    });
    /**
     * 验证验证码
     */
    $("#checkcode").click(function(){
        $.ajax({
            url: 'checkcode',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {code:'123456'},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
	});
    /**
     * 保存基本资料
     */
    $("#baseinfo").click(function(){
        $.ajax({
            url: 'saveUserBaseInfo',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {name:'陈梓平',gender:1,IDNum:"448745819502412351",maritalStatus:0,
                companyName:"维泽数据有限公司",companyProvince:"广东",companyCity:"深圳",
                companyArea:"龙岗区",companyAddress:"城投商务中心903",homeProvince:"广东",
                homeCity:"深圳",homeArea:"龙岗区",homeAddress:"清湖路",goodsProvince:"广东",
                goodsCity:"深圳",goodsArea:"龙岗区",goodsAddress:"清湖路",education:"1"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 保存常用联系人
     */
    $("#contacts").click(function(){
        $.ajax({
            url: 'saveContacts',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {name:'陈梓平',relationship:'关系',phone:"18475525886"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 保存身份证
     */
    $("#IDCard").click(function(){
        $.ajax({
            url: 'saveIDCard',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {oneserverId1:'oneserverId1',oneserverId2:'oneserverId2',oneserverId3:'oneserverId3'},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 保存社保账户
     */
    $("#SSA").click(function(){
        $.ajax({
            url: 'saveSocialSecurityAccount',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {SSA:'SSA',password:'password'},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 保存到银行卡
     */
    $("#BankCard").click(function(){
        $.ajax({
            url: 'saveBankCard',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {bankType:'中国银行',bankCard:'624185122354879578841',openBank:"龙岗支行"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取用户基本信息
     */
    $("#getBaseInfo").click(function(){
        $.ajax({
            url: 'getUserInfs',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改用户基本信息
     */
    $("#updateBaseInfo").click(function(){
        $.ajax({
            url: 'updateUserInfo',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {name:'林生',gender:1,IDNum:"448745819502412351",maritalStatus:0,
                companyName:"维泽数据有限公司",companyProvince:"广东",companyCity:"深圳",
                companyArea:"龙岗区",companyAddress:"城投商务中心903",homeProvince:"广东",
                homeCity:"深圳",homeArea:"龙岗区",homeAddress:"清湖路",goodsProvince:"广东",
                goodsCity:"深圳",goodsArea:"龙岗区",goodsAddress:"清湖路",education:"大专"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取常联系人信息
     */
    $("#getUserContacts").click(function(){
        $.ajax({
            url: 'getUserContacts',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改常用联系人
     */
    $("#updateUserContacts").click(function(){
        $.ajax({
            url: 'updateUserContact',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {name:'陈生',relationship:'关系',phone:"18475525886"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取用户身份证正反面
     */
    $("#getUserIDCard").click(function(){
        $.ajax({
            url: 'getUserIDCard',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改身份证正反面
     */
    $("#updateUserIDCard").click(function(){
        $.ajax({
            url: 'updateUserIDCard',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {oneserverId1:'updateoneserverId1',oneserverId2:'updateoneserverId2'},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取社保信息
     */
    $("#getSSA").click(function(){
        $.ajax({
            url: 'getSSA',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改社保账户
     */0
    $("#updateSSA").click(function(){
        $.ajax({
            url: 'updateSSA',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {SSA:'2SSAupdate',password:'fqwnuifhqw7498'},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取银行卡
     */
    $("#getBankCard").click(function(){
        $.ajax({
            url: 'getBankCard',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改用户银行卡信息
     */
    $("#updateBankCard").click(function(){
        $.ajax({
            url: 'updateBankCard',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {bankType:'中国银行',bankCard:'624185122354879578841',openBank:"龙岗支行"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取商品
     */
    $("#getGoods").click(function(){
        $.ajax({
            url: 'getGoods',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
             data: "limit=0&type=0",
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取手机品牌
     */
    $("#getPhoneType").click(function(){
        $.ajax({
            url: 'getPhoneType',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 根据手机品牌查询商品
     */
    $("#getGoodsByPhoneTypeID").click(function(){
        $.ajax({
            url: 'getGoodsByPhoneTypeID',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
             data: {limit:0,phoneTypeID:14,type:0},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取运营商
     */
    $("#getOperator").click(function(){
        $.ajax({
            url: 'getOperator',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 根据运营商查询商品
     */
    $("#getGoodsByOperator").click(function(){
        $.ajax({
            url: 'getGoodsByOperator',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {limit:0,operatorType:5,type:0},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 根据商品ID查询商品
     */
    $("#getGoodsDetails").click(function(){
        $.ajax({
            url: 'getGoodsDetails',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: "goodID=1",
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 根据商品ID查询商品颜色分类
     */
    $("#getGoodsCSO").click(function(){
        $.ajax({
            url: 'getGoodsCSO',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: "goodID=1",
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 根据商品的颜色/存储/运营商选项获取价格
     */
    $("#getGoodPriceByCSO").click(function(){
        $.ajax({
            url: 'getGoodPriceByCSO',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {goodID:1,ctype:11,stype:8,supID:1},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 加入购物车和立即购买前判断是否注册了用户
     */
    $("#isRegist").click(function(){
        $.ajax({
            url: 'isRegist',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 加入购物车
     */
    $("#addOrder").click(function(){
        $.ajax({
            url: 'addOrder',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {goodID:1,ctype:11,stype:8,pID:1,periods:12,supID:1},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 查看购物车
     */
    $("#getOrderInfo").click(function(){
        $.ajax({
            url: 'getOrderInfo',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {goodID:1,ctype:11,stype:8,otype:5},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
                $("#oneserverId2").html(json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 查看收货地址
     */
    $("#getGoodsAddress").click(function(){
        $.ajax({
            url: 'getGoodsAddress',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 查看单个收货地址
     */
    $("#getSingleGoodsAddress").click(function(){
        $.ajax({
            url: 'getSingleGoodsAddress',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: "id=1",
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 添加订单
     */
    $("#addUserOrder").click(function(){
        $.ajax({
            url: 'addUserOrder',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {goodsID:1,pollenID:23,quota:2000,goodsMoney:1500,skuID:2,salesMancode:"qhfuqfgqfgqigqgqgfq",periods:12,pID:1},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 查看订单列表
     */
    $("#getUserOrders").click(function(){
        $.ajax({
            url: 'getUserOrders',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 添加收货地址
     */
    $("#addGoodsAddress").click(function(){
        $.ajax({
            url: 'addGoodsAddress',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {name:'黄生',province:"广东",
                city:"深圳",area:"龙岗区",detailsAddress:"清湖路",phone:"18475525881"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改收货地址
     */
    $("#updateGoodsAddress").click(function(){
        $.ajax({
            url: 'updateGoodsAddress',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {id:2,name:'欧阳生',province:"广东",
                city:"深圳",area:"南山区",detailsAddress:"清湖路",phone:"18475525882"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 付款页面获取信息
     */
    $("#getGoodsInfoInPay").click(function(){
        $.ajax({
            url: 'getGoodsInfoInPay',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {goodID:1,ctype:11,stype:8,supID:1,pID:1,periods:12,wpackage:"280*24"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 根据城市查询商品
     */
    $("#getGoodsByCity").click(function(){
        $.ajax({
            url: 'getGoodsByCity',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
             data: {limit:0,city:'长沙',type:0},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 购物车-查看购物车
     */
    $("#getShopCart").click(function(){
        $.ajax({
            url: 'getShopCart',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
                $("#oneserverId2").html(json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });

    /**
     * 购物车-付款页面获取信息
     */
    $("#getSCGoodsInfoInPay").click(function(){
        $.ajax({
            url: 'getSCGoodsInfoInPay',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {goodID:1,ctype:11,stype:8,supID:1,periods:12,shopcartID:1,pID:1,wpackage:"280*24"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 购物车-添加订单
     */
    $("#addSCUserOrder").click(function(){
        $.ajax({
            url: 'addSCUserOrder',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {goodsID:1,pollenID:23,quota:2000,goodsMoney:1500,skuID:2,salesMancode:"qhfuqfgqfgqigqgqgfq",periods:12,shopcartID:1,pID:1},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 购物车-删除购物车
     */
    $("#deleteCarts").click(function(){
        $.ajax({
            url: 'deleteCarts',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {cartIDarry:[2,3]},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取套餐
     */
    $("#getPhonePackage").click(function(){
        $.ajax({
            url: 'getPhonePackage',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data: {skuid:18},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 根据城市和运营商查商品
     */
    $("#getGoodsByCityAndOtype").click(function(){
        $.ajax({
            url: 'getGoodsByCityAndOtype',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data:{limit:0,otype:'深圳电信',type:0},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 保存车险保单
     */
    $("#saveInsuranceNum").click(function(){
        $.ajax({
            url: 'saveInsuranceNum',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data:{insuranceNum:"448998849849498"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 保存信用卡
     */
    $("#saveUserCreditCard").click(function(){
        $.ajax({
            url: 'saveUserCreditCard',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data:{creditcard:"6254784169874123654784",bankname:"中国银行"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取信用卡
     */
    $("#getMineCC").click(function(){
        $.ajax({
            url: 'getMineCC',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改信用卡
     */
    $("#updateMineCC").click(function(){
        $.ajax({
            url: 'updateMineCC',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data:{creditcard:"6254784169874123654784",bankname:"中国农业银行"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取车险保单号
     */
    $("#getMineInsNum").click(function(){
        $.ajax({
            url: 'getMineInsNum',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改车险保单号
     */
    $("#updateMineInsNum").click(function(){
        $.ajax({
            url: 'updateMineInsNum',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data:{insuranceNum:"878998849849498"},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 保存saveSSAINCC
     */
    $("#saveSSAINCC").click(function(){
    	var ccdata = {creditcard:"6254784169874123654784",bankname:"中国银行"};
    	var ssadata = {SSA:'SSA',password:'password'};
        $.ajax({
            url: 'saveSSAINCC',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data:{creditcard:"6254784169874123654784",bankname:"中国银行",insuranceNum:"448998849849498",plateNum:"粤B88888",insuranceComp:"维泽数据有限公司",SSA:'SSA',password:'password'},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 修改updateSSAINCC
     */
    $("#updateSSAINCC").click(function(){
        $.ajax({
            url: 'updateSSAINCC',
            type: 'post',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            data:{creditcard:"8254784169874123654784",bankname:"中国工行",insuranceNum:"998998849849498",plateNum:"粤B88888",insuranceComp:"维泽数据有限公司",SSA:'SSA78',password:'11988949849'},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * getMineOrders
     */
    $("#getMineOrders").click(function(){
        $.ajax({
            url: 'getMineOrders',
            type: 'get',
            dataType: 'json', // 这句不加出现415错误:Unsupported Media Type
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 获取订单详情
     */
    $("#getOrderListDetail").click(function(){
        $.ajax({
            url: 'getOrderListDetail',
            type: 'get',
            dataType: 'json',
            data:{oid:64},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
    /**
     * 筛选类型
     */
    $("#getGoodsByScreen").click(function(){
        $.ajax({
            url: 'getGoodsByScreen',
            type: 'post',
            dataType: 'json',
            data:{limit:0,cityAndotype:[],phoneTypeID:[],type:0},
            success: function(data) {
                var json = JSON.stringify(data);
                alert("success..."+json);
            },
            error: function(data) {
                alert("error...");
            }
        });
    });
});