package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.entity.TrieQueryResult;
import com.cn.jmw.trie.tokenizer.TokenizerManager;
import com.cn.jmw.trie.tokenizer.TokenizerObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月07日 17:36
 * @Version 1.0
 */
public class TrieQuerierTest {

    static TrieNode trieNode = new TrieNode();

    {
        trieNode.add(TokenizerManager.getIntArray("南京"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南宁"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天门"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天南"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天"), MultiCodeMode.Replace, 1, 0);
    }

    /**
     * @Param []
     * @return void
     * @exception
     * @Date 2023/2/20 10:12
     * 常数项-查询-前缀
     * 1-2ms
     */
    @Test
    public void constantItemQueryPrefix(){
        long l = System.currentTimeMillis();
        TrieQuerier trieQuerier = new TrieQuerier(trieNode, new TokenizerObject("阿达的南天门发放"), true);

        TrieQueryResult query = trieQuerier.query();
        String word = query.getWord();
        System.out.println(word);
        long end = System.currentTimeMillis() - l;
        System.out.println(end);
    }

    /**
     * @Param []
     * @return void
     * @exception
     * @Date 2023/2/20 10:15
     * 千万级别-N-项查询前缀
     * 1-2ms
     */
    @Test
    public void tenMillionNQueryPrefixes() {
        for (int i = 0; i < 10000000; i++) {
            int[] arr = new int[1 + (int) (Math.random() * 10)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = 19969 + (int) (Math.random() * 20000);
            }
            trieNode.add(arr, MultiCodeMode.Replace, 1, 0);
        }

        long l = System.currentTimeMillis();
        TrieQuerier trieQuerier = new TrieQuerier(trieNode, new TokenizerObject("阿达的南天门发放"), true);
        int deep = trieQuerier.getDeep();
        System.out.println("deep:"+deep);
        TrieQueryResult query = trieQuerier.query();
        String word = query.getWord();
        System.out.println(word);
        long end = System.currentTimeMillis() - l;
        System.out.println(end);
    }

    @Test
    public void querier() {
//        TrieNode trieNode1 = trieNode.get(TokenizerManager.getIntLocal("南"));
//        trieNode1.print();

        TrieQuerier trieQuerier = new TrieQuerier(trieNode, new TokenizerObject("阿达的南天门发放"), true);
        trieNode.print();
        int deep = trieQuerier.getDeep();
        System.out.println("deep:"+deep);
        TrieQueryResult query = trieQuerier.query();
        String word = query.getWord();
        TrieCode[] codes = query.getCodes();
        System.out.println(word);
        int offset = query.getOffset();
//        System.out.println(codes);
        System.out.println(offset);

    }





//    WebDriver driver = new ChromeDriver();    //Chrome浏览器
//    WebDriver driver = new FirefoxDriver();   //Firefox浏览器
//    WebDriver driver = new EdgeDriver();      //Edge浏览器
//    WebDriver driver = new InternetExplorerDriver();  // Internet Explorer浏览器
//    WebDriver driver = new OperaDriver();     //Opera浏览器
//    WebDriver driver = new PhantomJSDriver();   //PhantomJS
    @Test
    public void one() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("no-sandbox");
        options.addArguments("disable-gpu");
        options.addArguments("disable-features=NetworkService");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("silent-launch");
        options.addArguments("disable-application-cache");
        options.addArguments("disable-web-security");
        options.addArguments("no-proxy-server");
        options.addArguments("disable-dev-shm-usage");
        options.addArguments("window-size=2048,1536");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "http://152.136.154.249:4444/wd/hub");
        WebDriver webDriver = new ChromeDriver(options);

        webDriver.get("https://www.baidu.com/");

        ExpectedCondition<WebElement> ConditionOfSign = ExpectedConditions.presenceOfElementLocated(By.id("headlessBrowserRenderSign"));
        ExpectedCondition<WebElement> ConditionOfWidth = ExpectedConditions.presenceOfElementLocated(By.id("width"));
        ExpectedCondition<WebElement> ConditionOfHeight = ExpectedConditions.presenceOfElementLocated(By.id("height"));

        Double contentWidth = Double.parseDouble(webDriver.findElement(By.id("width")).getAttribute("value"));

        Double contentHeight = Double.parseDouble(webDriver.findElement(By.id("height")).getAttribute("value"));

        if (500>0 && 500 != contentWidth) {
            // scale the window
            webDriver.manage().window().setSize(new Dimension(500, contentHeight.intValue()));
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // scale the window again
        contentWidth = Double.parseDouble(webDriver.findElement(By.id("width")).getAttribute("value"));
        contentWidth = contentWidth>0 ? contentWidth : 1920;
        contentHeight = Double.parseDouble(webDriver.findElement(By.id("height")).getAttribute("value"));
        contentHeight = contentHeight>0 ? contentHeight : 600;
        webDriver.manage().window().setSize(new Dimension(contentWidth.intValue(), contentHeight.intValue()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        TakesScreenshot screenshot = (TakesScreenshot) webDriver;
        System.out.println();
    }

}