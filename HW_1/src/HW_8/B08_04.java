package HW_8;


import java.util.PriorityQueue;
import java.util.Comparator;

public class B08_04 {
    public static void main(String[] args) {

        PriorityQueue<Point> pq = new PriorityQueue<>(
                Comparator.comparingDouble(Point::distanceToCenter)
        );

        pq.add(new Point(3, 4));   // dist 5
        pq.add(new Point(1, 1));   // dist 1.41
        pq.add(new Point(0, 5));   // dist 5
        pq.add(new Point(-2, -2)); // dist 2.82
        pq.add(new Point(10, 1));  // dist 10.049

        System.out.println("Точки у порядку зростання відстані:");

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
