package com.testtask.SevenWindsStudio.controllers;

import com.testtask.SevenWindsStudio.DTO.RequestUserDTO;
import com.testtask.SevenWindsStudio.converter.RequestUserConverter;
import com.testtask.SevenWindsStudio.entities.User;
import com.testtask.SevenWindsStudio.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api("Контроллер")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RequestUserConverter converter;

    @GetMapping("/getuser/{id}")
    @ApiOperation("Получение конкретной записи")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/adduser")
    @ApiOperation("Добавление записи")
    public User addUser(@RequestBody RequestUserDTO dto) {
        return userService.addUser(converter.DTOToEntity(dto));
    }

    @GetMapping("/getusers")
    @ApiOperation("Получение всех записей")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @DeleteMapping("/resetdb")
    public void resetDB() {
        userService.resetDB();
    }
}
