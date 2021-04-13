package com.javarush.task.task36.task3608.controller;

import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.model.ModelData;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

public class Controller {
    private Model model;
    private UsersView usersView;
    private EditUserView editUserView;

    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }


    public void setModel(Model model) {
        this.model = model;
    }

//    6. В методе onShowAllUsers() класса Controller должен вызываться метод refresh у объекта usersView с параметром model.getModelData() после вызова метода loadUsers().

    public void onShowAllUsers(){
        model.loadUsers();
        usersView.refresh(model.getModelData());
    }


    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
    }


    public void onShowAllDeletedUsers() {
//       ModelData modelData = model.getModelData();
//       modelData.getUsers();
        model.loadDeletedUsers();
        //тут рефреш в шаге 6!!!!!!!!!
        usersView.refresh(model.getModelData());
    }


//    Это получилось потому, что данные пришли с сервера, обновились в ModelData, но Вью ничего о них не знает.
//Вью сама не умеет себя обновлять. Это делает Контроллер.
//Пойди в контроллер и добавь обновление данных во Вью.
//Напомню, данные хранятся в Модели.

    public void onOpenUserEditForm(long userId) {
        model.loadUserById(userId);
        editUserView.refresh(model.getModelData());
    }

    public void onUserDelete(long id){
        model.deleteUserById(id);
        usersView.refresh(model.getModelData());
    }

    public void onUserChange(String name, long id, int level){
        model.changeUserData(name,id,level);

        usersView.refresh(model.getModelData());
    }
}
