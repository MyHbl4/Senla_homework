package task4.datasource;

import task4.model.Buyer;
import task4.other.BuyerArrayList;

public class BuyerDataSourceImpl implements BuyerDataSource {
  private final BuyerArrayList buyers = new BuyerArrayList();

  public BuyerDataSourceImpl() {
    initData();
  }

  public void initData() {

    buyers.add(new Buyer(1, "Вася Сюткин", 28, "Гомель, ул. Войкова 256"));
    buyers.add(new Buyer(2, "Антон Шкуратов", 32, "Гомель, пр-т Речицкий 67"));
    buyers.add(new Buyer(3, "Вася Сюткин", 32, "Гомель, ул. Международная 35"));
  }

  @Override
  public BuyerArrayList getBuyers() {
    return buyers;
  }
}
