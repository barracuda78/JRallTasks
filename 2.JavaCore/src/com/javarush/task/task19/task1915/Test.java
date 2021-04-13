package com.javarush.task.task19.task1915;

public class Test {

    public static void main(String[] args) {
        Adapter adapter = new Adapter(new MemoryCard());
        adapter.connectWithUsbCable();
    }

    static class Adapter implements USB{
        private MemoryCard memoryCard;

        public Adapter(MemoryCard memoryCard){
            this.memoryCard = memoryCard;
        }

        @Override
        public void connectWithUsbCable() {
            memoryCard.insert();
            memoryCard.copyData();
        }
    }

    public interface USB {

        void connectWithUsbCable();
    }

    public static class MemoryCard {

        public void insert() {
            System.out.println("Карта памяти успешно вставлена!");
        }

        public void copyData() {
            System.out.println("Данные скопированы на компьютер!");
        }
    }
}
