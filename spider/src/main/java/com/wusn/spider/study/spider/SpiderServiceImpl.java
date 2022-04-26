package com.wusn.spider.study.spider;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SpiderServiceImpl implements SpiderService{

    @Value("${spider.webdriver.type}")
    private String webDriverType;

    @Value("${spider.cookie.uid}")
    private String cookieUid;

    @Value("${spider.cookie.tmzw}")
    private String cookieTmzw;

    @Value("${spider.cookie.zwfigprt}")
    private String cookieZwfigprt;

    @Value("${spider.cookie.bl.uid}")
    private String cookieBlUid;

    @Value("${spider.cookie.token}")
    private String cookieToken;

    private WebDriver driver;

    @Override
    @PostConstruct
    public void start() {
        // 1.获取浏览器驱动
        getWebDriver();
        if (driver != null) {
            // 2.登录学习强国
            Login();
//            driver.quit();
        }
    }

    private void getWebDriver() {
        switch (webDriverType) {
            case "Chrome":
            case "chrome":
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Edge":
            case "edge":
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                driver = null;
        }
    }

    private void Login() {
        driver.manage().window().maximize();
        driver.get("https://www.xuexi.cn/");
        driver.manage().addCookie(new Cookie("__UID__", cookieUid));
        driver.manage().addCookie(new Cookie("tmzw", cookieTmzw));
        driver.manage().addCookie(new Cookie("zwfigprt", cookieZwfigprt));
        driver.manage().addCookie(new Cookie("_bl_uid", cookieBlUid));
        driver.manage().addCookie(new Cookie("token", cookieToken));
        driver.get("https://www.xuexi.cn/");
    }

}
