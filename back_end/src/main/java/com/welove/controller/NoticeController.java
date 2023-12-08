package com.welove.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.welove.common.Result;
import com.welove.entity.Notice;
import com.welove.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String searchContent) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("send_time");
        queryWrapper.like("notice_content", searchContent);
        Page<Notice> page = noticeService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Notice notice) {
        boolean b = noticeService.saveOrUpdate(notice);
        if (b) {
            return Result.success("保存成功");
        }
        return Result.fail("保存失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean b = noticeService.removeById(id);
        if (b) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        boolean b = noticeService.removeByIds(ids);
        if (b) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @GetMapping("all")
    public Result findAll() {
        return Result.success(noticeService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(noticeService.getById(id));
    }


}
