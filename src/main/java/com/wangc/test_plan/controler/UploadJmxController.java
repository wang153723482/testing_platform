package com.wangc.test_plan.controler;

import com.wangc.comm.Param;
import com.wangc.comm.StringUtils;
import com.wangc.test_plan.bean.TestPlanBean;
import com.wangc.test_plan.jmeter.Tools;
import com.wangc.test_plan.service.TPService;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by wangchao on 2017/4/25.
 */

@Controller
@RequestMapping(value = "/tp")
public class UploadJmxController {
    private static final Logger logger = LoggerFactory.getLogger(UploadJmxController.class);
    private final String SUCCESS = "上传成功";
    private final String FAILURE_NOT_FOUND = "上传失败(FileNotFoundException)";
    private final String FAILURE_IO = "上传失败(IOException)";
    private final String FAILURE_FILE_EMPTY = "上传失败(File is empty)";

    @Autowired
    TPService tpService;

    /*跳转到上传页面*/
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "";
    }

    /*上传*/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(Model model, @RequestParam MultipartFile file,
                         @RequestParam String tpName, @ModelAttribute TestPlanBean tp) {
        // TODO: wangc@2017/6/6  关于路径的部分，太混乱！！！ 
        String msg = "";
        String dir = StringUtils.creAndGetDir(Param.USER_DIR+Param.JMX_PATH);//动态生成的路径 /yyyy/MM
        String tmpFileName = "tp_" + StringUtils.getDate("yyyyMMddHHmmss");
        String filePath = Param.JMX_PATH+dir +File.separator + tmpFileName + file.getOriginalFilename();//相对路径 
        String fileAllPath = Param.USER_DIR + filePath; //完整路径
        
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(fileAllPath)));
                out.write(file.getBytes());
                out.flush();
                out.close();

                msg = SUCCESS;
                logger.info(SUCCESS);

                tp.setJmxSavePath(filePath);
                try {
                    tp.setCsvDataXpath(Tools.getCsvDataXPath(fileAllPath) );
                } catch (DocumentException e) {
                    e.printStackTrace(); // TODO: wangc@2017/8/2  处理异常 
                }
                tpService.insert(tp);

                /// todo 解析 脚本，获取参数,保存至数据库，或传递给下一个页面

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                msg = FAILURE_NOT_FOUND;
                logger.error(FAILURE_NOT_FOUND);
            } catch (IOException e) {
                e.printStackTrace();
                msg = FAILURE_IO;
                logger.error(FAILURE_IO);
            }
        } else {
            msg = FAILURE_FILE_EMPTY;
            logger.error(FAILURE_FILE_EMPTY);
        }
        model.addAttribute("msg", msg);
        return "redirect:/tp/list";
    }

}
