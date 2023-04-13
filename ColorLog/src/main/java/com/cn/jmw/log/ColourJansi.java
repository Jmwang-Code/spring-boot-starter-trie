package com.cn.jmw.log;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月13日 15:31
 * @Version 1.0
 */
public class ColourJansi {
    public static Ansi formatStr(String content, Ansi.Color colour) {
        return Ansi.ansi().fg(colour).a(content);
    }
    public static void output(String content, Ansi.Color colour) {
        AnsiConsole.systemInstall();
        System.out.println(formatStr(content, colour));
        AnsiConsole.systemUninstall();
    }
    public static void main(String[] args) {
        System.out.println("控制台颜色测试：");
        output("红色", Ansi.Color.RED);
        output("黄色", Ansi.Color.YELLOW);
        output("蓝色", Ansi.Color.BLUE);
        output("绿色", Ansi.Color.GREEN);
        output("品红", Ansi.Color.MAGENTA);
        output("青色", Ansi.Color.CYAN);
    }
}
