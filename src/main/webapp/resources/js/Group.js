
		var bindGroupFlag = 0;
		/*
		绑定所有切换事件
		*/
		function updateGroupState() {
				var viewStarGroupList = $('div[viewStarGroup]');
				for ( var i = 0; i < viewStarGroupList.length; i++) {
					var temp = $(viewStarGroupList[i]);
					var viewStarGroup = temp.attr('viewStarGroup');
					var viewSelectedState = temp.attr('viewSelectedState');
					var viewStarUnSelectedState = temp.attr('viewStarUnSelectedState');
					if (typeof (temp.attr('viewStarDefault')) != "undefined") {
						temp.addClass(viewSelectedState);
						temp.attr('selected', 'true');
						temp.removeAttr('viewStarDefault');
					} else {
						temp.addClass(viewStarUnSelectedState);
					}

					temp.unbind('click');
					temp.bind('click', function() {
						groupSelectState(this);
					});
				}
		}
		/*
		所有切换事件中的元素的点击事件
		*/
		function groupSelectState(selectE) {
			var viewStarGroup = $(selectE).attr('viewStarGroup');
			var viewSelectedState = $(selectE).attr('viewSelectedState');
			var viewStarUnSelectedState = $(selectE).attr('viewStarUnSelectedState');
			var viewStarGroupList = $('div[viewStarGroup=' + viewStarGroup + ']');
			var callBack = $(selectE).attr('callBack');
			viewStarGroupList.removeClass(viewSelectedState);
			viewStarGroupList.addClass(viewStarUnSelectedState);
			viewStarGroupList.removeAttr('selected');
			$(selectE).removeClass(viewStarUnSelectedState);
			$(selectE).addClass(viewSelectedState);
			$(selectE).attr('selected', 'true');
			//回调函数
			eval("" + callBack + "()");
		}

		/*
		得到同组元素选定的值
		*/
		function getGroupValue(viewStarGroup) {
			return $('div[viewStarGroup=' + viewStarGroup + '][selected]').attr('data');
		}