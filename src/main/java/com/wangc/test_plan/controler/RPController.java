package com.wangc.test_plan.controler;

import com.wangc.comm.Param;
import com.wangc.comm.StringUtils;
import com.wangc.test_plan.bean.TestPlanBean;
import com.wangc.test_plan.bean.RunPlanBean;
import com.wangc.test_plan.jmeter.RunJmx;
import com.wangc.test_plan.jmeter.Tools;
import com.wangc.test_plan.service.RPService;
import com.wangc.test_plan.service.TPService;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by wangchao on 2017/3/10.
 */

@Controller
@RequestMapping("/rp")
public class RPController {

    @Autowired
    RPService rpService;
    @Autowired
    TPService tpService;

    @Value("${tp.jmeter.jmx.path}")
    String a;

    private static final Logger logger = LoggerFactory.getLogger(RPController.class);
    private final String SUCCESS = "上传成功";
    private final String FAILURE_NOT_FOUND = "上传失败(FileNotFoundException)";
    private final String FAILURE_IO = "上传失败(IOException)";
    private final String FAILURE_FILE_EMPTY = "上传失败(File is empty)";


    /*
    * 添加一个执行计划。
    * 先判断该执行计划中是否已经生成了jmx，如果生成则执行 runJmx() ，否则，先生成jmx，然后再 runJmx()
    * */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute RunPlanBean rpb,@RequestParam MultipartFile file) {
        
        TestPlanBean tpb = tpService.selectById(rpb.getTpId());// TODO: wangc@2017/8/17  异常是查不到数据，未处理 
        rpb.setTestPlanBean(tpb);
        rpb.setJmxPath(tpb.getJmxSavePath());
                                                               
        if(null!=file && !file.isEmpty()){
            String dataFilePath = saveFile(file);
            rpb.setDataPath(dataFilePath);
            try {
                Tools.updateJmx(Param.USER_DIR+tpb.getJmxSavePath(),Param.USER_DIR+rpb.getDataPath(),tpb.getCsvDataXpath());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        
        RunJmx.run(rpb);

        rpService.insert(rpb);

        return "redirect:/tp/list";
    }

    /**
     * 根据test_plan id 查询当前计划的所有执行日志
     * @param tpId test_plan.id
     * @return
     */
    @RequestMapping(value = "report_list", method = RequestMethod.GET)
    public String report_list(Model model, @RequestParam String tpId) {
        List<RunPlanBean> list = rpService.list(tpId);
        model.addAttribute("rp_list", list);
//        model.addAttribute("html_path",list.get(0).getHtmlPath()); //未处理空异常
        return "run_plan/report_list";
    }
    
    /*
    * 根据test_plan id 查询并显示当前正在执行的jmeter.log
    * */
    @RequestMapping(value = "runlog_list", method = RequestMethod.GET)
    public String runlogList(Model model, @RequestParam String tpId,@RequestParam(defaultValue = "0") long lastModified) {
        List<RunPlanBean> list = rpService.list(tpId);
        String logPath = list.get(0).getLogPath();//todo  未处理异常
        model.addAttribute("log_result",rpService.runlogList(logPath,lastModified));
        return "run_plan/run_log";
    }

    //todo 移到工具类
    public String saveFile(MultipartFile file) {
        String typePath = Param.DATA_PATH;
        String dir = StringUtils.creAndGetDir(Param.USER_DIR+typePath);//动态生成的路径 /yyyy/MM
        String tmpFileName = StringUtils.getDate("yyyyMMddHHmmss");
        String filePath = typePath+dir + File.separator + tmpFileName + file.getOriginalFilename();//相对路径 
        String fileAllPath = Param.USER_DIR + filePath; //完整路径

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(fileAllPath)));
                out.write(file.getBytes());
                out.flush();
                out.close();
                logger.info(SUCCESS);
                return filePath;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.error(FAILURE_NOT_FOUND);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(FAILURE_IO);
            }
        } else {
            logger.error(FAILURE_FILE_EMPTY);
        }
        return null;//todo 异常处理
    }


}
