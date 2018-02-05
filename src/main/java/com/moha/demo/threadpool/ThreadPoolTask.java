package com.moha.demo.threadpool;


import com.moha.demo.entity.CheckEmailValidator;
import com.moha.demo.util.DateTimeUtil;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maozewei on 2017/11/28.
 */
public class ThreadPoolTask implements Callable<CheckEmailValidator>, Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTask.class);
    private static final String EMAILSUFFIX = "verify-email.org";
    private static final String EMAILPREFIX = "check";
    private SMTPClient client = new SMTPClient();
    private final ReentrantLock mainLock = new ReentrantLock();

    // 保存任务所需要的数据
    private String email;

    private CheckEmailValidator ce;


    public ThreadPoolTask(String tasks) {
        this.email = tasks;
    }

    @Override
    public CheckEmailValidator call() throws Exception {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        // 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
        LOGGER.error(email+"***-begin****");
        ce =  new CheckEmailValidator();
        ce.setEmail(email);
        if (!email.matches("^(?![_.])\\w+([-+.&#39;]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            ce = checkEmailValidatorView(ce, 0, "Email false format");
            LOGGER.error(email+"-Email false format");
            return ce;
        }
        String host = "";
        String hostName = email.split("@")[1];

        Record[] result = null;
        client.setConnectTimeout(8000);

        try {
            result = recordMX(hostName);
            if (result == null) {
                ce = checkEmailValidatorView(ce, 0, "Not found MX records");
                LOGGER.error(email+"-Not found MX records");
                return ce;
            }

            int count = 0;
            //尝试和SMTP邮箱服务器建立Socket连接
            for (int i = 0; i < result.length; i++) {
                host = result[i].getAdditionalName().toString();
                try {
                    client.connect(host);   //连接到接收邮箱地址的邮箱服务器
                } catch (Exception e) {        //捕捉连接超时的抛出的异常
                    count++;
                    if (count >= result.length) {    //如果由MX得到的result服务器都连接不上，则认定email无效
                        ce = checkEmailValidatorView(ce, 0, "Connect mail server timeout");
                        LOGGER.error(email+"-Connect mail server timeout");
                        return ce;
                    }
                }
                //所有以2开头的响应码都是正常的响应
                if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                    //断开socket连接
                    client.disconnect();
                    ce = checkEmailValidatorView(ce, 0, "ReplyCode beginning not with a 2");
                    LOGGER.error(email+"-ReplyCode beginning not with a 2");
                    return ce;
                } else {
                    //找到MX记录---建立链接成功
                    break;
                }
            }

            String fromEmail = EMAILPREFIX + "@" + EMAILSUFFIX;

            //尝试和SMTP服务器建立连接,发送一条消息给SMTP服务器
            client.login(EMAILPREFIX);

            //设置发送者，在设置接受者之前必须要先设置发送者
            client.setSender(fromEmail);

            //设置接收者,在设置接受者必须先设置发送者，否则SMTP服务器会拒绝你的命令
            client.addRecipient(email);
            //(250表示正常)
            if (250 == client.getReplyCode()) {
                LOGGER.error(email+"-ReplyCode {}",client.getReplyCode());
                ce = checkEmailValidatorView(ce, 1, client.getReplyString());
                return ce;
            } else {
                LOGGER.error(email+"-ReplyCode {}",client.getReplyCode());
                ce = checkEmailValidatorView(ce, 0, client.getReplyString());
                return ce;
            }

        } catch (Exception e) {
            LOGGER.error(email + "-Exception error:", e);
            ce = checkEmailValidatorView(ce, 0, "email Invalid");
            return ce;
        } finally {
            try {
                mainLock.unlock();
                client.disconnect();
                //System.out.println("任务完成：" + ce.getEmail());
                LOGGER.error(email+"***-end****");
                email = null;
                ce = null;
            } catch (IOException e) {
                LOGGER.warn(email + "-IOException error:", e);
            }
        }
//        ce = CheckEmailValidatorUtil.check(email);
//        LOGGER.info("ThreadPoolTask ce {}", ce.getEmail());
//        return ce;
    }

    private static Record[] recordMX(String hostName) throws TextParseException {
        // 查找DNS缓存服务器上为MX类型的缓存域名信息
        Lookup lookup = new Lookup(hostName, Type.MX);
        lookup.run();
        if (lookup.getResult() != Lookup.SUCCESSFUL) {//查找失败
            //System.err.println("邮箱（" + email + "）校验未通过，未找到对应的MX记录!");
            return null;
        } else {//查找成功
            return lookup.getAnswers();
        }
    }

    private static CheckEmailValidator checkEmailValidatorView(CheckEmailValidator ce, int status, String replyString){
        ce.setReplyString(replyString);
        ce.setStatus(status);
        ce.setCreateTime(DateTimeUtil.getCurrentDatetime("yyyy-MM-dd HH:mm:ss"));
        return ce;
    }
}
