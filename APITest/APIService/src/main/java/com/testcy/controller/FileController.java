package com.testcy.controller;

import com.testcy.bean.Token;
import com.testcy.mapper.TokenMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
@Api(description = "文件上传和下载接口")
public class FileController {

    @Autowired
    private TokenMapper tokenMapper;

    @ApiOperation(value = "文件上传接口",httpMethod = "POST")
    @PostMapping("/file/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("userName") String userName,
                             @RequestPart("token") String token,
                             @RequestPart MultipartFile file) {
        if (file.isEmpty() || userName == null || token == null) {
            return "参数不合法！";
        }
        try {
            file.transferTo(new File("D:\\IdeaProjects\\HTTPAPITest\\APITest\\upload\\" + file.getOriginalFilename()));
            Token token1 = new Token();
            token1.setToken(token);
            token1.setUserName(userName);
            tokenMapper.insert(token1);
        } catch (IOException e) {
            log.error("目标文件或路径不存在！");
            e.printStackTrace();
        }

        return "上传成功！";
    }

    //文件下载
    @GetMapping("/file/download")
    @ResponseBody
    @ApiOperation(value = "文件下载接口",httpMethod = "GET")
    public String download(HttpServletRequest request, HttpServletResponse response) {
        //文件下载相关代码
        String fileName = "devops.jpg";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "D:\\IdeaProjects\\HTTPAPITest\\APITest\\upload\\";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = 0;
                    while ((i = bis.read(buffer)) != -1) {
                        os.write(buffer, 0, i);
                    }
                    System.out.println("success");
                    return "下载成功！";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "下载失败！";
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载成功";
    }
}