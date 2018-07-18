
/**
 * 2017年10月16日
 * 点击关闭弹窗
 */

$(document).ready(function() {
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

})

