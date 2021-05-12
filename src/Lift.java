import java.util.List;
import java.util.Stack;

public class Lift {
    private int currentFloor;
    private int currentNumberOfPeople;
    public Stack<Request> requests;

    public Lift() {
        this.currentNumberOfPeople = 0;
        currentFloor = 1;
        requests = new Stack<Request>();
    }

    public int getCurrentNumberOfPeople() {
        return currentNumberOfPeople;
    }

    public void setCurrentNumberOfPeople(int currentNumberOfPeople) {
        this.currentNumberOfPeople = currentNumberOfPeople;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void completeRequest() {
        if (requests.empty())
            return;
        Request curr = requests.pop();
        if(curr.isAdd)
            currentNumberOfPeople += 1;
        else
            currentNumberOfPeople -= 1;
        System.out.println("Go to " + curr.floor + " with " + currentNumberOfPeople + " people.");
    }

    public void addRequest(Request req) {
        requests.push(req);
    }
}

