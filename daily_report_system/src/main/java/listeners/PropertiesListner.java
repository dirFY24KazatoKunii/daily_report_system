package listeners;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PropertiesListner implements ServletContextListener {

    public PropertiesListner() {
    }

    /**
     * Webアプリケーションのシャットダウン時に実行する処理
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    /**
     * Webアプリケーションの起動時に実行する処理
     */
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext context = arg0.getServletContext();

        //プロパティファイルを読み込み、アプリケーションスコープに設定する
        try {
            InputStream is = PropertiesListner.class.getClassLoader().getResourceAsStream("application.properties");

            Properties properties = new Properties();
            properties.load(is);
            is.close();

            Iterator<String> pit = properties.stringPropertyNames().iterator();
            while (pit.hasNext()) {
                String pname = pit.next();
                context.setAttribute(pname, properties.getProperty(pname));

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}