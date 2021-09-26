package task4.service.impl;

import task4.model.Buyer;
import task4.repository.BuyerRepository;
import task4.service.BuyerService;

public class BuyerServiceImpl implements BuyerService {
  private final BuyerRepository buyerRepository;

  public BuyerServiceImpl(BuyerRepository buyerRepository) {
    this.buyerRepository = buyerRepository;
  }

  @Override
  public void addBuyer(Buyer buyer) {
    buyerRepository.getAll().add(buyer);
    System.out.println("The buyer has been added!");
  }

  @Override
  public void removeBuyer(int id) {
    for (int i = 0; i < buyerRepository.getAll().size(); i++) {
      if (buyerRepository.getAll().get(i).getId() == id) {
        buyerRepository.getAll().remove(i);
        System.out.println("The buyer has been deleted!");
      }
    }
  }

  @Override
  public void printBuyerRepository() {
    System.out.println("List of all buyer:");
    buyerRepository.getAll().print();
  }
}
