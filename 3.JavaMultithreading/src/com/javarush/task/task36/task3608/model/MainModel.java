package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model {
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

//    3. Реализуй логику метода loadUsers:
//3.1. Достань всех пользователей между 1 и 100 уровнями. (Метод getUsersBetweenLevels(1, 100)).
//3.2. Положи всех пользователей в modelData.

    //7MVC
    //3. Выполни рефакторинг класса MainModel. Теперь, когда есть удаленные пользователи, часть методов стала работать неправильно.
    //Почти во всех методах, где требуется список пользователей, нужно работать только с активными(живыми) пользователями.
    //Вынеси в отдельный приватный метод List<User> getAllUsers() получение списка всех активных пользователей.
    //Фильтрация активных пользователей у тебя уже есть - метод List<User> filterOnlyActiveUsers(List<User> allUsers).
    //Отрефактори все методы, которые используют список пользователей. Они должны использовать список живых пользователей.

    private List<User> getAllUsers(){
        modelData.setDisplayDeletedUserList(false);

        List<User> users = userService.getUsersBetweenLevels(1, 100);
        List<User> livingUsers = userService.filterOnlyActiveUsers(users);
        return livingUsers;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(getAllUsers());
        modelData.setDisplayDeletedUserList(false); //-------------> added in MVC (6) task
    }


    public void loadDeletedUsers() {
        modelData.setUsers(userService.getAllDeletedUsers());
        modelData.setDisplayDeletedUserList(true);  //-------------> added in MVC (6) task
    }

//    В классе MainModel в методе loadUserById(long) нужно вызвать метод getUsersById(long) на объекте класса UserService.
//    В классе MainModel в методе loadUserById(long) нужно вызвать метод setActiveUser(User) на объекте класса ModelData.
//Проблема в методе loadUserById( ) класса MainModel.
// Необходимо сохранить объект типа User, получаемый из userService при помощи метода getUsersById( )
// , и затем назначить модели активного пользователя, используя для этого метод setActiveUser( ) и передав в него полученный ранее объект.

    public void loadUserById(long userId){
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
        modelData.setDisplayDeletedUserList(false); //-------------> added in MVC (7) task
    }

    public void deleteUserById(long id){
        userService.deleteUser(id);
        modelData.setUsers(getAllUsers());
        modelData.setDisplayDeletedUserList(false); //-------------> added in MVC (8) task
    }

    public void changeUserData(String name, long id, int level){
        userService.createOrUpdateUser(name, id, level);
        User user = userService.getUsersById(id);
        modelData.setActiveUser(user);
        loadUsers();
    }
}
