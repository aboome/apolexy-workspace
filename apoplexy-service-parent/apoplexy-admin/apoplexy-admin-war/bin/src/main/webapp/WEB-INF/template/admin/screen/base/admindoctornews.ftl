<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>医生端讯息资料管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="hospital-name" class="control-label hospital-name-label">资讯标题:</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="admin-doctornews-name"
                           placeholder="请输入标题">
                </div>
         
                <div class="hospital-name">
                <select  id="doctorselect">
                    <option value="">请选择咨询分类</option>
	                <option value="1">学术知识</option>
	                <option value="2">新闻资讯</option>
                </select>
                </div>
                
                <div class="hospital-name">
                <select  id="doctor-content">
                 <option value="">请选择内容分类</option>
	                <option value="0">图片</option>
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
            <button type="button" class="btn add" id="add" onclick="addDoctorNews()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>

        </div>

        <div id="admindoctornewstable" class="hospital-table">
            
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
 <!-- 弹出层 -->
     <div id="deleteLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo">您确定要删除该条记录吗？</p>
    </div>
    
        <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="addDoctorNewsTitle" class="control-label zf-name-label">新闻标题</label>
                <input type="text" class="form-control zf-layer-input" id="addDoctorNewsTitle" placeholder="请输入新闻标题">
            </div>
            
            <div class="form-group">
                <label for="addDoctorSubTitle" class="control-label zf-name-label">摘要</label>
                <input type="text" class="form-control zf-layer-input" id="addDoctorSubTitle" placeholder="请输入新闻摘要">
            </div>
            
            <div class="form-group">
                <label for="addDoctorSmallLogo" class="control-label zf-name-label">缩略图</label>
                    <input type="text" class="form-control zf-layer-input" id="addDoctorSmallLogo" placeholder="请输入图片URL">
            </div>
            
                <div class="hospital-name">
                 <label for="addDoctorNewsType" class="control-label zf-name-label">资讯分类</label>
                <select  id="addDoctorNewsType">
                 <option value="">请选择咨询分类</option>
	                <option value="1">学术知识</option>
	                <option value="2">新闻资讯</option>
                </select>
                </div>
                
                
                  <div class="hospital-name">
                 <label for="addDoctorType" class="control-label zf-name-label">内容分类</label>
                <select  id="addDoctorType">
                 <option value="">请选择内容分类</option>
	                <option value="0">图片</option>
	                <option value="1">视频</option>
	                <option value="2">PDF</option>
                </select>
                </div>
                
             <div class="form-group">
                <label for="addDoctorNewsSource" class="control-label zf-name-label">资讯来源</label>
                <input type="text" class="form-control zf-layer-input" id="addDoctorNewsSource" placeholder="请输入资讯来源">
            </div>  
            
            <div class="form-group">
                <label for="addDoctorNewsContent" class="control-label zf-name-label">内容</label>
                <input type="text" class="form-control zf-layer-input" id="addDoctorNewsContent" placeholder="请输入内容URL">
            </div>   
            
              <div class="form-group">
                <label for="addDoctorNewsCreateTime" class="control-label zf-name-label">发布时间</label>
                <input type="text" class="form-control zf-layer-input" id="addDoctorNewsCreateTime" onClick="WdatePicker()" placeholder="请输入录入时间">
            </div>   
            
        </form>
    </div>
     
     
             <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="modifyDoctorNewsTitle" class="control-label zf-name-label">新闻标题</label>
                <input type="text" class="form-control zf-layer-input" id="modifyDoctorNewsTitle" placeholder="请输入新闻标题">
            </div>
            
            <div class="form-group">
                <label for="modifyDoctorSubTitle" class="control-label zf-name-label">摘要</label>
                <input type="text" class="form-control zf-layer-input" id="modifyDoctorSubTitle" placeholder="请输入新闻摘要">
            </div>
            
            <div class="form-group">
                <label for="modifyDoctorSmallLogo" class="control-label zf-name-label">缩略图</label>
                    <input type="text" class="form-control zf-layer-input" id="modifyDoctorSmallLogo" placeholder="请输入图片URL">
            </div>
            
                <div class="hospital-name">
                 <label for="modifyDoctorNewsType" class="control-label zf-name-label">资讯分类</label>
                <select  id="modifyDoctorNewsType">
                 <option value="">请选择咨询分类</option>
	                <option value="1">学术知识</option>
	                <option value="2">新闻资讯</option>
                </select>
                </div>
                
                
                  <div class="hospital-name">
                 <label for="modifyDoctorType" class="control-label zf-name-label">内容分类</label>
                <select  id="modifyDoctorType">
                 <option value="">请选择内容分类</option>
	                <option value="0">图片</option>
	                <option value="1">视频</option>
	                <option value="2">PDF</option>
                </select>
                </div>
                
             <div class="form-group">
                <label for="modifyDoctorNewsSource" class="control-label zf-name-label">资讯来源</label>
                <input type="text" class="form-control zf-layer-input" id="modifyDoctorNewsSource" placeholder="请输入资讯来源">
            </div>  
            
            <div class="form-group">
                <label for="modifyDoctorNewsContent" class="control-label zf-name-label">内容</label>
                <input type="text" class="form-control zf-layer-input" id="modifyDoctorNewsContent" placeholder="请输入内容URL">
            </div>   
            
              <div class="form-group">
                <label for="modifyDoctorNewsCreateTime" class="control-label zf-name-label">发布时间</label>
                <input type="text" class="form-control zf-layer-input" id="modifyDoctorNewsCreateTime" onClick="WdatePicker()" placeholder="请输入录入时间">
            </div>   
            
        </form>
    </div>
     
     
    
</div>
<#macro html_foot>
    <@p.js src="base/admindoctornews.js"/>	
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>