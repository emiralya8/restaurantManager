package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class CustomerService implements IService<Customer>{

    @Autowired
    private ICustomerRepository ICustomerRepository;

    @Override
    public List<Customer> getAllElements() {
        return ICustomerRepository.findAll();
    }

    @Override
    public Customer createElement(Customer element) {
        return ICustomerRepository.save(element);
    }

    @Override
    public Customer getElementById(String id) {
        return ICustomerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateElement(String id, Customer eDetails) {
        boolean isTrue = false;
        Customer customerElement = ICustomerRepository.findById(id).orElse(null);
        if(customerElement != null){
            Field[] fields = customerElement.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(customerElement) != field.get(eDetails)){
                        isTrue = true;
                        field.set(customerElement,field.get(eDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            ICustomerRepository.save(customerElement);
        }

        return isTrue ? customerElement : eDetails;
    }

    @Override
    public void deleteElement(String id) {
        ICustomerRepository.findById(id).ifPresent(customerElement -> ICustomerRepository.deleteById(id));
    }
}
