/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

/**
 *
 * @author root
 */
public interface Subject {
     
        void attach(Observer o);
        void deattach(Observer o);
        void alert();
    
}
