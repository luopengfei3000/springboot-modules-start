package com.example.springbootswagger.controller;

import com.example.springbootswagger.dto.Brand;
import com.example.springbootswagger.dto.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "BrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/test")
public class BrandController {
    List<Brand> list = new ArrayList<>();
    {
        Brand b1 = new Brand();
        b1.setId(1l);
        b1.setFirstLetter("A");
        b1.setFirstLetter("小米");
        b1.setProductCount(10);
        Brand b2 = new Brand();
        b2.setId(1l);
        b2.setFirstLetter("B");
        b2.setFirstLetter("华为");
        b2.setProductCount(20);
        list.add(b1);
        list.add(b2);
    }

    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Brand> getBrandList() {
        return list;
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody Brand result) {
        CommonResult commonResult;
        commonResult = CommonResult.success(result);
        return commonResult;
    }


    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Brand> deleteBrand(@PathVariable("id") long id) {
        list.remove(id);
        return list;
    }

}
