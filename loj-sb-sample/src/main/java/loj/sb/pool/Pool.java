
package loj.sb.pool;

import java.net.MalformedURLException;

import org.apache.commons.pool2.PooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.sun.star.comp.helper.BootstrapException;

import loj.tempuserdir.TempUserDirConnector;
import loj.tempuserdir.TempUserDirServer;
import loj.util.LoUtil;

@Configuration
public class Pool {
    private static Logger logger = LoggerFactory.getLogger(Pool.class); 
    /*
    class DisposableConnector extends TempUserDirConnector implements DisposableBean {
        public DisposableConnector(TempUserDirServer server) {
            super(server);
        }
        @Override
        public void destroy() {
            disconnect();
        }
    }
    */
    @Bean(name="tempUserDirConnector")
    @Scope("prototype")
    public TempUserDirConnector tempUserDirConnector() throws MalformedURLException, BootstrapException {
//        return new DisposableConnector(new TempUserDirServer(LoUtil.getExecFolder()));
        return new TempUserDirConnector(new TempUserDirServer(LoUtil.getExecFolder()));
    }

    @Bean
    public CommonsPool2TargetSource targetSource() {
        CommonsPool2TargetSource targetSource = new CommonsPool2TargetSource() {
            /*
            void raiseExcepion() throws Exception {
                if (true) throw new Exception("test");
            }*/
            @Override
            public void destroyObject(PooledObject<Object> p) throws Exception {
                logger.info("destroyObject");
                /*
                try {
                    raiseExcepion();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                var tudc = (TempUserDirConnector)p.getObject();
                if (tudc.key == null) return;
                new Thread(() -> {
                    try {
                        //Thread.sleep(getMinEvictableIdleTimeMillis());
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tudc.disconnect();
                    tudc.key = null;
                }).start();
            }
        };
        targetSource.setTargetBeanName("tempUserDirConnector");
        targetSource.setTargetClass(TempUserDirConnector.class);
        targetSource.setMaxIdle(2);
        //targetSource.setMinIdle(2);
        //targetSource.setTimeBetweenEvictionRunsMillis(180000);
        //targetSource.setMinEvictableIdleTimeMillis(100000);
        return targetSource;
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBean() {
        ProxyFactoryBean p = new ProxyFactoryBean();
        p.setTargetSource(targetSource());
        return p;
    }
}
