package com.moha.demo.controller;

import com.moha.demo.entity.View;
import com.moha.demo.service.DemoService;
import com.moha.demo.util.LifeBean;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/*JSP页面多个同名数据传到controller*/
@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LifeBean lifeBean;

    @RequestMapping("show")
    public String demo(){
      /*  CheckEmailValidator cev = new CheckEmailValidator();
        cev.setEmail("799845210@qq.com");
        cev.setReplyString("111");
        cev.setStatus(1);
        cev.setCreateTime("2017");
        mongoTemplate.insert(cev,"CheckEmailValidator");
*/
        return "/index";
    }
    @RequestMapping("form")
    public String form() {
        List<String> list = Arrays.asList("test@gmail.com","miss023@gmail.com","dolphin0520@163.com","stardyun@yahoo.com.cn","799845210@qq.com","knight_xiaodai@outlook.com","1060941496@qq.com","emailss@gmail.com","ffafa@gmail.com");
        demoService.ser(list);
        return "/formSubmit";
    }

    @RequestMapping("data")
    @ResponseBody
    public String forms(String data){
        lifeBean.texts.add(String.valueOf(System.currentTimeMillis()));

        System.out.println(lifeBean.product().size());

        return "success";
    }

    @RequestMapping("data1")
    @ResponseBody
    public String forms1(String data){
        lifeBean.texts.add(String.valueOf(System.currentTimeMillis()));
        System.out.println(lifeBean.product().size());
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "fff",method = RequestMethod.POST)
    public int vs(@RequestBody List<View> views){
        for(View v : views){
            v.getId();
        }
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "fffs",method = RequestMethod.POST)
    public int vs(Integer[] id,String[] name){

        return 1;
    }
    @ResponseBody
    @RequestMapping(value = "fffss",method = RequestMethod.POST)
    public int vs(Integer[] id){

        return 1;
    }


    @ResponseBody
    @RequestMapping(value = "tms",method = RequestMethod.GET)
    public int vss(Integer[] id){
        demoService.ser(null);
        return 1;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, IOReactorException {
        //具体参数含义下文会讲
        //apache提供了ioReactor的参数配置，这里我们配置IO 线程为1
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(1).build();
        //根据这个配置创建一个ioReactor
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        //asyncHttpClient使用PoolingNHttpClientConnectionManager管理我们客户端连接
        PoolingNHttpClientConnectionManager conManager = new PoolingNHttpClientConnectionManager(ioReactor);
        //设置总共的连接的最大数量
        conManager.setMaxTotal(100);
        //设置每个路由的连接的最大数量
        conManager.setDefaultMaxPerRoute(100);
        //创建一个Client
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setConnectionManager(conManager).build();
        // Start the client
        httpclient.start();
        System.out.println(System.currentTimeMillis());
        // Execute request
        final HttpGet request1 = new HttpGet("http://www.apache.org/");
        Future<HttpResponse> future = httpclient.execute(request1, null);
        // and wait until a response is received
        HttpResponse response1 = future.get();
        System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
        System.out.println(System.currentTimeMillis());

        // One most likely would want to use a callback for operation result
        final HttpGet request2 = new HttpGet("http://www.apache.org/");
        httpclient.execute(request2, new FutureCallback<HttpResponse>() {
            //Complete成功后会回调这个方法
            public void completed(final HttpResponse response2) {
                System.out.println(request2.getRequestLine() + "->" + response2.getStatusLine());
            }

            public void failed(final Exception ex) {
                System.out.println(request2.getRequestLine() + "->" + ex);
            }

            public void cancelled() {
                System.out.println(request2.getRequestLine() + " cancelled");
            }

        });
        System.out.println(System.currentTimeMillis());
    }



    @RequestMapping(value="/asynctask", method = RequestMethod.GET)
    public DeferredResult<String> asyncTask() throws IOReactorException {
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(1).build();
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        PoolingNHttpClientConnectionManager conManager = new PoolingNHttpClientConnectionManager(ioReactor);
        conManager.setMaxTotal(100);
        conManager.setDefaultMaxPerRoute(100);
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setConnectionManager(conManager).build();
        // Start the client
        httpclient.start();
        //设置超时时间200ms
        System.out.println("1"+System.currentTimeMillis());
        final DeferredResult<String> deferredResult = new DeferredResult<String>(500L);
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                System.out.println("异步调用执行超时！thread id is : " + Thread.currentThread().getId());
                deferredResult.setResult("/index");
            }
        });
        System.out.println("2"+System.currentTimeMillis());
        System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
        final HttpGet request2 = new HttpGet("http://www.baidu.com/");
        httpclient.execute(request2, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response2) {
                System.out.println(request2.getRequestLine() + "2->" + response2.getStatusLine());
                deferredResult.setResult("/formSubmit");
            }

            public void failed(final Exception ex) {
                System.out.println(request2.getRequestLine() + "->" + ex);
            }

            public void cancelled() {
                System.out.println(request2.getRequestLine() + " cancelled");
            }

        });
        System.out.println("3"+System.currentTimeMillis());
        return deferredResult;
    }



}
