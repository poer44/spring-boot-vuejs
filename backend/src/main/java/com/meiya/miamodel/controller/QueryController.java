package com.meiya.miamodel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiya.miamodel.domain.Datas;
import com.meiya.miamodel.domain.Mission;
import com.meiya.miamodel.domain.Net;
import com.meiya.miamodel.domain.Project;
import com.meiya.miamodel.repository.DatasRepository;
import com.meiya.miamodel.repository.MissionRepository;
import com.meiya.miamodel.repository.NetRepository;
import com.meiya.miamodel.repository.ProjectRepository;
import com.meiya.miamodel.socket.LogSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;


@RestController()
@RequestMapping("/api")
public class QueryController {

    private static final Logger LOG = LoggerFactory.getLogger(QueryController.class);


    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private DatasRepository datasRepository;
    @Autowired
    private NetRepository netRepository;


    @GetMapping(path = "/project")
    public @ResponseBody
    IPage<Project> getProjectList(Page<Project> page) {
        IPage<Project> res = projectRepository.selectPageVo(page);
        List<Project> list = new ArrayList<>();
        for (Project project : res.getRecords()) {
            QueryWrapper<Mission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pid", project.getId());
            if (missionRepository.selectCount(queryWrapper) != 0) {
                project.setHasChildren(true);
            }
            list.add(project);
        }
        res.setRecords(list);
        return res;
    }

    @PostMapping(path = "/project/add")
    public @ResponseBody
    Object addProject(@Valid @RequestBody Project params) {
        params.setCrtm(new Date());
        int result = projectRepository.insert(params);
        return params.getId();
    }

    @GetMapping(path = "/mission/{pid}")
    public @ResponseBody
    List<Mission> getMissionList(@PathVariable("pid") long pid) {
        QueryWrapper<Mission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        return missionRepository.selectList(queryWrapper);
    }

    @GetMapping(path = "/datas")
    public @ResponseBody
    List<Datas> getDatas() {
        return datasRepository.selectList(null);
    }

    @GetMapping(path = "/nets")
    public @ResponseBody
    List<Net> getNets() {
        return netRepository.selectList(null);
    }


    @GetMapping(path = "/test")
    public @ResponseBody
    void test() throws IOException {
        Thread thread = new Thread(() -> {
            CopyOnWriteArraySet<LogSocket> websocket = LogSocket.getWebsocket();

            Runtime run = Runtime.getRuntime();
            String cmd = "    make_image_classifier \\" +
                    "            --image_dir /usr/local/tensorflow/flower_photos \\" +
                    "            --tfhub_module /usr/local/tensorflow/tfhub_model \\" +
                    "            --image_size 224 \\" +
                    "            --saved_model_dir /usr/local/tensorflow/20200316 \\" +
                    "            --labels_output_file  /usr/local/tensorflow/20200316/class_labels.txt \\" +
                    "            --tflite_output_file /usr/local/tensorflow/20200316/new_mobile_model.tflite";
            System.out.println(cmd);
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
                    try {
                        myWebSocket.sendMessage(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("job result [" + result + "]");
                list.add(result);
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            process.destroy();
        });
        thread.start();
    }

}
