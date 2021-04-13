package com.javarush.task.task36.task3608.model;

//3. В пакете model создай класс FakeModel, реализующий Model. Он нам понадобится на начальном этапе.
//В нем создай поле ModelData modelData, которое инициализируй объектом.

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();
    @Override
    public ModelData getModelData() {
        return modelData;
    }

//Реализуй его в FakeModel: инициализируй список пользователей modelData любыми данными. Они не влияют на тестирование.
//У меня такие данные:
//User{name='A', id=1, level=1}
//User{name='B', id=2, level=1}
    @Override
    //Метод void loadUsers() в классе FakeModel должен инициализировать список пользователей в объекте modelData любыми данными.
    public void loadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Rustamov", 1L, 2));
        users.add(new User("Ruzaev", 2L, 25));
        modelData.setUsers(users);
    }

    public void loadDeletedUsers(){
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }

    public void loadUserById(){
        throw new UnsupportedOperationException();
    }

    public void deleteUserById(long id){
        throw new UnsupportedOperationException();
    }

    public void changeUserData(String name, long id, int level){
        throw new UnsupportedOperationException();
    }

}
