package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {
    //2. В UsersView создай поле-контроллер, также создай ему сеттер.
    private Controller controller;


    //3. Реализуй логику метода refresh:
    //3.1. Выведи в консоль фразу "All users:".
    //3.2. Выведи в консоль всех пользователей, которые есть в modelData.
    //Перед каждым пользователем сделай отступ в виде табуляции.
    //3.3. В конце выведи визуальный разделитель данных
    //===================================================

//4. создай в ModelData поле boolean displayDeletedUserList с геттером и сеттером.
//
//5. Измени метод refresh в UsersView так, чтобы он отображал "All users:" либо "All deleted users:"
//в зависимости от того, какие пользователи находятся в списке.
//Добавь в необходимые методы модели изменение displayDeletedUserList.
    @Override
    public void refresh(ModelData modelData) {
        if(modelData.isDisplayDeletedUserList()){
            System.out.println("All deleted users:");
            for(User user : modelData.getUsers()){
                System.out.println("\t" + user);
            }
            System.out.println("===================================================");
        }else{
            System.out.println("All users:");
            for(User user : modelData.getUsers()){
                System.out.println("\t" + user);
            }
            System.out.println("===================================================");
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

//    Добавь в UsersView публичный метод void fireEventShowAllUsers(), который будет эмулировать событие клиента.
//Обратись к контроллеру и вызови его нужный метод для отображения всех пользователей.
    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }


}
