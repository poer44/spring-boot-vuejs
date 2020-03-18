package com.meiya.miamodel.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Component 将类注入到容器
 * @ServerEndpoint 前端通过这个url进行访问通信 建立连接
 */
@ServerEndpoint("/logsocket/{mid}")
@Component
@Slf4j
public class LogSocket {

    //存放websocket 的线程安全的无序的集合
    private static CopyOnWriteArraySet<LogSocket> websocket = new CopyOnWriteArraySet<LogSocket>();

    private Session session;
    private long mid;

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public static CopyOnWriteArraySet<LogSocket> getWebsocket() {
        return websocket;
    }

    public static void setWebsocket(CopyOnWriteArraySet<LogSocket> websocket) {
        LogSocket.websocket = websocket;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("mid") long mid) {
        this.session = session;
        this.mid = mid;
        websocket.add(this);     //加入set中
        // addOnlineCount();           //在线数加1
        try {
            sendMessage("连接已建立成功.当前任务id为" + mid);
        } catch (Exception e) {
            System.out.println("IO异常");
        }
    }


    /**
     * 关闭通信连接
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        //关闭连接后将此socket删除
        websocket.remove(this);
        System.out.println("进入onClose方法");
    }

    /**
     * 获取客户端发来的信息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("进入onMessage方法; message = " + message);
    }

    /**
     * 给客户端推送信息
     */
    public void sendMessage(String message) throws IOException {
        log.info("socket发送-mid=" + this.mid + "-session" + this.session);
        if (session != null) {
            synchronized (session) {
                this.session.getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 异常方法
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("进入error方法");
        error.printStackTrace();
    }

}
