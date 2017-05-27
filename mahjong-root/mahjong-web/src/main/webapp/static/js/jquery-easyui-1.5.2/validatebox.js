//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
	//jQuery.validator.rules
	//验证用户名
    userName: {
        validator: function (value) {
        	var patten = /^[a-zA-Z0-9_]{4,20}$/;
        	if(patten.test(value)){
        		var result = null;
        		$.ajax({
					url : "user/checkUserName",
					type:"post",
					data:{'userName':value},
					dataType:'json',
					async: false,
					success : function(val) {
						result = val;
					}
				});
        		if(result.code == '0'){
					return true;
				}else{
					$.fn.validatebox.defaults.rules.userName.message = result.msg;
				}	
        			
        	}else{
            	$.fn.validatebox.defaults.rules.userName.message = '用户名长度为4-20个字符,由数字、字母(不分大小写)或下划线组成.';
        	}
        	return false;
        },
        message: ''
    },
    //验证汉字
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: 'The input Chinese characters only.'
    },
    realName: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]{2,5}$/.test(value);
        },
        message: '姓名为2~5个中文.'
    },
    //移动手机号码验证
    Mobile: {//value值为文本框中的值
        validator: function (value) {
            var reg = /^1[3|4|5|7|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '手机号码格式错误。'
    },
    //国内邮编验证
    ZipCode: {
        validator: function (value) {
            var reg = /^[0-9]\d{5}$/;
            return reg.test(value);
        },
        message: 'The zip code must be 6 digits and 0 began.'
    },
  	//数字
    Number: {
        validator: function (value,param) {
        	var reg =/^[0-9]*$/;
        	if(reg.test(value)){
        		if(param != null && param.length == 2){
        			if(value.length > param[1] || value.length < param[0]){
        				return false;
        			}
        		}
        		return true;
        	}
        	return false;
        },
        message: '请输入数字.'
    },
    
    Integer:{
    	validator: function (value,param) {
    		var i = Number(value);
    		if (i >= param[0] && i <= param[1]){
    			return true;
    		}else{
    			return false;
    		}
        },
        message: '限制值大小必须在{0}~{1}之间.'
    },
    
    URL:{
    	validator: function (value) {
    		/*var RegUrl = new RegExp(); 
    		RegUrl.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");
    		return RegUrl.test(str) && value.length < 200;*/
    		
    		/*var strRegex = '^((https|http|ftp|rtsp|mms)?://)' 
			+ '?(([0-9a-zA-Z_!~*\'().&=+$%-]+: )?[0-9a-zA-Z_!~*\'().&=+$%-]+@)?' //ftp的user@ 
			+ '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184 
			+ '|' // 允许IP和DOMAIN（域名） 
			+ '([0-9a-zA-Z_!~*\'()-]+.)*' // 域名- www. 
			+ '([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z].' // 二级域名 
			+ '[a-z]{2,6})' // first level domain- .com or .museum 
			+ '(:[0-9]{1,4})?' // 端口- :80 
			+ '((/?)|' // a slash isn't required if there is no file name 
			+ '(/[0-9a-zA-Z_!~*\'().;?:@&=+$,%#-]+)+/?)$';
			var re=new RegExp(strRegex); 
			//re.test() 
			if (re.test(value) && value.length < 500) { 
			return (true); 
			} else { 
			return (false);
			} */
    		
    		if(value.length > 10 && value.length < 500){
    			return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value)
    		}
    		return false;
        },
        message: '请输入正确的URL,长度不能超过500。'
    },
})