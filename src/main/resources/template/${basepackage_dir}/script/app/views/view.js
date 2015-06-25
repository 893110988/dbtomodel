/**
 * Demo
 * 
 * @authors chengliangzhang@ebnew.com
 * @date 2015-04-17 13:53:02
 * @version 1.0
 */
define(function(require, exports, module) {
	/* 模块依赖关系 */
	var Utils = require('utils/utils');
	var Global = require('utils/global');
 	 
	/* 组件依赖注入 */
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar','bui/overlay' ,'bui/form'],
					function(Grid, Data, Toolbar,Overlay,Form) {
						var Store = Data.Store, columns = [
                               <#list table.columns as column>
                               <#if column.columnNameLower=="id">
               						{
    									sortable : false,
    									title : '${column.columnNameLower}',
    									dataIndex : '${column.columnNameLower}',
    									visible : false
    								},
               						
                               </#if>
                             </#list>
						                               
                               <#list table.columns as column>
                                   <#if column.columnNameLower!="id" && column.columnNameLower!="createTime" && column.columnNameLower!="updateTime">
                   						{
        									sortable : false,
        									title : '${column.columnNameLower}',
        									dataIndex : '${column.columnNameLower}',
        									visible : true,
        									width : '25%'
        								},
                   						
                                   </#if>
                               </#list>
						                               
							 
								{
									sortable : false,
									title : '操作',
									dataIndex : 'status',
									renderer : function(value, obj, index) {
										var html = "<a href='javascript:void(0)' class='delHref blue-239' text="+obj.id+" id='delHref_"+obj.id+"'  title='删除'>删除</a>";
										html += "    <a href='javascript:void(0)' class='updateHref blue-239' text="+obj.id+"  id='updateHref_"+obj.id+"'    title='更新'>更新</a>";
										return html;

									},
									width : 100
								} 
								
								];

						/* 请求数据 */
						var store  = new Store({
							autoLoad : true,
							proxy : {
								url : Global.root + '${table.sqlName}/list.json',
								method : 'POST',
								dataType : 'json'

							},
							sortField : 'createTime',
							sortInfo : {
								field : 'createTime',
								direction : 'DESC'
							},

							params : {
								'address' : $('#supplierAddressSearch').val(),
								'supplierName' : $('#supplierNameSearch').val()
							},
							pageSize : 10
						// 配置分页数目
						});

						/* 处理原始数据 */
						store.on('beforeprocessload', function(ev) {
							ev.data.rows = ev.data.rows;
							ev.data.results = ev.data.totalCount;
						});
						/* 数据请求成功后 */
						store.on('load', function(ev) {
							var num = store.getTotalCount();
							$("#numerPBarTotalNumber b").text(num);
						});
 
						/* 配置分页工具条 */
						var NumerPBar = Toolbar.NumberPagingBar, bar = new NumerPBar(
								{
									render : '#bar',
									autoRender : true,
									prevText : '<',
									nextText : '>',
									content : '<div id="numerPBarTotalNumber" class="totalNumber">共<b>0</b>条记录</div>',
									elCls : 'pagination pull-right',
									store : store
								});

						/* 配置Grid参数 */
						var grid = new Grid.Grid({
							render : '#grid',
							itemStatusFields : {
								gray : 'disabled'
							},
							autoRender : true,
							width : '100%',
							columns : columns,
							innerBorder : false,
							store : store
						});

						
						/********************************以下为操作**************************/
						
						$('#searchBtn').on('click', function() {
							store.load({
									'address' : $('#supplierAddressSearch').val(),
									'supplierName' : $('#supplierNameSearch').val()
								}
							);
						});

						/**
						 * 更新表单
						 */
						 var updateForm = new Form.HForm({
								srcNode : '#updateForm',
								 submitType:'ajax',
				 		          dataType : 'json',
				 		          method : 'POST',
				 		         action :  Global.root+"${table.sqlName}/update.json",
				 		          callback : function(res) {
				 		        	 if(res.resStatus < 300) {
				 		        		BUI.Message.Alert('更新供应商成功!',function(){
				 		         			 window.location.href=Global.root+"html/${table.sqlName}/index.html";
				 		         		 },'info').show();
				 		        	 } else {
				 		        		 BUI.Message.Alert(res.resMsg,'error').show();
				  		        	 }
				  		         }
							}).render();
						 
						 /**
						  * 更新对话框
						  */
						 var dialog = new Overlay.Dialog({
								title : '更新供应商',
								width : 500,
								height : 200,
								buttons : [ {
									text : '保存',
									elCls : 'button button-primary',
									handler : function() {
										updateForm.submit();
										this.close();
									}
								}, {
									text : '取消',
									elCls : 'button',
									handler : function() {
										this.close();
									}
								} ],
								mask : false, // 设置是否模态
								// 配置DOM容器的编号
								contentId : 'updateContent'
							});
						 
						/**
						 * 更新操作
						 */
						$(".updateHref ").live('click',function(evn) {
							 var self = $(this);
							 var obj = self.attr("text");
							 new BUI.Data.Proxy.Ajax({
								  method : 'GET',
						    	  dataType : 'json',
						    	  url :  Global.root +'${table.sqlName}/get.json'
						    	  }).save("get", {
										'id' : obj
									},function(data) {
										updateForm.setRecord(data);
										 dialog.show();
									},this);
						});
						
						 /**
						 * 删除操作
						 */
						$(".delHref ").live('click',function(evn) {
							 var self = $(this);
							 var obj = self.attr("text");
				 
							 BUI.Message
								.Show({
									title : '系统提示',
									msg : '确定要删除供应商【' + obj + '】?',
									icon : 'question',
									buttons : [
											{
												text : '确认',
												elCls : 'button button-primary',
												handler : function() {
													var curDig = this;
													
													new BUI.Data.Proxy.Ajax({
														  method : 'POST',
												    	  dataType : 'json',
												    	  url : Global.root+"${table.sqlName}/remove.json"
												    	  }).save("remove", {
																'id' : obj
															},function(data) {
																if (data != null && data.resStatus < 300) {
																	BUI.Message.Alert("删除成功!","info");
																} else {
																	BUI.Message.Alert("删除失败!","error");
																}
																curDig.close();
																if (store != null) {
																	store.load();
																}
															},this);
												}
											}, {
												text : '取消',
												elCls : 'button',
												handler : function() {
													this.close();
												}
											}]
								});
							 
						});

						
						/**
						 * 返回
						 */
					       $("#returnBtn").on('click',function(){
					    	   window.location.href=Global.root+"html/${table.sqlName}/index.html";
					       });
					       
						   /**
						    * 添加供应商
						    */
					       var form =  new Form.Form({
						          srcNode : '#supplier_form',
						          submitType:'ajax',
				 		          dataType : 'json',
				 		          method : 'POST',
				 		          callback : function(res) {
				 		        	 if(res.resStatus < 300) {
				 		        		BUI.Message.Alert('增加供应商成功!',function(){
				 		         			 window.location.href=Global.root+"html/${table.sqlName}/index.html";
				 		         		 },'info');
				 		        	 } else {
				 		        		 BUI.Message.Alert(res.resMsg,'error');
				  		        	 }
				  		         },
				 		           action : Global.root+"${table.sqlName}/save.json"
					       }).render();
						
					}); 
 
});
 

 