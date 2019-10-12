import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.*;
/**
 * NormalizationTest class
 * 需要进行检查，防止注入漏洞
 * @author wangxiaochen
 * @date 2019/09/11
 */
public class NormalizationTest {
    public static void main(String[] args) {
        // 包含全角尖括号
        String sbcCase = "\uFe64" + ";reboot;" + "\uFe65";
        // 包含半角尖括号
        String dbcCase = "\u003C" + ";reboot;" + "\u003E";

        System.out.println("包含全角尖括号的输入字符串：" + sbcCase + "\n包含半角尖括号的输入字符串：" + dbcCase);
        // 普通方法无法判断是否包含全角尖括号
        System.out.println("字符串\"" + sbcCase + "\"中是否包含全角尖括号：" + sbcCase.contains("＜"));

        // 归一化前，无法正确区分全角和半角尖括号，出错校验遗漏，系统受到破坏，服务被重启
        System.out.println("-----------------------------");
        System.out.println("归一化前：" + sbcCase);
        checkInputString(sbcCase);

        // 归一化后，就可正确校验，系统免受破坏
        System.out.println("-----------------------------");
        String normalized = Normalizer.normalize(sbcCase, Normalizer.Form.NFKC);
        System.out.println("归一化后：" + normalized);
        checkInputString(normalized);
    }

    private static void checkInputString(String str) {
        Pattern pattern = Pattern.compile("<\\s*;reboot\\s*;>");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("命令注入失败");
            return;
        }
        System.out.println("命令注入成功，服务器即将重启");

    }
}
