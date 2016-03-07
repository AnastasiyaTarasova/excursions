/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import Service.Service;
import java.util.List;
import javax.swing.JOptionPane;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Оксана
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

     /**
     * Test of getType method, of class User.
     */
    @Test
    public void testGetType() {
        
        System.out.println("getType");
        User instance = new User(2, "Ирина", "Петрова", "Экскурсия по рекам СПб", "Санкт-Петербург", "шкштф_1@mail.ru",
            "guide2", "guide2");
        int expResult = 2;
        int result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

   
    /**
     * Test of setType method, of class User.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        User user = new User(2, "Ирина", "Петрова", "Экскурсия по рекам СПб", "Санкт-Петербург", "ivan_1@mail.ru",
            "guide2", "guide2");
        user.setType(1);
        int type = 1;
        User instance = new User();
        instance.setType(type);
        assertEquals(instance.getType(), user.getType());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class User.
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        User user = Service.login("guide2", "guide2");
        if (user != null)
            JOptionPane.showMessageDialog(null, "the user exist");//Пользователь существует
        else{
            int result = Service.addUser(2, "Ирина", "Петрова", "Экскурсия по рекам СПб", "Санкт-Петербург", "шкштф_1@mail.ru",
            "guide2", "guide2");
            assertTrue(result > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        }
    }

    /**
     * Test of removeUser method, of class User.
     */
    @Test
    public void testRemoveUser_Long() throws Exception {
        System.out.println("removeUser");
        Long userId = 100L;
        Service.removeUser(userId);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeUser method, of class User.
     */
    @Test
    public void testRemoveUser_User() throws Exception {
        System.out.println("removeUser");
        User user = new User(2, "Ирина", "Петрова", "Экскурсия по рекам СПб", "Санкт-Петербург", "шкштф_1@mail.ru",
            "guide2", "guide2");
        User user_ = Service.login(user.getLogin(), user.getPassword());
        if (user_ != null)
            Service.removeUserObject(user);
        else
            JOptionPane.showMessageDialog(null, "the user does not exist");//Пользователя не существует
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class User.
     */
    @Test
    public void testUpdateUser() throws Exception {
        System.out.println("updateUser");
        List <User> user = Service.findNameOfExcursion("Экскурсия по рекам СПб");
        String str2 = "Ирина";
        if (user != null){
            user.get(0).setNameOfExcursion("Ирина");
            //Service.updateUser(user.get(0));
        }
        assertEquals(user.get(0).getNameOfExcursion(), str2);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    /**
     * Test of find method, of class User.
     */
    @Test
    public void testFind_long() {
        System.out.println("find");
        long id = 88L;
        String str = "Обзорная по Москве";
        User result = Service.find(id);
        assertEquals(str, result.getNameOfExcursion());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findNameGuide method, of class User.
     */
    @Test
    public void testFindNameGuide() throws Exception {
        System.out.println("findNameGuide");
        String nameGuide = "Экскурсия по рекам СПб";
        List<User> result = Service.findNameOfExcursion(nameGuide);
        assertTrue(result.size()>0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class User.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        int id = 2;
        List<User> result = Service.getAll(id);
        assertTrue(result.size()>0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
