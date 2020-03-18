package com.meiya.miamodel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiya.miamodel.domain.*;
import com.meiya.miamodel.repository.*;
import com.meiya.miamodel.service.AsyncService;
import com.meiya.miamodel.socket.LogSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;


@RestController()
@RequestMapping("/api")
@Slf4j
public class QueryController {



    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private DatasRepository datasRepository;
    @Autowired
    private NetRepository netRepository;
    @Autowired
    private MissionDetailsRepository mretailRepository;
    @Autowired
    private AsyncService asyncService;


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

    @PostMapping(path = "/mission/add")
    public @ResponseBody
    Object addMission(@Valid @RequestBody Mission params) {
        params.setCrtm(new Date());
        int result = missionRepository.insert(params);
        return params.getId();
    }

    @PostMapping(path = "/mretail/add")
    public @ResponseBody
    Object addMretail(@Valid @RequestBody MissionDetails params) {
        int result = mretailRepository.insert(params);
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


    @GetMapping(path = "/start_train")
    public @ResponseBody
    void startTrain(long mid) {
        asyncService.executeAsync(mid);
    }

}
