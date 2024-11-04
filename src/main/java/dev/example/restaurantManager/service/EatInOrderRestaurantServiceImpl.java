package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EatInOrderRestaurantServiceImpl implements EatInOrderRestaurantService {

    @Autowired
    EatInOrderRepository eatInOrderRepository;

    @Override
    public List<EatInOrderRestaurant> getAllEatInOrderRestaurant() {
        return eatInOrderRepository.findAll();
    }

    @Override
    public EatInOrderRestaurant createEatInOrderRestaurant(EatInOrderRestaurant eatInOrderRestaurant) {
        return eatInOrderRepository.save(eatInOrderRestaurant);
    }

    @Override
    public EatInOrderRestaurant getEatInOrderRestaurantById(String id) {
        Optional<EatInOrderRestaurant> eatInOrderRestaurant = eatInOrderRepository.findById(id);
        return eatInOrderRestaurant.orElse(null);
    }

    @Override
    public EatInOrderRestaurant updateEatInOrderRestaurant(String id, EatInOrderRestaurant eatInOrderRestaurant) {
        EatInOrderRestaurant updateEatInOrderRestaurant = eatInOrderRepository.findById(id).orElse(null);

        if(updateEatInOrderRestaurant != null){
            updateEatInOrderRestaurant.setId(id);
            updateEatInOrderRestaurant.setDate(eatInOrderRestaurant.getDate());
            updateEatInOrderRestaurant.setPaid(eatInOrderRestaurant.isPaid());
            updateEatInOrderRestaurant.setMenus(eatInOrderRestaurant.getMenus());
            updateEatInOrderRestaurant.setPeopleQty(eatInOrderRestaurant.getPeopleQty());
            updateEatInOrderRestaurant.setWaiter(eatInOrderRestaurant.getWaiter());
            updateEatInOrderRestaurant.setTotalPayment(eatInOrderRestaurant.getTotalPayment());
            updateEatInOrderRestaurant.setOrderedTableRestaurant(eatInOrderRestaurant.getOrderedTableRestaurant());
            return eatInOrderRepository.save(updateEatInOrderRestaurant);
        }

        return null;
    }

    @Override
    public boolean deleteEatInOrderRestaurant(String id) {
        eatInOrderRepository.deleteById(id);
        return eatInOrderRepository.findById(id).isPresent();
    }
}
