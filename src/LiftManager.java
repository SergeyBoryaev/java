import java.util.Random;

public class LiftManager {
    private Lift[] lifts;
    private int maxPeople;
    private int numberOfFloors;

    public void simulate(int numberOfRequests) {
        Random rnd = new Random();
        for(int i = 0; i < numberOfRequests; i++) {
            int currentFloor = rnd.nextInt(numberOfFloors) + 1;
            boolean isUp;
            if (currentFloor == 1)
                isUp = true;
            else if (currentFloor == numberOfFloors)
                isUp = false;
            else
                isUp = rnd.nextBoolean();
            makeRequest(currentFloor, isUp);
        }
        for(int i = 0; i < numberOfRequests; i++) {
            for (int j = 0; j < lifts.length; j++)
                lifts[j].completeRequest();
        }
    }

    public void makeRequest(int currentFloor, boolean isUp) {
        Request req = new Request(currentFloor, true);
        Lift neaerstLift = lifts[0];
        int minDist = numberOfFloors;
        for(int i = 0; i < lifts.length; i++) {
            if (lifts[i].requests.empty()) {
                if (minDist > Math.abs(lifts[i].getCurrentFloor() - currentFloor)) {
                    minDist = Math.abs(lifts[i].getCurrentFloor() - currentFloor);
                    neaerstLift = lifts[i];
                }
            }
            else if (minDist > Math.abs(lifts[i].requests.peek().floor - currentFloor)) {
                minDist = Math.abs(lifts[i].requests.peek().floor - currentFloor);
                neaerstLift = lifts[i];
            }
        }
        neaerstLift.addRequest(req);
    }

    LiftManager(int numberOfLifts, int numberOfFloors, int maxPeople) {
        lifts = new Lift[numberOfLifts];
        this.maxPeople = maxPeople;
        this.numberOfFloors = numberOfFloors;
        for (int i = 0; i < numberOfLifts; i++)
            lifts[i] = new Lift();
    }
}
