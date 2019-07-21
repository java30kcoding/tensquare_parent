package cn.itlou.controller;

import cn.itlou.pojo.Label;
import cn.itlou.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @GetMapping(value = "/{labelId}")
    public Result findById(@PathVariable String labelId){
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping(value = "/{labelId}")
    public Result update(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping(value = "/{labelId}")
    public Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

//    @PostMapping(value = "/search")
//    public Result findSearch(@RequestBody Label label){
//        List<Label> labelList = labelService.findByLabel(label);
//        return new Result(true, StatusCode.OK, "查询成功", labelList);
//    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size){
        Page<Label> pageLabel = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageLabel.getTotalElements(), pageLabel.getContent()));
    }

    @GetMapping(value = "/toplist/{page}/{size}")
    public Result toplist(@PathVariable int page, @PathVariable int size){
        Page<Label> hotlist = labelService.toplist(page, size);
        return new Result(true,StatusCode.OK,"查询成功", new PageResult<Label>(hotlist.getTotalElements(), hotlist.getContent()));
    }

    @GetMapping(value = "/toplist")
    public Result toplist1(){
        return new Result(true,StatusCode.OK,"查询成功", labelService.findAll());
    }

}
