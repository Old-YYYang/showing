$(function () {
    var type = {
        MENU:'菜单',
        FUNCTION:'功能',
        BLOCK:'模块'
    };
    var permissionGrid = $('#permissionGrid');
    permissionGrid.treegrid({
        border: false,
        fit: true,
        iconCls: 'fa fa-plus',
        url: '/system/permission/list',
        idField: 'id',
        treeField: 'name',
        columns: [[
            /*{field:'id', title:'编号', width: 90},*/
            {field:'name', title:'名称', width: 120},
            {field:'type', title:'类型', width: 110, align:'center', formatter: function (val) {
                    return type[val];
                }},
            {field:'resource', title:'资源', width: 150},
            {field:'path', title:'路径', width: 180},
            {field:'weight', title:'权重', align:'center', width: 90},
            {field:'permissionKey', title:'标识', width: 90},
            {field:'description', title:'描述', width: 200},
            {field:'enable', title:'状态', align:'center', width: 90, formatter: function (val) {
                    return val?"启用":"禁用"
                }
            },
            {field:'action', title:'操作', width: 100, align:'center', formatter: function (val, row) {
                var btns = [];
                btns.push('<a data-id="'+row.id+'" class="actions fa-pencil-square-o edit">编辑</a>');
                btns.push('<a data-id="'+row.id+'" class="actions fa-trash-o delete">删除</a>');
                return btns.join('');
                }
            }
        ]],
        toolbar: [
            {
                text: '添加权限',
                handler: function () {
                    formDialog();
                }
            }
        ]
    });

    var gridPanel = permissionGrid.treegrid('getPanel');
    gridPanel.on('click', 'a.edit', function () {
        var rid = this.dataset.id;
        formDialog(rid);
    }).on('click', 'a.delete', function () {
        var rid = this.dataset.id;
        $.messager.confirm('提示', '是否删除？', function (r) {
            if (r) {
                $.get('/system/permission/delete?id='+rid).success(function (data) {
                    permissionGrid.treegrid('reload');
                });
            }
        })
    });
    
    function formDialog(rid) {
        var dialog = $('<div/>').dialog({
            title: (rid?'编辑':'添加')+'权限',
            width: 500,
            height: 480,
            modal: true,
            href: rid?'/system/permission/load?id='+rid:'/system/permission/form',
            buttons: [
                {
                    text: '确定',
                    width: 56,
                    handler: function () {
                        var permissionForm = $('#permissionForm');
                        if (permissionForm.form('validate')) {
                            $.post('/system/permission/'+(rid?'update':'save'), permissionForm.serialize(), function (result) {

                            }).success(function () {
                                permissionGrid.treegrid('reload');
                                dialog.dialog('close');
                            })
                        }
                    }
                }
            ],
            onClose: function () {
                $(this).dialog('destroy');
            }
        });
    };
});

