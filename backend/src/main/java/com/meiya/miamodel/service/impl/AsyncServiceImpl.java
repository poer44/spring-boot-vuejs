package com.meiya.miamodel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.meiya.miamodel.domain.MissionDetails;
import com.meiya.miamodel.repository.MissionDetailsRepository;
import com.meiya.miamodel.service.AsyncService;
import com.meiya.miamodel.socket.LogSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author xuebw
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private MissionDetailsRepository mretailRepository;

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
                    "            --saved_model_dir /home/miamodel/result/" + mid + " \\" +
                    "            --labels_output_file  /home/miamodel/result/" + mid + "/class_labels.txt \\" +
                    "            --tflite_output_file /home/miamodel/result/" + mid + "/new_mobile_model.tflite";
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
                for (LogSocket myWebSocket : websocket) {
                    if (myWebSocket.getMid() == mid) {
                        try {
                            myWebSocket.sendMessage(transprocess(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(result);
            }
            try {
                Map finalmap = new Gson().fromJson(transprocess(result), Map.class);
                MissionDetails mretail = new MissionDetails();

                mretail.setLog(list.toString());
//                mretail
//                mretailRepository.update()
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

    /**
     * out特殊处理
     *
     * @param val
     * @return
     */
    private String transprocess(String val) {
        Map map = new HashMap<>();
        if (StringUtils.isNotBlank(val)) {
            val = val.replaceAll("\b", "").replaceAll("-", "").replaceAll("\\s+", " ").replaceAll(":", "");
            String[] strs = val.split(" ");
            if (strs.length > 4) {//姑且认为是正常的数据类型
                //处理进度
                String[] countp = strs[0].split("/");
                if (countp.length == 2) {
                    map.put("process", Double.parseDouble(countp[0]) / Double.parseDouble(countp[1]));
                }
                if (strs.length % 2 == 0) {
                    for (int i = 2; i < strs.length; i++) {
                        map.put(strs[i], strs[i + 1]);
                        i++;
                    }
                }
            }
        }
        map.put("cmd", val);
        Gson gson = new Gson();
        val = gson.toJson(map);
        return val;
    }

    public static void main(String[] args) {
        String str = "89/98";
        String[] countp = str.split("/");
        System.out.println(Double.parseDouble(countp[0]) / Double.parseDouble(countp[1]));
    }

}
