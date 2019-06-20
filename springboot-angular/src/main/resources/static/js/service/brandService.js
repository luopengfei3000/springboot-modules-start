//服务层
app.service('brandService', function ($http) {
    //读取列表数据绑定到表单中
    this.findAll = function () {
        return $http.get('/ordertwo/findAll');
    }
    //分页
    this.findPage = function (page, rows, searchEntity) {
        return $http.post('/ordertwo/operation/getOrderByPage?page=' + page + '&rows=' + rows, searchEntity);
    }
    //查询实体
    this.findOne = function (id) {
        return $http.get('/ordertwo/operation/findById?id=' + id);
    }
    //保存
    this.save = function (entity) {
        return $http({
            url: '/ordertwo/operation/save',
            method: 'POST',
            data: entity
        });
    }
    //删除
    this.dele = function (ids) {
        return $http({
            url: '/ordertwo/operation/delete',
            method: 'POST',
            data: ids
        });
    }

});
