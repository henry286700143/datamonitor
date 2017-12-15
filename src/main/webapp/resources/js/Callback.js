		var tableId = "table1";
		/*
		回调函数,切换频道
		*/
		function channelCallback() {
			channelName = getGroupValue('channel');
			colName = getChannelFirstCol(channelName);
			setChannelInfo();
			viewStarTable(tableId,  getChannelColumnDetailListURL(), getChannelColumnDetailListParam(), 1);
		}
		/*
		回调函数,切换下部频道
		*/
		function channelextraCallback() {
			channelName = getGroupValue('channelextra');
			colName = getChannelFirstCol(channelName);
			setChannelInfo();

			viewStarTable(tableId, getChannelColumnDetailListURL(), getChannelColumnDetailListParam(), 1);
		}
		/*
		回调函数,切换栏目
		*/
		function colCallback() {
			colName = getGroupValue('col');
			viewStarTable(tableId, getChannelColumnDetailListURL(), getChannelColumnDetailListParam(), 1);
		}