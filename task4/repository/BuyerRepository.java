package task4.repository;

import task4.model.Buyer;
import task4.util.BuyerArrayList;

public interface BuyerRepository {

  BuyerArrayList getAll();

  Buyer findBuyerById(int id);

  Buyer findBuyerByName(String name);
}
