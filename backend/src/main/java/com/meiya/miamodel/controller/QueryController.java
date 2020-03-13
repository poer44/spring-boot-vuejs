package com.meiya.miamodel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiya.miamodel.domain.Project;
import com.meiya.miamodel.domain.User;
import com.meiya.miamodel.repository.ProjectRepository;
import com.meiya.miamodel.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api")
public class QueryController {

    private static final Logger LOG = LoggerFactory.getLogger(QueryController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody long addNewUser (@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User(firstName, lastName);
        userRepository.insert(user);
        LOG.info(user.toString() + " successfully saved into DB");
        return user.getId();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {
        LOG.info("Reading user with id " + id + " from database.");
        return userRepository.selectById(id);
    }

    @GetMapping(path="/project")
    public @ResponseBody
    IPage<Project> getProjectList(Page<Project> page) {
        return projectRepository.selectPageVo(page);
    }

}
