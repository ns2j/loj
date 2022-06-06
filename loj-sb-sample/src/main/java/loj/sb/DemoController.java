package loj.sb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.uno.Exception;
import com.sun.star.uno.XComponentContext;

import loj.my.Desktop;
import loj.my.Doc;
import loj.tempuserdir.TempUserDirConnector;

@RestController
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    ProxyFactoryBean factory;
    
    @Autowired
    IndexService indexService;
    @Autowired
    ChartService chartService;
    
    void setResponse(HttpServletResponse response, Doc doc) throws com.sun.star.io.IOException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment; filename=\"LojSbSample.xlsx\"");
        //myNewDoc.writeToStream(response.getOutputStream(), "MS Excel 97");
        //myNewDoc.writeToStream(response.getOutputStream(), "Calc MS Excel 2007 XML");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        doc.writeToStream(baos, "Calc MS Excel 2007 XML");
        response.getOutputStream().write(baos.toByteArray());
        response.setContentLength(baos.size());
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public void index(HttpServletResponse response) throws java.lang.Exception {
        //TempUserDirConnector connector = (TempUserDirConnector) source.getTarget();
        TempUserDirConnector connector = (TempUserDirConnector)factory.getObject();
        logger.info("connector.key: " + connector.key);
        XComponentContext context = connector.connect();
        Desktop desktop = new Desktop(context);
        //Util.writeType("proxy.properties", desktop, "desktop");
        try (Doc origDoc = desktop.getDocFromResource("original.ods");
                Doc newDoc = desktop.getNewDoc();) {
            indexService.make(origDoc, newDoc);
            setResponse(response, newDoc);
        }
    }

    @RequestMapping(value="/chart", method=RequestMethod.GET)
    public void chart(HttpServletResponse response) throws IllegalArgumentException, IOException, BootstrapException, Exception {
        TempUserDirConnector connector = (TempUserDirConnector)factory.getObject();
        logger.info("connector.key: " + connector.key);
        XComponentContext context = connector.connect();
        Desktop desktop = new Desktop(context);
        try (Doc doc = desktop.getNewDoc();) {
            chartService.make(doc);
            setResponse(response, doc);
        }
    }


}