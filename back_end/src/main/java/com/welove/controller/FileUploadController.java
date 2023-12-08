package com.welove.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.welove.common.Result;
import com.welove.dao.FileWallDao;
import com.welove.entity.FileWall;
import com.welove.service.FileWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file_wall")
public class FileUploadController {


    //拿到配置文件里的属性
    @Value("${files.upload.path}")
    private String fileUploadPath;   //D:\temp

    @Resource
    private FileWallDao fileUploadDao;

    @Autowired
    private FileWallService fileUploadService;

    /**
     * 前端传来文件，后端保存
     *
     * @param file
     * @return
     * @throws IOException
     */
    //文件上传接口 可以不用Result包装，前端一般拿到地址直接用
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, @RequestParam String userId) throws IOException {
        return fileUploadService.uploadFileWall(file, userId);
    }

    /**
     * 文件下载接口  http://localhost:9090/file_wall/get/{xxxxx.txt};
     *
     * @param uuidExt
     * @param response
     * @throws IOException
     */
    @GetMapping("/get/{uuidExt}")
    public void download(@PathVariable String uuidExt, HttpServletResponse response) throws IOException {
        File uploadFile = new File(fileUploadPath + uuidExt);
        // set format
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(uuidExt, "UTF-8"));
        response.setContentType("application/octet-stream");

        outputStream.write(FileUtil.readBytes(uploadFile));
        outputStream.flush();
        outputStream.close();

    }


    @RequestMapping("/list")
    public List<FileWall> listAllFiles() {
        return fileUploadService.list();
    }

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable String id) {
        return Result.success(fileUploadService.getById(id));
    }

    @GetMapping("/getByUserId/{userId}")
    public Result getByUserId(@PathVariable("userId") String userId) {
        return fileUploadService.getByUid(Integer.parseInt(userId));
    }

    @RequestMapping("/page")
    public IPage<FileWall> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String filename  //不加default 如果输入框都是“”，则会报错
    ) {
        return fileUploadService.findPage(pageNum, pageSize, filename);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        // {xx} 和 方法参数的 xx 名字要一模一样
        return fileUploadService.deleteById(id);
    }

    @PostMapping("/delete/batch")
    public Result deleteByIds(@RequestBody List<Integer> ids) {
        // {xx} 和 方法参数的 xx 名字要一模一样
        return fileUploadService.deleteByIds(ids);
    }

    @PostMapping("/changeEnable")
    public Result insertOrUpdate(@RequestBody FileWall fileUploaded) {
        return Result.success(fileUploadService.updateById(fileUploaded));
    }
}
