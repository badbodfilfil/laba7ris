/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lenovo Z
 */
@Local
public interface CustomerBeanLocal {
    
    public Customer add(Customer customer);

    public Customer get(int id);

    public void update(Customer customer);

    public void delete(int id);
public List<Customer> getAll();
}
