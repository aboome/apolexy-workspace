//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if (element.is(":radio") || element.is(":checkbox")) {
            error.appendTo(element.parent().parent().parent());
        } else {
            error.appendTo(element.parent());
        }
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"

});

// 手机号码验证
jQuery.validator.addMethod("isMobile", function (value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

// 用户名验证
jQuery.validator.addMethod("isUserName", function (value, element) {
    var length = value.length;
    var userName = /^\w{4,16}$/;
    return this.optional(element) || (userName.test(value));
}, "请填写正确的用户名");

//密码
jQuery.validator.addMethod("isPassword", function (value, element) {
    var length = value.length;
    var password = /^\w{8,20}$/;
    return this.optional(element) || (password.test(value));
}, "密码必须是字母、数字和下划线组合");

jQuery.validator.addMethod("isTemperature", function (value, element) {
    var temp = /^(\+|-)?([0-9]{1,3})(\.[0-9])?$/;
    return this.optional(element) || (temp.test(value));
}, "请输入合适的值(-999.9~999.9,保留一位小数)");

jQuery.validator.addMethod("setTemperature", function (value, element) {
    var temp = /^((300)|((1|2)?([0-9]{1,2})(\.[0-9])?))$/;
    return this.optional(element) || (temp.test(value));
}, "请输入合适的值(0-300,保留一位小数)");

// 不相等
jQuery.validator.addMethod("notEqualTo", function (value, element, param) {
    return value != $(param).val();
}, $.validator.format("新密码不能和原密码相同"));

// 资源id
jQuery.validator.addMethod("isResourceId", function (value, element, param) {
    var resource = /^\w{32}$/;
    return this.optional(element) || (resource.test(value));
}, $.validator.format("请上传图片"));

//经纬度
jQuery.validator.addMethod("isLatLon", function (value, element, param) {
    var regex = /^(\+|\-)?[0-9]+(.[0-9]{1,16})?$/;
    return this.optional(element) || (regex.test(value));
}, $.validator.format("请输入正确的经纬度值"));

$().ready(function () {

    var icon = "<i class='fa fa-times-circle'></i> ";

    $("#loginForm").validate({
        rules: {
            username: {
                required: true,
                rangelength: [4, 16]
            },
            password: {
                required: true,
                rangelength: [8, 20]
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
                rangelength: icon + "用户名是长度为 {0} 至 {1} 之间的字符串"
            },
            password: {
                required: icon + "请输入您的密码",
                rangelength: icon + "密码是长度为 {0} 至 {1} 之间的字符串"
            }
        }
    });


    $('#modifyPasswordForm').validate({
        rules: {
            oldPassword: {
                required: true,
                rangelength: [8, 20],
                isPassword: true
            },
            newPassword: {
                required: true,
                rangelength: [8, 20],
                isPassword: true,
                notEqualTo: "#oldPassword"
            },
            repeatPassword: {
                required: true,
                rangelength: [8, 20],
                equalTo: "#newPassword"
            }
        },
        messages: {
            oldPassword: {
                rangelength: '密码长度为 {0} 到 {1} 个字符之间'
            },
            newPassword: {
                rangelength: '密码长度为 {0} 到 {1} 个字符之间',
                notEqualTo: '新密码不能和原密码相同'
            },
            repeatPassword: {
                rangelength: '密码长度为 {0} 到 {1} 个字符之间',
                equalTo: '请保证两次输入的密码相同'
            }
        }
    });

    $('#doctorInfo').validate({
        rules: {
            addDoctorName: {
                required: true,
                maxlength: 10
            },
            addDepartment: {
                maxlength: 20
            },
            addPhone: {
                required: true,
                isMobile: true
            },
            addSex: {
                required: true
            },
            addTitle: {
                maxlength: 10
            },
            addHospital: {
                required: true
            },
            addEffectiveTime: {
                dateISO: true
            },
            addJob: {
                maxlength: 10
            },
            addEmail: {
                maxlength: 64,
                email: true
            }
        },
        messages: {
            addDoctorName: {
                required: "医生名字为必填项",
                maxlength: '输入内容过长'
            },
            addDepartment: {
                maxlength: '输入内容过长'
            },
            addPhone: {
                required: '请输入电话号码',
                isMobile: '请输入正确格式的电话'
            },
            addSex: {
                required: '请选择性别'
            },
            addTitle: {
                maxlength: '输入内容过长'
            },
            addHospital: {
                required: '请选择所在医院'
            },
            addEffectiveTime: {
                dateISO: '请输入有效格式的有效期'
            },
            addJob: {
                maxlength: '输入内容过长'
            },
            addEmail: {
                maxlength: '输入内容过长',
                email: '请输入正确格式的email'
            }
        }
    });

    $('#modifyDoctor').validate({
        rules: {
            modifyDoctorName: {
                required: true,
                maxlength: 10
            },
            modifyDepartment: {
                maxlength: 20
            },
            modifyPhone: {
                required: true,
                isMobile: true
            },
            modifyTitle: {
                maxlength: 10
            },
            modifyHospital: {
                required: true
            },
            modifyEffectiveTime: {
                dateISO: true
            },
            modifyJob: {
                maxlength: 10
            },
            modifyEmail: {
                maxlength: 64,
                email: true
            }


        },
        messages: {
            modifyDoctorName: {
                required: "医生名字为必填项",
                maxlength: '输入内容过长'
            },
            modifyDepartment: {
                maxlength: '输入内容过长'
            },
            modifyPhone: {
                required: '请输入电话号码',
                isMobile: '请输入正确格式的电话'
            },
            modifyTitle: {
                maxlength: '输入内容过长'
            },
            modifyHospital: {
                required: '请输入所在医院'
            },
            modifyEffectiveTime: {
                dateISO: '请输入有效形式的时间'
            },
            modifyJob: {
                maxlength: '输入内容过长'
            },
            modifyEmail: {
                maxlength: '输入内容过长',
                email: '请输入正确格式的email'
            }
        }
    });

    $('#landingpageInfo').validate({
        rules: {
            addSort: {
                required: true,
                digits: true
            },
            addOwner: {
                required: true
            }
        },
        messages: {
            addSort: {
                required: "排序必须填写",
                digits: "排序必须填写数字"
            },
            addOwner: {
                required: "客户端类型必须填写"
            }
        }
    });

    $('#addBannerInfo').validate({
        rules: {
            addBannerInfoTitle: {
                maxlength: 20
            },
            bannerInfoImage:{
                required: true
            },
            addSort: {
                required: true,
                number: true
            },
            addOwner: {
                required: true
            },
            addUrl: {
                maxlength: 64,
                url: true
            }
        },
        messages: {
            addBannerInfoTitle: {
                maxlength: '标题字符串长度必须小于{0}'
            },
            bannerInfoImage:{
                required: '请选择图片'
            },
            addSort: {
                required: '请输入排序数字',
                number: '排序请输入数字'
            },
            addOwner: {
                required: '请选择客户端类型'
            },
            addUrl: {
                maxlength: '链接地址字符串长度必须小于{0}',
                url: '请输入有效的链接地址'
            }
        }
    });

    $('#modifyBannerInfo').validate({
        rules: {
            modifyBannerInfoTitle: {
                maxlength: 20
            },
            modifySort: {
                required: true,
                number: true
            },
            modifyOwner: {
                required: true
            },
            modifyUrl: {
                maxlength: 64,
                url: true
            }
        },
        messages: {
            modifyBannerInfoTitle: {
                maxlength: '标题字符串长度必须小于{0}'
            },
            modifySort: {
                required: '请输入排序数字',
                number: '排序请输入数字'
            },
            modifyOwner: {
                required: '请选择客户端类型'
            },
            modifyUrl: {
                maxlength: '链接地址字符串长度必须小于{0}',
                url: '请输入有效的链接地址'
            }
        }
    });


    $('#addAdmin').validate({
        rules: {
            addAdminName: {
                required: true,
                isUserName: true
            },
            addPassword: {
                required: true,
                isPassword: true
            },
            addPhone: {
                isMobile: true
            },
            addEmail: {
                maxlength: 64,
                email: true
            },
            addJob: {
                maxlength: 20
            },
            addDesc: {
                maxlength: 40
            },
            addRealName: {
                maxlength: 10
            }
        },
        messages: {
            addAdminName: {
                required: '请输入用户名',
                isUserName: '用户名为字母、数字、符号的组合4-16位'
            },
            addPassword: {
                required: '请输入密码',
                isPassword: "密码必须是字母、数字和下划线组合8-20位"
            },
            addPhone: {
                isMobile: '请输入电话号码'
            },
            addEmail: {
                maxlength: '输入内容过长',
                email: '请输入正确的email地址'
            },
            addJob: {
                maxlength: '输入内容过长'
            },
            addDesc: {
                maxlength: '输入内容过长'
            },
            addRealName: {
                maxlength: '输入内容过长'
            }
        }
    });
    $('#modifyAdmin').validate({
        rules: {
            modifyAdminName: {
                required: true,
                isUserName: true
            },
            modifyPhone: {
                isMobile: true
            },
            modifyEmail: {
                maxlength: 64,
                email: true
            },
            modifyJob: {
                maxlength: 20
            },
            modifyDesc: {
                maxlength: 40
            },
            modifyRealName: {
                maxlength: 10
            }
        },
        messages: {
            modifyAdminName: {
                required: '请输入用户名',
                isUserName: '用户名为字母、数字、符号的组合4-16位'
            },
            modifyPhone: {
                isMobile: '请输入电话号码'
            },
            modifyEmail: {
                maxlength: '输入内容过长',
                email: '请输入正确形式的email地址'
            },
            modifyJob: {
                maxlength: '输入内容过长'
            },
            modifyDesc: {
                maxlength: '输入内容过长'
            },
            modifyRealName: {
                maxlength: '输入内容过长'
            }
        }
    });

    $('#resetPassword').validate({
        rules: {
            newPassword: {
                required: true,
                rangelength: [8, 20],
                isPassword: true
            },
            repeatPassword: {
                required: true,
                rangelength: [8, 20],
                equalTo: "#newPassword"
            }
        },
        messages: {
            newPassword: {
                required: '请输入新密码',
                rangelength: '密码长度为 {0} 到 {1} 个字符之间',
                isPassword: "密码必须是字母、数字和下划线组合"
            },
            repeatPassword: {
                required: '请输入确认密码',
                rangelength: '密码长度为 {0} 到 {1} 个字符之间',
                equalTo: '请保证两次输入的密码相同'
            }
        }
    });
    $('#addHospitalInfo').validate({
        rules: {
            addHospitalName: {
                required: true,
                maxlength: 64
            },
            addHospitalAddr: {
                maxlength: 128
            },
            addLevel: {
                required: true,
                minlength: 1
            },
            addHospitalLon: {
                isLatLon: true
            },
            addHospitalLat: {
                isLatLon: true
            },
            addUnion: {
                required: true,
                minlength: 1
            }
        },
        messages: {
            addHospitalName: {
                required: "医院名称为必填项",
                maxlength: '输入内容过长'
            },
            addHospitalAddr: {
                maxlength: '输入内容过长'
            },
            addLevel: {
                required: "医院等级为必选项",
                minlength: "医院等级为必选项"
            },
            addHospitalLon: {
                isLatLon: "请输入正确的经度"
            },
            addHospitalLat: {
                isLatLon: "请输入正确的纬度"
            },
            addUnion: {
                required: "请选择医院是否为联盟医院",
                minlength: "请选择医院是否为联盟医院"
            }
        }
    });

    $('#modifyHospitalInfo').validate({
        rules: {
            modifyHospitalName: {
                required: true,
                maxlength: 64
            },
            modifyHospitalAddr: {
                maxlength: 128
            },
            modifyLevel: {
                required: true,
                minlength: 1
            },
            modifyHospitalLon: {
                isLatLon: true
            },
            modifyHospitalLat: {
                isLatLon: true
            },
            modifyUnion: {
                required: true,
                minlength: 1
            }
        },
        messages: {
            modifyHospitalName: {
                required: "医院名称为必填项",
                maxlength: '输入内容过长'
            },
            modifyHospitalAddr: {
                maxlength: '输入内容过长'
            },
            modifyLevel: {
                required: "医院等级为必选项",
                minlength: "医院等级为必选项"
            },
            modifyHospitalLon: {
                isLatLon: "请输入正确的经度"
            },
            modifyHospitalLat: {
                isLatLon: "请输入正确的纬度"
            },
            modifyUnion: {
                required: "请选择医院是否为联盟医院",
                minlength: "请选择医院是否为联盟医院"
            }
        }
    });

    $('#roleInfo').validate({
        rules: {
            addRoleName: {
                required: true,
                maxlength: 20
            },
            addDesc: {
                maxlength: 40
            }
        },
        messages: {
            addRoleName: {
                required: '角色名称不能为空',
                maxlength: '长度必须小于20'
            },
            addDesc: {
                maxlength: '长度必须小于64'
            }
        }
    });

    $('#roleModifyInfo').validate({
        rules: {
            modifyRoleName: {
                required: true,
                maxlength: 20
            },
            modifyDesc: {
                maxlength: 40
            }
        },
        messages: {
            modifyRoleName: {
                required: '角色名称不能为空',
                maxlength: '长度必须小于20'
            },
            modifyDesc: {
                maxlength: '长度必须小于64'
            }
        }
    });

    $('#addMenuForm').validate({
        rules: {
            addMenuName: {
                required: true,
                maxlength: 40
            },
            addMenuUrl: {
                required: true,
                maxlength: 128
            },
            addMenuSort: {
                required: true,
                digits: true
            },
            addMenuLevel: {
                required: true
            },
            addParentMenu: {
                required: true
            }
        },
        messages: {
            addMenuName: {
                required: '菜单名称不能为空',
                maxlength: '菜单名称字符串长度必须小于{0}'
            },
            addMenuUrl: {
                required: '菜单URL不能为空',
                maxlength: '菜单URL字符串长度必须小于{0}'
            },
            addMenuSort: {
                required: '菜单排序不能为空',
                digits: '只能填写数字'
            },
            addMenuLevel: {
                required: '请选择菜单等级'
            },
            addParentMenu: {
                required: '请选择父级菜单'
            }
        }
    });

    $('#modifyMenuForm').validate({
        rules: {
            modifyMnuName: {
                required: true,
                maxlength: 40
            },
            modifyMenuUrl: {
                required: true,
                maxlength: 128
            },
            modifySort: {
                required: true,
                digits: true
            },
            modifyParentMenu: {
                required: true
            }
        },
        messages: {
            modifyMennuName: {
                required: '菜单名称不能为空',
                maxlength: '菜单名称字符串长度必须小于{0}'
            },
            modifyMennuUrl: {
                required: '菜单URL不能为空',
                maxlength: '菜单URL字符串长度必须小于{0}'
            },
            modifySort: {
                required: '菜单排序不能为空',
                digits: '只能填写数字'
            },
            modifyParentMenu: {
                required: '请选择父级菜单'
            }
        }
    });

    $("#addRoleForm").validate({
        rules: {
            addRoleName: {
                required: true,
                maxlength: 64
            },
            addDesc: {
                maxlength: 128
            }
        },
        messages: {
            addRoleName: {
                required: '角色名称不能为空',
                maxlength: '角色名称字符串长度必须小于{0}'
            },
            addDesc: {
                maxlength: '角色备注字符串长度必须小于{0}'
            }
        }
    });

    $("#modifyRoleForm").validate({
        rules: {
            modifyRoleName: {
                required: true,
                maxlength: 64
            },
            modifyDesc: {
                maxlength: 128
            }
        },
        messages: {
            modifyRoleName: {
                required: '角色名称不能为空',
                maxlength: '角色名称字符串长度必须小于{0}'
            },
            modifyDesc: {
                maxlength: '角色备注字符串长度必须小于{0}'
            }
        }
    });

    $("#versionForm").validate({
        rules: {
            modifyVersion: {
                required: true,
                maxlength: 32
            },
            modifyURL: {
                required:true,
                url:true,
                maxlength: 128
            }
        },
        messages: {
            modifyVersion: {
                required: '版本信息不能为空',
                maxlength: '版本信息字符串长度必须小于{0}'
            },
            modifyURL: {
                required:'升级链接不能为空',
                url:'请输入有效的网址',
                maxlength: '升级链接字符串长度必须小于{0}'
            }
        }
    });

    $("#addDoctorNewsForm").validate({
        rules: {
            addDoctorNewsTitle: {
                required: true,
                maxlength: 64
            },
            addDoctorSubTitle: {
                required:true,
                maxlength: 128
            },
            addDoctorNewsSource:{
                required:true,
                maxlength: 64
            },
            addDoctorType:{
                required: true,
                minlength: 1
            },
            addDoctorNewsType: {
                required: true,
                minlength: 1
            },
            newsImage: {
                required: true
            },
            addDoctorSrc:{
                required: true,
                minlength: 1
            }

        },
        messages: {
            addDoctorNewsTitle: {
                required: '标题不能为空',
                maxlength: '标题字符串长度必须小于{0}'
            },
            addDoctorSubTitle: {
                required: '摘要不能为空',
                maxlength: '摘要字符串长度必须小于{0}'
            },
            addDoctorNewsSource:{
                required: '资源信息不能为空',
                maxlength: '资源字符串长度必须小于{0}'
            },
            addDoctorType:{
                required: '请选择内容分类',
                minlength: '请选择内容分类'
            },
            addDoctorNewsType:{
                required: '请选择资讯分类',
                minlength: '请选择资讯分类'
            },
            newsImage: {
                required: '请选择需要上传的缩略图'
            },
            addDoctorSrc:{
                required: '请选择资源分类',
                minlength: '请选择资源分类'
            }

        }
    });

    $("#modifyDoctorNewsForm").validate({
        rules: {
            modifyDoctorNewsTitle: {
                required: true,
                maxlength: 64
            },
            modifyDoctorSubTitle: {
                required:true,
                maxlength: 128
            },
            modifyDoctorNewsSource:{
                required:true,
                maxlength: 64
            },
            modifyDoctorType:{
                required: true,
                minlength: 1
            },
            modifyDoctorNewsType: {
                required: true,
                minlength: 1
            },
            modifyDoctorSrc:{
                required: true,
                minlength: 1
            }
        },
        messages: {
            modifyDoctorNewsTitle: {
                required: '标题不能为空',
                maxlength: '标题字符串长度必须小于{0}'
            },
            modifyDoctorSubTitle: {
                required: '摘要不能为空',
                maxlength: '摘要字符串长度必须小于{0}'
            },
            modifyDoctorNewsSource:{
                required: '资源信息不能为空',
                maxlength: '资源信息字符串长度必须小于{0}'
            },
            modifyDoctorType:{
                required: '请选择内容分类',
                minlength: '请选择内容分类'
            },
            modifyDoctorNewsType:{
                required: '请选择资讯分类',
                minlength: '请选择资讯分类'
            },
            modifyDoctorSrc:{
                required: '请选择资源分类',
                minlength: '请选择资源分类'
            }

        }
    });

    $("#addPatientNewsForm").validate({
        rules: {
            addPatientNewsTitle: {
                required: true,
                maxlength: 64
            },
            addPatientSubTitle: {
                required:true,
                maxlength: 128
            },
            addPatientNewsSource:{
                required:true,
                maxlength: 64
            },
            addPatientType:{
                required: true,
                minlength: 1
            },
            newsImage: {
                required: true
            },
            addPatientSrc:{
                required: true,
                minlength: 1
            }

        },
        messages: {
            addPatientNewsTitle: {
                required: '标题不能为空',
                maxlength: '标题字符串长度必须小于{0}'
            },
            addPatientSubTitle: {
                required: '摘要不能为空',
                maxlength: '摘要字符串长度必须小于{0}'
            },
            addPatientNewsSource:{
                required: '资源信息不能为空',
                maxlength: '资源字符串长度必须小于{0}'
            },
            addPatientType:{
                required: '请选择内容分类',
                minlength: '请选择内容分类'
            },
            newsImage: {
                required: '请选择需要上传的缩略图'
            },
            addPatientSrc:{
                required: '请选择资源分类',
                minlength: '请选择资源分类'
            }

        }
    });

});
