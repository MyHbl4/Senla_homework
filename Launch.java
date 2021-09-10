public class Launch {
    public static void main(String[] args) {
        BodyLineStep body = new BodyLineStep();
        EngineLineStep engine = new EngineLineStep();
        TowerLineStep tower = new TowerLineStep();
        AssemblyLine line = new AssemblyLine(body, engine, tower); //запуск линии
        Tank Tortoise = (Tank) line.assembleProduct(new Tank()); //какой именно продукт на выходе (танк)
    }
}
