package com.welove.service;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welove.common.Result;
import com.welove.dao.FileWallDao;
import com.welove.entity.FileWall;
import com.welove.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FileWallService extends ServiceImpl<FileWallDao, FileWall> {

    @Autowired
    private FileWallDao fileWallDao;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    public IPage<FileWall> findPage(Integer pageNum,
                                    Integer pageSize,
                                    String filename) {

        IPage<FileWall> page = new Page<>(pageNum, pageSize);

        QueryWrapper<FileWall> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        IPage<FileWall> filePage = this.page(page, queryWrapper);
        return filePage;
    }

    public Result getByUid(Integer userId) {
        QueryWrapper<FileWall> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        List<FileWall> list = this.list(query);
        return Result.success(list);
    }

    public FileWall getById(String id) {
        QueryWrapper<FileWall> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        FileWall one = getOne(queryWrapper);
        return one;
    }

    public Result deleteById(Integer id) {
        FileWall file = new FileWall();
        file.setFileId(id);
        return Result.success(this.removeById(file));
    }

    public Result deleteByIds(List<Integer> ids) {
        QueryWrapper<FileWall> qw = new QueryWrapper<>();
        qw.in("file_id", ids);
        List<FileWall> files = fileWallDao.selectList(qw);
        for (FileWall file : files) {
            this.removeById(file);
        }
        return Result.success();
    }

    public FileWall  upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        //创建父目录
        File temp = new File(fileUploadPath);
        if (!temp.exists()) {
            temp.mkdirs();
        }
        //文件的唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        String uuidExt = uuid + StrUtil.DOT + type;

        //  D:\temp\xxxxxx.txt
        File uploadFile = new File(fileUploadPath + uuidExt);

        FileWall selectOne=null;
        String url;
        String md5 = null;
        if (!uploadFile.exists()){
            file.transferTo(uploadFile);
        }

        // check if md5 already exists
        md5 = SecureUtil.md5(uploadFile);
        selectOne = getFileByMd5(md5);
        if (selectOne != null) {
            url = selectOne.getFileUrl();
            FileUtil.del(uploadFile);
        }else{
            //把获取到的文件存到本地 ( use md5 to identify redundant files, keep only one, which means this step can be skipped)
            url = "http://localhost:9090/file_wall/get/" + uuidExt;
        }

        // save to db
        FileWall fileUploaded = new FileWall();
        fileUploaded.setMd5(md5);

        fileUploaded.setFileUrl(url);

        return fileUploaded;
    }

    public String uploadFileWall(MultipartFile file,String userId) throws IOException{
        FileWall fileWall = this.upload(file);
        fileWall.setUserId(Integer.parseInt(userId) );
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = formatter.format(new Date());
        fileWall.setCreateTime(curTime);
        fileWallDao.insert(fileWall);
        return fileWall.getFileUrl();
    }

    private FileWall getFileByMd5(String md5){
        QueryWrapper<FileWall> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<FileWall> list = fileWallDao.selectList(queryWrapper);
        return list.size()==0? null:list.get(0);
    }
}
