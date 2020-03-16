package com.meiya.miamodel.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiya.miamodel.domain.Datas;
import com.meiya.miamodel.domain.Mission;
import com.meiya.miamodel.domain.Project;
import com.meiya.miamodel.repository.DatasRepository;
import com.meiya.miamodel.repository.MissionRepository;
import com.meiya.miamodel.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController()
@RequestMapping("/api")
public class QueryController {

    private static final Logger LOG = LoggerFactory.getLogger(QueryController.class);

//    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";
//
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private DatasRepository datasRepository;
//    @RequestMapping(path = "/hello")
//    public @ResponseBody String sayHello() {
//        LOG.info("GET called on /hello resource");
//        return HELLO_TEXT;
//    }
//
//    @RequestMapping(path = "/user", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody long addNewUser (@RequestParam String firstName, @RequestParam String lastName) {
//        User user = new User(firstName, lastName);
//        userRepository.insert(user);
//        LOG.info(user.toString() + " successfully saved into DB");
//        return user.getId();
//    }
//
//    @GetMapping(path="/user/{id}")
//    public @ResponseBody User getUserById(@PathVariable("id") long id) {
//        LOG.info("Reading user with id " + id + " from database.");
//        return userRepository.selectById(id);
//    }


    @GetMapping(path = "/project")
    public @ResponseBody
    IPage<Project> getProjectList(Page<Project> page) {
        IPage<Project> res = projectRepository.selectPageVo(page);
        List<Project> list=new ArrayList<>();
        for(Project project:res.getRecords()){
            QueryWrapper<Mission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pid", project.getId());
            if(missionRepository.selectCount(queryWrapper)!=0){
                project.setHasChildren(true);
            }
            list.add(project);
        }
        res.setRecords(list);
        return res;
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


    @GetMapping(path = "/test")
    public @ResponseBody
    void test() throws IOException {
        Runtime run = Runtime.getRuntime();
        String cmd = "    make_image_classifier \\" +
                "            --image_dir my_image dir \\" +
                "            --tfhub_module https://tfhub.dev/google/tf2-preview/mobilenet_v2/feature_vector/4 \\" +
                "            --image_size 224 \\" +
                "            --saved_model_dir my_dir/new_model \\" +
                "            --labels_output_file class_labels.txt \\" +
                "            --tflite_output_file new_mobile_model.tflite";
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
        while ((result = bs.readLine()) != null) {
            System.out.println("job result [" + result + "]");
            list.add(result);
        }
        in.close();
        process.destroy();
    }

}
