<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>患者端患教资料管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="hospital-name" class="control-label hospital-name-label">资讯标题:</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="admin-patientnews-name"
                           placeholder="请输入标题">
                </div>
         
                
                <div class="hospital-name">
                <select  id="patientnews-content">
                 <option value="">请选择分类</option>
	                <option value="0">图文</option>
	                <option value="1">视频</option>
	                <option value="2">PDF</option>
                </select>
                </div>
                
                <div class="search-btn">
                    <button type="button" class="btn search" id="search">查询结果</button>
                    <button type="button" class="btn clear" id="clear">清空条件</button>
                </div>
            </div>
        </form>
    </div>
    <div class="table-content">
        <div class="add-and-import">
            <button type="button" class="btn add" id="add" onclick="addPatientNews()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>

        </div>

        <div id="adminpatientnewstable" class="hospital-table">
            
        </div>
    </div>
    <div class="footer">
        <div class="page-term form-horizontal">
            <label for="size" class="control-label size-label">每页显示：</label>
            <div class="page-size">
                <select class="form-control input-sm" id="size">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                    <option value="40">40</option>
                    <option value="50">50</option>
                </select>
            </div>
            <label for="size" class="control-label size-label">条</label>
            <div class="page-count">
                全部记录共：<span class="count-num">550</span>条
            </div>
        </div>
        <div class="page-content">
            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>
    </div>
</div>
</#macro>
 <!-- 弹出层 -->
    <div id="deleteLayer" style="display:none">
        <ul class="input-group">
            <label>您确定删除此条患者咨询信息吗？</label>
        </ul>
    </div>
    
     <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="addPatientNewsTitle" class="control-label zf-name-label">新闻标题</label>
                <input type="text" class="form-control zf-layer-input" id="addPatientNewsTitle" placeholder="请输入新闻标题">
            </div>
            
            <div class="form-group">
                <label for="addPatientSubTitle" class="control-label zf-name-label">摘要</label>
                <input type="text" class="form-control zf-layer-input" id="addPatientSubTitle" placeholder="请输入新闻摘要">
            </div>
            
            <div class="form-group">
                <label for="addPatientSmallLogo" class="control-label zf-name-label">缩略图</label>
                    <input type="text" class="form-control zf-layer-input" id="addPatientSmallLogo" placeholder="请输入图片URL">
            </div>

                
                
                  <div class="hospital-name">
                 <label for="addPatientType" class="control-label zf-name-label">内容分类</label>
                <select  id="addPatientType">
                 <option value="">请选择内容分类</option>
	                <option value="0">图片</option>
	                <option value="1">视频</option>
	                <option value="2">PDF</option>
                </select>
                </div>
                
             <div class="form-group">
                <label for="addPatientNewsSource" class="control-label zf-name-label">资讯来源</label>
                <input type="text" class="form-control zf-layer-input" id="addPatientNewsSource" placeholder="请输入资讯来源">
            </div>  
            
            <div class="form-group">
                <label for="addPatientNewsContent" class="control-label zf-name-label">内容</label>
                <input type="text" class="form-control zf-layer-input" id="addPatientNewsContent" placeholder="请输入内容URL">
            </div>   
            
              <div class="form-group">
                <label for="addPatientNewsCreateTime" class="control-label zf-name-label">发布时间</label>
                <input type="text" class="form-control zf-layer-input" id="addPatientNewsCreateTime" onClick="WdatePicker()" placeholder="请输入录入时间">
            </div>   
            
        </form>
    </div>
    
    
              <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="modifyPatientNewsTitle" class="control-label zf-name-label">新闻标题</label>
                <input type="text" class="form-control zf-layer-input" id="modifyPatientNewsTitle" placeholder="请输入新闻标题">
            </div>
            
            <div class="form-group">
                <label for="modifyPatientSubTitle" class="control-label zf-name-label">摘要</label>
                <input type="text" class="form-control zf-layer-input" id="modifyPatientSubTitle" placeholder="请输入新闻摘要">
            </div>
            
            <div class="form-group">
                <label for="modifyPatientSmallLogo" class="control-label zf-name-label">缩略图</label>
                    <input type="text" class="form-control zf-layer-input" id="modifyPatientSmallLogo" placeholder="请输入图片URL">
            </div>
            
                <div class="hospital-name">
                 <label for="modifyPatientType" class="control-label zf-name-label">资讯分类</label>
                <select  id="modifyPatientType">
                 <option value="">请选择内容分类</option>
	                <option value="0">图片</option>
	                <option value="1">视频</option>
	                <option value="2">PDF</option>
                </select>
                </div>
                
                
    
                
             <div class="form-group">
                <label for="modifyPatientNewsSource" class="control-label zf-name-label">资讯来源</label>
                <input type="text" class="form-control zf-layer-input" id="modifyPatientNewsSource" placeholder="请输入资讯来源">
            </div>  
            
            <div class="form-group">
                <label for="modifyPatientNewsContent" class="control-label zf-name-label">内容</label>
                <input type="text" class="form-control zf-layer-input" id="modifyPatientNewsContent" placeholder="请输入内容URL">
            </div>   
            
              <div class="form-group">
                <label for="modifyPatientNewsCreateTime" class="control-label zf-name-label">发布时间</label>
                <input type="text" class="form-control zf-layer-input" id="modifyPatientNewsCreateTime" onClick="WdatePicker()" placeholder="请输入录入时间">
            </div>   
            
        </form>
    </div>
    
    
</div>
<#macro html_foot>
    <@p.js src="base/adminpatientnews.js"/>	
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>