/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.controlador;

import java.sql.SQLException;
import modelo.MPersonal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class TestPersonal {
    
    MPersonal personal;
    
    public TestPersonal() {
         personal = new MPersonal();
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Comenzando");
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Terminando");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void busuqeda() throws SQLException{
        personal.busquedaDinamica("V", "27");
    }
}
