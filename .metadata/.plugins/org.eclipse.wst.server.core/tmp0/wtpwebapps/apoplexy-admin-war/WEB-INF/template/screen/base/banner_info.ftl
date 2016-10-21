<#include "/layout/baseLayout.ftl">
<#macro html_title>手机端主页推荐位管理</#macro>
<#macro html_body>
<div class="hospital-wrap" style="padding: 15px 21px;">

    <div class="table-content" style="padding-top: 0;">
        <div class="add-and-import">
            <button type="button" class="btn add" id="add" onclick="addBannerInfo()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>

        </div>

        <div id="doctorBannerTable" class="hospital-table">

        </div>

        <div id="patientBannerTable" class="hospital-table">

        </div>
    </div>


    <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" id="addBannerInfo" role="form">
            <div class="form-group">
                <label for="addBannerInfoTitle" class="control-label col-xs-3">标题</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="addBannerInfoTitle" name="addBannerInfoTitle"
                           placeholder="请输入标题">
                </div>
            </div>

            <div class="form-group">
                <label for="addBannerInfoImage" class="control-label zf-name-label col-xs-3">图片</label>
                <div class="col-xs-9">
                    <input type="file" name="bannerInfoImage" id="bannerInfoImage" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="addSort" class="control-label zf-name-label col-xs-3">排序</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control hospital-name-input" id="addSort" name="addSort"
                           placeholder="请输入排序">
                </div>
            </div>

            <div class="form-group">
                <label for="addOwner" class="control-label col-xs-3">客户端类型</label>
                <div class="col-xs-9">
                    <select class="form-control" id="addOwner" name="addOwner">
                        <option value="">请选择客户端类型</option>
                        <option value="1">医生</option>
                        <option value="2">患者</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="addUrl" class="control-label col-xs-3">链接</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="addUrl" name="addUrl" placeholder="请输入链接">
                </div>
            </div>
        </form>
    </div>


    <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="modifyBannerInfo">
            <div class="form-group">
                <label for="modifyBannerInfoTitle" class="control-label col-xs-3">标题</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="modifyBannerInfoTitle" name="modifyBannerInfoTitle"
                           placeholder="请输入标题">
                </div>
            </div>

            <div class="form-group">
                <label for="modifyBannerInfoImage" class="control-label col-xs-3">图片</label>
                <div class="col-xs-9">
                    <input type="file" name="modifyImage" id="modifyImage" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="modifyUrl" class="control-label col-xs-3">链接</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="modifyUrl" name="modifyUrl" placeholder="请输入链接">
                </div>
            </div>

            <div class="form-group">
                <label for="modifySort" class="control-label col-xs-3">排序</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="modifySort" name="modifySort" placeholder="请输入排序">
                </div>
            </div>

            <div class="form-group">
                <label for="modifyOwner" class="control-label col-xs-3">客户端类型</label>
                <div class="col-xs-9">
                    <select class="form-control" id="modifyOwner" name="modifyOwner">
                        <option value="">请选择客户端类型</option>
                        <option value="1">医生</option>
                        <option value="2">患者</option>
                    </select>
                </div>
            </div>
        </form>
    </div>

    <!-- 弹出层 -->
    <div id="deleteLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo modal-body">您确定要删除该条记录吗？</p>
    </div>

    <!-- 弹出层 -->
    <div id="detailLayer" style="display:none" class="zf-modal">
        <div class="modal-body">
            <img id="bannerDetail" src=""/>
        </div>
    </div>

</div>
</#macro>

<#macro html_foot>
    <@p.js src="base/banner.js"/>
    <@p.js src="third/ajaxfileupload.js"/>
</#macro>