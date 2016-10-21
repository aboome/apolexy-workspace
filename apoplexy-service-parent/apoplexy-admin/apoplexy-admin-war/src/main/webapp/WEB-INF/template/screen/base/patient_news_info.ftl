<#include "/layout/baseLayout.ftl">
<#macro html_title>患者端患教资料管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-inline" role="form">
            <div class="form-horizontal">
                <label for="admin-patientnews-name" class="control-label hospital-name-label">资讯标题:</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="admin-patientnews-name"
                           placeholder="请输入标题">
                </div>

                <label for="patientnews-content" class="control-label hospital-name-label">内容分类:</label>
                <div class="hospital-name">
                    <select id="patientnews-content" class="form-control">
                        <option value="">请选择资源分类</option>
                        <option value="1">图片</option>
                        <option value="2">视频</option>
                        <option value="3">PDF</option>
                    </select>
                </div>
            </div>

            <div class="search-btn row">
                <div class="col-xs-1">
                    <button type="button" class="btn btn-block search" id="search">查询</button>
                </div>
                <div class="col-xs-1">
                    <button type="button" class="btn btn-block clear" id="clear">清空</button>
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

        <div id="patientNewsTable" class="hospital-table">

        </div>
    </div>

</div>

<div id="deleteLayer" style="display:none" class="zf-modal">
    <p class="deleteInfo">您确定要删除该条记录吗？</p>
</div>

<!-- 弹出层 -->
<div id="imageLayer" style="display:none" class="zf-modal">
    <div class="modal-body">
        <img id="newsImage" src=""/>
    </div>
</div>

<div id="detailLayer" style="display:none" class="zf-modal">
    <iframe id= "newsContentFrame" width="800px" height="600px">
    </iframe>
    <div id="newsContentDiv">
    </div>
</div>

</#macro>
<#macro html_foot>
    <@p.js src="base/patient_news.js"/>
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>