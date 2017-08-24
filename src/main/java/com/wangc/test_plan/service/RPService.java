package com.wangc.test_plan.service;

import com.wangc.comm.Param;
import com.wangc.test_plan.bean.LogResultBean;
import com.wangc.test_plan.bean.RunPlanBean;
import com.wangc.test_plan.mapper.RPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Created by wangchao on 2017/3/10.
 */


@Service
public class RPService {
    
    @Autowired
    RPMapper rpMapper;
    
    public void insert(RunPlanBean rpb){
        rpMapper.insertSelective(rpb);
    }
    
    public List<RunPlanBean> list(String tId){
        RunPlanBean rpb = new RunPlanBean();
        rpb.setTpId(tId);
        return rpMapper.select(rpb);
    }
    
    public static void main(String[] args){
        RPService rps = new RPService();
        String p = "D:\\workspace_HelloWorld\\testing_platform\\jmeter\\log\\2017\\07\\1500886505219_24165505219.log";
        long l = 0L;
        LogResultBean s = rps.runlogList(p,l);
        Long aa = s.getModifiedTime();
        System.out.println( s.getContent() );
        try{
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        
        System.out.println( rps.runlogList(p,aa).getContent() );
    }
    
    /*
    * @logPath 日志文件的路径
    * @oldLastModified 上次读取日志时的文件 modified 属性，要做对比用
    * */
    public LogResultBean runlogList(String logPath, long lastModified){
        
        String result = "";
        long modifiedTime = 0L;
        try {
            File file = new File(Param.USER_DIR+Param.LOG_PATH+logPath);
            modifiedTime = file.lastModified();
            if (modifiedTime != lastModified) { // 文件内容有修改时再重新读取
                System.out.println("文件内容修改，重新加载。。。");
                result = readFile(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new LogResultBean(logPath,modifiedTime,result); //这里是result可能为空，因为文件没有发生变化
    }


    private String readFile(File file) {
        
        String  content = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String temp;
            while ((temp = reader.readLine()) != null) {
                if(temp.contains("o.a.j.r.Summariser")){   //todo 判断条件太草率
                    buffer.append(temp.replace("INFO o.a.j.r.Summariser","")).append("##@"); //##@ 代表换行，前端处理
                }
            }
            reader.close();
            content = buffer.toString();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.err.println("读取文件出错");
        }
        return content;
    }
    
}
