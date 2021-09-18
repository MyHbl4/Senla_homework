package task4.service;

import task4.model.Buyer;

public interface BuyerService {

  void addBuyer(Buyer buyer);

  void removeBuyer(int id);

  void printBuyerRepository();
}
