package task4.UI;

import task4.UI.action.Manager;

public class Navigator {

  private static Navigator instance;
  private Menu currentMenu;

  private Navigator() {}

  public static Navigator getInstance() {
    if (instance == null) {
      instance = new Navigator();
    }
    return instance;
  }

  public void printMenu() {
    System.out.println(currentMenu);
  }

  public void helloShop(){
    System.out.println("\n::BOOK STORE::\n*******************");
  }

  public void loadCsv(){
    new Manager().getBookService().downloadBookCsv();
    new Manager().getOrderService().downloadOrderCsv();
    new Manager().getRequestService().downloadRequestCsv();
  }

  public void navigate(int index) {
    if (currentMenu != null) {
      MenuItem menuItem = currentMenu.getMenuItems().get(index);
      menuItem.doAction();
      currentMenu = menuItem.getNextMenu();
    }
  }

  public void setCurrentMenu(Menu currentMenu) {
    this.currentMenu = currentMenu;
  }

  public Menu getCurrentMenu() {
    return currentMenu;
  }
}
