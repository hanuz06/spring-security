package web.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "admin_pages/admin";
    }

    @GetMapping("/user/new")
    public String createUserForm(Model model){
        List<Role> allRoles = roleService.listRoles();
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", allRoles);
        return "admin_pages/newUserForm";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("userRoles") Long[] roleIds){
        Set<Role> roleSet = new HashSet<>();
        for (Long id: roleIds){
            roleSet.add(roleService.findByRoleId(id));
        }
        user.setRoles(roleSet);

        if(user.getId()==null){
            userService.addUser(user);
        }else {
            userService.updateUser(user);
        }
        return "redirect:admin_pages/admin";

    }

    @GetMapping("/user/{id}/edit")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "admin_pages/userEditForm";
    }

//    @PostMapping("/user/update")
//    public void updateUser(@RequestBody User user, Model model){
//
//    }

    @PostMapping("/delete/{userId}")
    public void deleteUser(@PathVariable Long userId){

    }
}
