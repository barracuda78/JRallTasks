package com.javarush.task.task21.task2112;
//Establishing database connection...
//        Entering body of try block.
//        Executing useful operation.
//        Operation is not supported yet!
//        Closing database connection...
public class Solution {
    public static void main(String[] args) {
        DBConnectionManager dbConnectionManager = new DBConnectionManager();
        try (FakeConnection fakeConnection = dbConnectionManager.getFakeConnection()) {
            System.out.println("Entering body of try block.");
            fakeConnection.usefulOperation();
            fakeConnection.unsupportedOperation();
        } catch (Exception e) {
        }
    }
}
