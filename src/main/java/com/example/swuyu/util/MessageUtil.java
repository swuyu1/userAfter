package com.example.swuyu.util;

import com.apistd.uni.Uni;
import com.apistd.uni.UniException;
import com.apistd.uni.UniResponse;
import com.apistd.uni.sms.UniMessage;
import com.apistd.uni.sms.UniSMS;

import java.util.Map;

public class MessageUtil {
    public static String ACCESS_KEY_ID = "macjV1qiUNfARzLd8MMSEL8BWXbnGb8D9gJA2Yw23i3F3V7vS";
    private static String ACCESS_KEY_SECRET = "un7ggnbbyMkzNJAnWtdpDNBBhbSjE2";

    /**
     * @param phone        手机号
     * @param templateId   "pub_verif_ttl3" 短信模板 ID 或自定义模板码
     * @param templateData 模板变量
     * @param Content      短信正文文本 （和templateId二选一）
     * @return 成功返回TRUE 失败返回失败信息
     */
    public static Object sendMessage(String phone, String templateId, Map<String, String> templateData, String Content) {
        // 初始化
        Uni.init(ACCESS_KEY_ID, ACCESS_KEY_SECRET); // 若使用简易验签模式仅传入第一个参数即可
        // 构建信息
        UniMessage message = UniSMS.buildMessage()
                .setTo(phone)
                .setSignature("拾物语")
                .setContent(Content)
                .setTemplateId(templateId)
                .setTemplateData(templateData);
        // 发送短信
        try {
            UniResponse res = message.send();
            return true;
        } catch (UniException e) {
            return e;
        }
    }
}
