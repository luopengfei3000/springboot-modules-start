//控制层
app.controller('brandController', function ($scope, $controller, brandService) {

    $controller('baseController', {$scope: $scope});//继承

    //查询列表数据
    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页查询数据
    $scope.findPage = function (page, rows) {
        brandService.findPage(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    /*$scope.save = function () {
        $http.post('/ordertwo/operation/save', $scope.entity).success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }*/

    //保存
    $scope.save = function () {
        brandService.save($scope.entity).then(function (r) {
            if (r.data.success) {
                //重新查询
                $scope.reloadList();//重新加载
            } else {
                alert(r.message);
            }
        });
    }

    //查询实体
    $scope.findOne = function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //批量删除(get提交方式)
    /*$scope.dele=function(){
        //获取选中的复选框
        $http.get('/ordertwo/operation/delete?ids='+$scope.selectIds).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }*/

    //批量删除(post提交方式)
    $scope.dele = function () {
        brandService.dele($scope.selectIds).then(function (r) {
            if (r.data.success) {
                //重新查询
                $scope.reloadList();//重新加载
            } else {
                alert(r.message);
            }
        });
    }

});
