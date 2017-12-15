/**
 * 树
 */

$(function(){
		$('#tree').tree({
		url: "service?serviceID=/treeService/firstloadInfotree&rootid="+rootid+"&table="+treeTable+"&nodefield="+treeNodefield+"&parentfield="+treeParentfield+"&namefield="+treeNamefield+"&rootname="+encodeURI(encodeURI(rootname)),
		method: "post",
		onClick:function(node){
			if(!hasChildren(node))
			{
				remoteAppend(node);
			}
			var path="";
			var pnode=$('#tree').tree('getParent',node.target);
			var snode=node;
			while(pnode!=null)
			{
				path=pnode.text+"-->"+path;
				snode=pnode;
				pnode=$('#tree').tree('getParent',snode.target);
			}
			path=path+node.text;
			$('#column').panel({title:path});
			var id=node.id;
			if(treeValuefield == treeNamefield){
				id = node.text;
			}
			document.getElementById("treeparentid").value = id;
			document.getElementById("treeparentname").value = path;
		}
	});
});
function hasChildren(node){
	var children ;
	if (node){
		children = $('#tree').tree('getChildren', node.target);
	} else {
		children = $('#tree').tree('getChildren');
	}
	return children.length==0?false:true;
}
function remoteAppend(node){
	if(node.attributes['isappend']=='false')
	{
		return ;
	}
	var url='service?serviceID=/treeService/searchInfochild&parentid='+node.id+"&table="+treeTable+"&nodefield="+treeNodefield+"&parentfield="+treeParentfield+"&namefield="+treeNamefield+"&valuefield="+treeValuefield;
	var tempicon=node.iconCls;
	node.iconCls='icon-tree_loading';
	$('#tree').tree('update', node);
	 $.ajax({
 		 type: "post",
 		 url: url,
  		 dataType: "json",
 		 success : function(d){

 		 		$('#tree').tree('append',{
					parent: (node?node.target:null),
					data:d
				});
				node.iconCls=tempicon;
				$('#tree').tree('update', node);
				node.attributes['isappend']='false';
     	 }
		});

}
function reload(){
	var node = $('#tree').tree('getSelected');
	if (node){
		$('#tree').tree('reload', node.target);
	} else {
		$('#tree').tree('reload');
	}
}
function getChildren(){
	var node = $('#tree').tree('getSelected');
	if (node){
		var children = $('#tree').tree('getChildren', node.target);
	} else {
		var children = $('#tree').tree('getChildren');
	}
	var s = '';
	for(var i=0; i<children.length; i++){
		s += children[i].text + ',';
	}

}
function getChecked(){
	var nodes = $('#tree').tree('getChecked');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') s += ',';
		s += nodes[i].id;
	}
	return s;
}
function getSelected(){
	var node = $('#tree').tree('getSelected');
	alert(node.text);
}
function getParent(){
	var node = $('#tree').tree('getParent');
	alert(node.text);
}
function collapse(){
	var node = $('#tree').tree('getSelected');
	$('#tree').tree('collapse',node.target);
}
function expand(){
	var node = $('#tree').tree('getSelected');
	$('#tree').tree('expand',node.target);
}
function collapseAll(){
	var node = $('#tree').tree('getSelected');
	if (node){
		$('#tree').tree('collapseAll', node.target);
	} else {
		$('#tree').tree('collapseAll');
	}
}
function expandAll(){
	var node = $('#tree').tree('getSelected');
	if (node){
		$('#tree').tree('expandAll', node.target);
	} else {
		$('#tree').tree('expandAll');
	}
}
function append(){
	var node = $('#tree').tree('getSelected');
	$('#tree').tree('append',{
		parent: (node?node.target:null),
		data:[{
			text:'new1'
		},{
			text:'new2',
			state:'closed',
			children:[{
				text:'subnew1'
			},{
				text:'subnew2'
			}]
		}]
	});
}
function remove(){
	var node = $('#tree').tree('getSelected');
	$('#tree').tree('remove', node.target);
}
function update(){
	var node = $('#tree').tree('getSelected');
	if (node){
		node.text = '<span style="font-weight:bold">new text</span>';
		node.iconCls = 'icon-save';
		$('#tree').tree('update', node);
	}
}
function isLeaf(){
	var node = $('#tree').tree('getSelected');
	var b = $('#tree').tree('isLeaf', node.target);
	alert(b)
}