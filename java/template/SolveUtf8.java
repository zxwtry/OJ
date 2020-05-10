package template;

public class SolveUtf8 {

    public static void main(String[] args) {
        String s = "2020/05/10 01:47:22 mall_material_if.go:50: [uin=825574677,seq=2637711948][DEBUG] get entrance template use local cache [entranceTemplateCache:[id:205159 name:'\\346\\234\\252\\346\\211\\223\\345\\215\\241-\\350\\277\\220\\350\\220\\245' state:1 template_text:'\\346\\273\\264\\357\\274\\201\\345\\246\\210\\345\\246\\210\\346\\230\\257\\350\\266\\205\\344\\272\\272\\345\\221\\212\\347\\231\\275\\345\\215\\241\\357\\274\\201' start_time:1589040000 end_time:1589126399 type:1 bytes_url:'https://ti.qq.com/signin/public/index.html?_wv=1090528161&_wwv=13' bytes_icon_url:'https://qzonestyle.gtimg.cn/qzone/space_item/material/QQPunchIn/org/7/205159/QQ20200510-0.png' bytes_icon_custom_url:'https://qzonestyle.gtimg.cn/qzone/space_item/material/QQPunchIn/org/7/205159/QQ20200510-0.png'  id:205158 name:'\\345\\267\\262\\346\\211\\223\\345\\215\\241-\\350\\277\\220\\350\\220\\245' state:4 template_text:'\\345\\267\\262\\346\\211\\223\\345\\215\\241%d\\345\\244\\251' start_time:1589040000 end_time:1589126399 type:1 bytes_url:'https://ti.qq.com/signin/public/index.html?_wv=1090528161&_wwv=13' bytes_icon_url:'https://qzonestyle.gtimg.cn/qzone/space_item/material/QQPunchIn/org/6/205158/QQ20200510-0.png' bytes_icon_custom_url:'https://qzonestyle.gtimg.cn/qzone/space_item/material/QQPunchIn/org/6/205158/QQ20200510-0.png'  id:202039 name:'\\345\\267\\262\\346\\211\\223\\345\\215\\241\\351\\273\\230\\350\\256\\244' state:4 template_text:'\\345\\267\\262\\346\\211\\223\\345\\215\\241%d\\345\\244\\251' start_time:1583337600 end_time:2147483647type:0 bytes_url:'https://ti.qq.com/signin/public/index.html?_wv=1090528161&_wwv=13' bytes_icon_url:'' bytes_icon_custom_url:''  id:205151 name:'\\346\\234\\252\\346\\211\\223\\345\\215\\241\\351\\273\\230\\350\\256\\244' state:1 template_text:'\\344\\273\\212\\345\\244\\251\\350\\277\\230\\346\\262\\241\\346\\211\\223\\345\\215\\241\\345\\223\\246' start_time:1583337600 end_time:2147483647 type:0 bytes_url:'https://ti.qq.com/signin/public/index.html?_wv=1090528161&_wwv=13' bytes_icon_url:'' bytes_icon_custom_url:'' ]]";

        System.out.println(transfromOctalToString(s));
    }

    public static String transfromOctalToString(String dataStr) {
        if (!dataStr.contains("\\")) {
            return dataStr;
        }
        // 不属于八进制内容的字符
        StringBuilder oldBuffer = new StringBuilder();
        // 属于八进制的内容，转成十六进制后缓存在这里
        StringBuilder hexBuffer = new StringBuilder();
        for (int i = 0; i < dataStr.length(); i++) {
            char c = dataStr.charAt(i);
            if (c != '\\') {
                oldBuffer.append(c);
            }
            // 反斜杠往后3个为一组，组成了一个八进制数。例如\\20710,其实是207组成了一个八进制数
            else {
                char c1 = dataStr.charAt(i + 1);
                char c2 = dataStr.charAt(i + 2);
                char c3 = dataStr.charAt(i + 3);
                i += 3;
                // 将八进制转换为十进e79fa5e98193e59b9ee7ad9431333366303162制，再转换为十六进制
                String hex = Integer.toHexString((Integer.valueOf("" + c1 + c2 + c3, 8)));
                // 先缓存住，直到凑够三个字节
                hexBuffer.append(hex);
                String hexString = hexBuffer.toString();
                // utf8编码中，三个字节为一个汉字
                if (hexString.length() == 6) {
                    // 凑够三个字节了，转成汉字后放入oldBuffer中
                    oldBuffer.append(hexStr2Str(hexString));
                    // 凑够一个汉字了，清空缓存
                    hexBuffer = new StringBuilder();
                }
            }
        }
        return oldBuffer.toString();
    }

    /**
     * 十六进制转换字符串
     */
    private static String hexStr2Str(String hexStr) {
        String str = "0123456789abcdef";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }
}
