package com.wangc.test_plan.controler;

import com.wangc.Application;
import com.wangc.comm.Param;
import com.wangc.comm.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by wangchao on 2017/4/25.
 * 
 */

@Controller
@RequestMapping(value = "/tp")
public class UploadJmxController {
    private static final Logger logger = LoggerFactory.getLogger(UploadJmxController.class);
    private final String SUCCESS = "上传成功";
    private final String FAILURE_NOT_FOUND = "上传失败(FileNotFoundException)";
    private final String FAILURE_IO = "上传失败(IOException)";
    private final String FAILURE_FILE_EMPTY = "上传失败(File is empty)";
    
    /*跳转到上传页面*/
    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        
        return "";
    }

    /*上传*/
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(Model model, @RequestParam MultipartFile file){
        String msg ="";
        String filePath = Param.USER_DIR+Param.UPLOAD_JMX_PATH;
        boolean creDirFlag = StringUtils.creDir(filePath);
        String tmpFileName = "tp_"+StringUtils.getDate("yyyyMMddHHmmss");
        String fileAllPath = filePath+File.separator+tmpFileName+file.getOriginalFilename(); //完整路径
        if(!creDirFlag){
            logger.error("cre dir failure");
            return "";
        } else if(!file.isEmpty()){
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(
                                new File( fileAllPath )));
                out.write(file.getBytes());
                out.flush();
                out.close();

                msg = SUCCESS; 
                logger.info(SUCCESS);
                
                /// 解析 脚本，获取参数，传递给下一个页面
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                msg = FAILURE_NOT_FOUND;
                logger.error(FAILURE_NOT_FOUND);
            } catch (IOException e) {
                e.printStackTrace();
                msg = FAILURE_IO;
                logger.error(FAILURE_IO);
            }
        }else{
            msg = FAILURE_FILE_EMPTY;
            logger.error( FAILURE_FILE_EMPTY );
        }
        model.addAttribute("msg",msg);
        return "/test_plan/upload";
    }
    
    
    
}
