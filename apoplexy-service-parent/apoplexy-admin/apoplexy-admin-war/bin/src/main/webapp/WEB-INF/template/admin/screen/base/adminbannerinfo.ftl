<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>手机端主页推荐位管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
              
         
                

            </div>
        </form>
    </div>
    <div class="table-content">
        <div class="add-and-import">
            <button type="button" class="btn add" id="add"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>
       
        </div>

        <div id="admindoctorbannerinfotable" class="hospital-table">
            
        </div>
        
        <div id="adminpatientbannerinfotable" class="hospital-table">
            
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
    <div id="deleteDoctorLayer" style="display:none">
        <ul class="input-group">
            <label>您确定删除此条医生端主页推荐位信息吗？</label>
        </ul>
    </div>
</div>
 <!-- 弹出层 -->
    <div id="deletePatientLayer" style="display:none">
        <ul class="input-group">
            <label>您确定删除此条患者端主页推荐位信息吗？</label>
        </ul>
    </div>
</div>
<#macro html_foot>
    <@p.js src="base/adminbannerinfo.js"/>	
</#macro>