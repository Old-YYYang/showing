$(function () {
    var roleGrid = $('#roleGrid');
    roleGrid.treegrid({
        border: false,
        fit: true,
        url: '',
        idField: 'id',
        treeField: 'name',
        columns: [[
            {field:'id', title:'编号', width: 90},
            {field:'name', title:'名称', width: 90},
            {field:'description', title:'描述', width: 90},
            {field:'weight', title:'权重', width: 90},
            {field:'enable', title:'状态', width: 90},
            {field:'resource', title:'资源', width: 90},
            {field:'type', title:'类型', width: 90},
            {field:'path', title:'路径', width: 90},
            {field:'permissionKey', title:'标识', width: 90}
        ]]
    });
});