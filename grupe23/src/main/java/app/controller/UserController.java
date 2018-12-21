package app.controller;


import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.UserService;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/user-controller")
public class UserController {


    @Autowired
    UserService userService;


    public UserController() {
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers(@RequestHeader("Authorization") String token) {
        List<User> users = userService.listAllUsers(token);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user, @RequestHeader("Authorization") String token) {
        String body = userService.createUser(user, token);
        return UtilsController.getResponseByString(body);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") long id, @RequestHeader("Authorization") String token) {
        User user = userService.getUserById(id, token);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id, @RequestHeader("Authorization") String token) {
        if (userService.deleteUserById(id, token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/set-categories", method = RequestMethod.POST)
    public ResponseEntity<String> setCategories(@RequestBody CategoriesUser categoriesUser, @RequestHeader("Authorization") String token) {
        String body = userService.setCategories(categoriesUser.getCategories(), categoriesUser.getUser(), token);
        return UtilsController.getResponseByString(body);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        HttpHeaders response = new HttpHeaders();
        HttpHeaders header = userService.loginUser(userName, password, response);
        if (!header.get("Authorization").isEmpty()) {
            return new ResponseEntity<>(null, header, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("error", HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/get-billboards", method = RequestMethod.GET)
    public ResponseEntity<List<Billboard>> getBillboards(@RequestParam("userName") String userName, @RequestHeader("Authorization") String token) {

        List<Billboard> billboards = userService.getBillboards(userName, token);
        if (billboards != null) {
            return new ResponseEntity<>(billboards, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/create-billboard", method = RequestMethod.POST)
    public ResponseEntity<String> createBillboard(@RequestBody UserBillboards userBillboards, @RequestHeader("Authorization") String token) {
        String body = userService.createBillboard(userBillboards.getBillboard(), userBillboards.getUser(), token);
        return UtilsController.getResponseByString(body);
    }

    @RequestMapping(value = "/like-billboard", method = RequestMethod.POST)
    public ResponseEntity<String> userLikesBillboard(@RequestParam("billboardName") String title, @RequestParam("userName") String userName, @RequestHeader("Authorization") String token){
        String body = userService.userLikesBillboard(title,userName,token);
        return UtilsController.getResponseByString(body);
    }


}