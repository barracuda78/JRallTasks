package com.javarush.task.task36.task3608;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.MainModel;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

public class Solution {
    public static void main(String[] args) {
//        4. Обнови Solution.main: замени FakeModel на MainModel.
//Преимущества MVC в том, что в любой момент времени легко можно заменить любую часть паттерна.
//5. В методе main вызов методов должен происходить в такой последовательности:
// fireEventShowAllUsers(),
// fireEventOpenUserEditForm(126L),
// fireEventUserDeleted(124L),
// fireEventShowDeletedUsers().
//2. Добавь в main вызов fireEventUserDeleted(124L) перед вызовом метода fireEventShowDeletedUsers().
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();
        Controller controller = new Controller();

        usersView.setController(controller);
        editUserView.setController(controller);

        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);


        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126L);
        editUserView.fireEventUserDeleted(124L);
        editUserView.fireEventUserChanged("R-U-Z-A-E-V", 126L, 41);
        usersView.fireEventShowDeletedUsers();
    }
}