public class AssemblyLine implements IAssemblyLine{
    private ILineStep body;
    private ILineStep engine;
    private ILineStep tower;

    public AssemblyLine(ILineStep body, ILineStep engine, ILineStep tower) {
        this.body = body;
        this.engine = engine;
        this.tower = tower;
    }

    @Override
    public IProduct assembleProduct(IProduct iProduct) {
        System.out.println("Установка элементов!");
        iProduct.installFirstPart(body.buildProductPart());
        iProduct.installSecondPart(engine.buildProductPart());
        iProduct.installThirdPart(tower.buildProductPart());
        System.out.println("Установка завершена! Танк готов!");

        return iProduct;
    }
}
