package task4.datasource;

import task4.model.Buyer;

public class BuyerRepositoryImpl implements BuyerRepository {
  private final BuyerDataSource buyerDataSource;

  public BuyerRepositoryImpl(BuyerDataSource buyerDataSource) {
    this.buyerDataSource = buyerDataSource;
  }

  @Override
  public void addBuyer(Buyer buyer) {
    buyerDataSource.getBuyers().add(buyer);
    System.out.println("The buyer has been added!");
  }

  @Override
  public void printBuyerRepository() {
    System.out.println("List of all buyer:");
    buyerDataSource.getBuyers().print();
  }
}
