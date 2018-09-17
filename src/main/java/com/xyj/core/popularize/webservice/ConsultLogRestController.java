package com.xyj.core.popularize.webservice;

import com.xyj.core.popularize.consts.ConsultStatus;
import com.xyj.core.popularize.consts.ConsultType;
import com.xyj.core.popularize.entity.ConsultLog;
import com.xyj.core.popularize.service.ConsultLogService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/1.0/consult/log")
public class ConsultLogRestController{

    @Autowired
    private ConsultLogService consultLogService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(@RequestBody ConsultLog log){
        consultLogService.save(log);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed update(@RequestBody ConsultLog log){
        consultLogService.update(log);

        return new Responsed("更新成功");
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void export(HttpServletRequest request, HttpServletResponse response, String url, ConsultStatus status,
                       ConsultType type, Date minCreateTime, Date maxCreateTime) throws Exception{

        XSSFWorkbook book = consultLogService.export(url, status, type, minCreateTime, maxCreateTime);

        String fileName = "咨询记录.xlsx";

        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        OutputStream outputStream = response.getOutputStream();
        BufferedOutputStream buffered = new BufferedOutputStream(outputStream);
        buffered.flush();

        book.write(buffered);
        buffered.close();
    }
}
