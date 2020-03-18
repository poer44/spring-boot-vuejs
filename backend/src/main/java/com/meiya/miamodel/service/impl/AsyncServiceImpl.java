package com.meiya.miamodel.service.impl;

import com.meiya.miamodel.service.AsyncService;
import com.meiya.miamodel.socket.LogSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author xuebw
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Async("executor")
    @Override
    public void executeAsync(long mid) {
        log.info("start executeAsync");
        try {
            log.info("当前运行的线程名称：" + Thread.currentThread().getName());
            CopyOnWriteArraySet<LogSocket> websocket = LogSocket.getWebsocket();

            Runtime run = Runtime.getRuntime();
            String cmd = "    make_image_classifier \\" +
                    "            --image_dir /home/miamodel/datasets/flower_photos \\" +
                    "            --tfhub_module /home/miamodel/tf_hub/imagenet/ \\" +
                    "            --image_size 224 \\" +
                    "            --saved_model_dir /home/miamodel/result/"+mid+" \\" +
                    "            --labels_output_file  /home/miamodel/result/"+mid+"/class_labels.txt \\" +
                    "            --tflite_output_file /home/miamodel/result/"+mid+"/new_mobile_model.tflite";
            log.info(cmd);
            Process process = null;
            try {
                process = run.exec(new String[]{"/bin/sh", "-c", cmd});
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            List<String> list = new ArrayList<String>();
            String result = null;

            while (true) {
                try {
                    if (!((result = bs.readLine()) != null)) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //todo 仅发送ceid
                for (LogSocket myWebSocket : websocket) {
                    if(myWebSocket.getMid()==mid) {
                        try {
                            myWebSocket.sendMessage(result + "</br>");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                log.info("job result [" + result + "]");
                list.add(result);
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("end executeAsync");
    }

}
