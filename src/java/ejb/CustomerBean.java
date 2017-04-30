/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Customer;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateful
public class CustomerBean implements CustomerBeanLocal {
@PersistenceContext(unitName = "DEVMODE")
    private EntityManager em;

    public Customer add(Customer customer){
        return em.merge(customer);
    }

    public Customer get(int id){
        return em.find(Customer.class, id);
    }

    public void update(Customer customer){
        add(customer);
    }

    public void delete(int id){
        em.remove(get(id));
    }

    public List<Customer> getAll(){
        TypedQuery<Customer> namedQuery = em.createNamedQuery("Customer.getAll", Customer.class);
        return namedQuery.getResultList();
    }
}
