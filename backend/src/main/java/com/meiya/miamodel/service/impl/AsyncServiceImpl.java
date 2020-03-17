package com.meiya.miamodel.service.impl;

import com.meiya.miamodel.service.AsyncService;
import com.meiya.miamodel.socket.LogSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author xuebw
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Async("executor")
    @Override
    public void executeAsync() {
        log.info("start executeAsync");
        try {
            System.out.println("当前运行的线程名称：" + Thread.currentThread().getName());
            CopyOnWriteArraySet<LogSocket> websocket = LogSocket.getWebsocket();

//            Runtime run = Runtime.getRuntime();
//            String cmd = "    make_image_classifier \\" +
//                    "            --image_dir /usr/local/tensorflow/flower_photos \\" +
//                    "            --tfhub_module /usr/local/tensorflow/tfhub_model \\" +
//                    "            --image_size 224 \\" +
//                    "            --saved_model_dir /usr/local/tensorflow/20200316 \\" +
//                    "            --labels_output_file  /usr/local/tensorflow/20200316/class_labels.txt \\" +
//                    "            --tflite_output_file /usr/local/tensorflow/20200316/new_mobile_model.tflite";
//            System.out.println(cmd);
//            Process process = null;
//            try {
//                process = run.exec(new String[]{"/bin/sh", "-c", cmd});
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            InputStream in = process.getInputStream();
//            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
//            List<String> list = new ArrayList<String>();
//            String result = null;
            int i=0;
            while (true) {
//                try {
//                    if (!((result = bs.readLine()) != null)) {
//                        break;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                //todo 仅发送ceid
                for (LogSocket myWebSocket : websocket) {
                    try {
                        myWebSocket.sendMessage("测试打日志"+i+"</br>");
                        i++;
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                System.out.println("job result [" + result + "]");
//                list.add(result);
//            }
//            try {
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
            }
//            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("end executeAsync");
    }

}
