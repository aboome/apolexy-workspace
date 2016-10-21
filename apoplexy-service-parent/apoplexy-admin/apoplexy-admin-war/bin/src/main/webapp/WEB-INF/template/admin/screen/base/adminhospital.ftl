<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>医院数据管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="hospital-name" class="control-label hospital-name-label">医院名称：</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="admin-hospital-name"
                           placeholder="请输入医院名称">
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
            <button typee="button" class="btn add" id="add" onclick="addHospital()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>
            <button typee="button" class="btn import"><i class="glyphicon glyphicon-import"></i>导入</button>
        </div>

        <div id="adminhospitaltable" class="hospital-table">
            
        </div>
</div>
 <!-- 弹出层 -->
     <div id="deleteLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo">您确定要删除该条记录吗？</p>
    </div>
    
    
     <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="addHospitalName" class="control-label zf-name-label">医院名称</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addHospitalName"
                           placeholder="请输入医院名称">
                </div>
            </div>
            <div class="form-group">
                <label for="addHospitaldesc" class="control-label zf-name-label">医院简介</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addHospitaldesc"
                           placeholder="请输入医院简介">
                </div>
            </div>
            <div class="form-group">
                <label for="addHospitalAddr" class="control-label zf-name-label">医院地址</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addHospitalAddr"
                           placeholder="请输入医院地址">
                </div>
            </div>
          <div class="hospital-name">
                 <label for="addLevel" class="control-label zf-name-label">医院 </label>
                <select  id="addLevel">
                 <option value="">请选择医院级别</option>
	                <option value="0">省级</option>
	                <option value="1">市级</option>
	                 <option value="2">区县级</option>
	                  <option value="3">社区级</option>
                </select>
            </div>
            
            
            <div class="form-group">
                <label for="addimageId" class="control-label zf-name-label">图片</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addimageId"
                           placeholder="请输入图片URL">
                </div>
            </div>
   
             <div class="form-group">
                <label for="addparentHospitalName" class="control-label zf-name-label">父级医院名称</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addparentHospitalName"
                           placeholder="请输入父级医院名称">
                </div>
              </div>  
            
                  <div class="form-group">
                <label for="addHospitalLon" class="control-label zf-name-label">经度</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addHospitalLon"
                           placeholder="请输入经度">
                </div>
                </div>
                
              <div class="form-group">
                <label for="addHospitalLat" class="control-label zf-name-label">纬度</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addHospitalLat"
                           placeholder="请输入纬度">
                </div>
                </div>
                                
              <div class="form-group">
                <label for="addAreaCode" class="control-label zf-name-label">区域编码</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addAreaCode"
                           placeholder="请输入区域编码">
                </div>
                </div>
   
        </form>
    </div>
    
     <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="modifyHospitalName" class="control-label zf-name-label">医院名称</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyHospitalName"
                           placeholder="请输入医院名称">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyHospitaldesc" class="control-label zf-name-label">医院简介</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyHospitaldesc"
                           placeholder="请输入医院简介">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyHospitalAddr" class="control-label zf-name-label">科室</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyHospitalAddr"
                           placeholder="请输入医院地址">
                </div>
            </div>
            
                <div class="form-group">
                 <label for="modifyLevel" class="control-label zf-name-label">医院级别</label>
                <select  id="modifyLevel">
                 <option value="">请选择医院级别</option>
	                <option value="0">省级</option>
	                <option value="1">市级</option>
	                 <option value="2">区县级</option>
	                  <option value="3">社区级</option>
                </select>
                </div>
                
            <div class="form-group">
                <label for="modifyimageId" class="control-label zf-name-label">图片</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyimageId"
                           placeholder="请输入URL">
                </div>
            </div>
            
            <div class="form-group">
                <label for="modifyparentHospitalName" class="control-label zf-name-label">父级医院名称</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyparentHospitalName"
                           placeholder="请输入父级医院名称">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyHospitalLon" class="control-label zf-name-label">经度</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyHospitalLon"
                           placeholder="请输入经度">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyHospitalLat" class="control-label zf-name-label">纬度</label>
                <div class="zf-layer-input">
                <input type="text" class="form-control zf-name-input" id="modifyHospitalLat"
                           placeholder="请输入纬度">
                </div>
            </div>
            
                <div class="form-group">
                <label for="modifyAreaCode" class="control-label zf-name-label">区域邮编</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyAreaCode" 
                    placeholder="请输入区域邮编">
                </div>
            </div>
        </form>
    </div>  
    
</div>



</#macro>
<#macro html_foot>
    <@p.js src="base/adminhospital.js"/>	
</#macro>