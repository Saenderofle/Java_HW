package HW_8;


public class B08_03_MAIN {
    public static void main(String[] args) {
        B08_03<String> g = new B08_03<>();

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");

        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("B", "C");

        g.printGraph();
        System.out.println("----");

        g.removeEdge("A", "C");
        g.printGraph();
        System.out.println("----");

        g.removeVertex("B");
        g.printGraph();
    }
}

