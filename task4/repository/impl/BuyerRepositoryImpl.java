package task4.repository.impl;

import task4.datasource.BuyerDataSource;
import task4.model.Buyer;
import task4.repository.BuyerRepository;
import task4.util.BuyerArrayList;

public class BuyerRepositoryImpl implements BuyerRepository {
  private final BuyerDataSource buyerDataSource;

  public BuyerRepositoryImpl(BuyerDataSource buyerDataSource) {
    this.buyerDataSource = buyerDataSource;
  }

  @Override
  public BuyerArrayList getAll() {
    return buyerDataSource.getBuyers();
  }

  @Override
  public Buyer findBuyerById(int id) {
    Buyer buyer = null;
    for (int i = 0; i < buyerDataSource.getBuyers().size(); i++) {
      if (buyerDataSource.getBuyers().get(i).getId() == id) {
        buyer = buyerDataSource.getBuyers().get(i);
      }
    }
    return buyer;
  }

  @Override
  public Buyer findBuyerByName(String name) {
    Buyer buyer = null;
    for (int i = 0; i < buyerDataSource.getBuyers().size(); i++) {
      if (buyerDataSource.getBuyers().get(i).getName().equals(name)) {
        buyer = buyerDataSource.getBuyers().get(i);
      }
    }
    return buyer;
  }
}
