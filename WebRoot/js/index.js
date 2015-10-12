/*轮播*/
	var $imgrolls = $("#jnImageroll div a"); //获取鼠标滑过的文字块
	$imgrolls.css("opacity", "0.7");
	var len = $imgrolls.length; //文字块的个数
	var index = 0; //保存计数
	var adTimer = null;
	$imgrolls.mouseover(function() {
		index = $imgrolls.index(this); //获取当前文字块的下标
		showImg(index);
	}).eq(0).mouseover(); //获取第一个文字块并模拟鼠标

	$('#jnImageroll').hover(function() {
		if (adTimer) {
			clearInterval(adTimer); //鼠标移入停止动画
		}
	}, function() {
		adTimer = setInterval(function() { //鼠标移出开始计时
			showImg(index);
			index++;
			if (index == len) {
				index = 0;
			}
		}, 5000); //每5秒调用showImg(),index+1，直到index==5
	}).trigger("mouseleave"); //模拟触发鼠标移出
	
function showImg(index) {
	var $rollobj = $("#jnImageroll"); //获取整个div
	var $rolllist = $rollobj.find("div a"); //获取每个文字块
	var newhref = $rolllist.eq(index).attr("href"); //读取前文字块上的属性href
	$("#JS_imgWrap").attr("href", newhref) //给图片块加上当前连接属性
		.find("img").eq(index).stop(true, true).fadeIn().siblings().fadeOut(); //依据index找到对应的图片让它滑入，其他的划出
	$rolllist.removeClass("chos").css("opacity", "0.7")
		.eq(index).addClass("chos").css("opacity", "1"); //给当前文字块添加属性chos,高亮文字，设置不透明度
}