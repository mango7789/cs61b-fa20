package bearmaps.proj2c;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.rules.Timeout;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private List<Vertex> solution = new ArrayList<>();
    private double solutionWeight = 0.;
    private int numStatesExplored = 0;
    private double explorationTime;
    private Map<Vertex, Double> distTo;
    private Map<Vertex, Vertex> vertexFrom;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        // auxiliary hashmap for recording the path of distance and vertex
        this.distTo = new HashMap<>();
        this.vertexFrom = new HashMap<>();
        // priority queue
        DoubleMapPQ<Vertex> priorityQueue = new DoubleMapPQ<>();
        // stopwatch, record the time
        Stopwatch sw = new Stopwatch();
        priorityQueue.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.);
        while (priorityQueue.size() != 0) {
            // exceed the required time
            if (sw.elapsedTime() > timeout) {
                this.outcome = SolverOutcome.TIMEOUT;
                this.explorationTime = timeout;
                return;
            }
            // remove the smallest, and relax all its neighbours
            Vertex first = priorityQueue.removeSmallest();
            if (first.equals(end)) {
                this.outcome = SolverOutcome.SOLVED;
                // add vertex to the solution
                Vertex vertexPoint = end;
                while (!vertexPoint.equals(start)) {
                    solution.add(vertexPoint);
                    vertexPoint = vertexFrom.get(vertexPoint);
                }
                solution.add(start);
                Collections.reverse(this.solution); // reverse the solution to get the correct path

                this.solutionWeight = distTo.get(end);
                this.explorationTime = sw.elapsedTime();
                return;
            }
            this.numStatesExplored++;

            for (WeightedEdge<Vertex> edge : input.neighbors(first)) {
                // relax edge and add neighbours to the PQ
                Vertex p = edge.from(), q = edge.to();
                double weight = edge.weight();
                double nextWeight = distTo.get(p) + weight;
                if (!distTo.containsKey(q)) {
                    distTo.put(q, Double.POSITIVE_INFINITY);
                }
                if (nextWeight < distTo.get(q)) {
                    distTo.put(q, nextWeight);
                    vertexFrom.put(q, p);
                    if (priorityQueue.contains(q)) {
                        priorityQueue.changePriority(q, nextWeight + input.estimatedDistanceToGoal(q, end));
                    }
                    else {
                        priorityQueue.add(q, nextWeight + input.estimatedDistanceToGoal(q, end));
                    }
                }
            }

        }
        // the priorityQueue is empty
        if (priorityQueue.size() == 0) {
            this.outcome = SolverOutcome.UNSOLVABLE;
            this.explorationTime = sw.elapsedTime();
        }

    }
    public SolverOutcome outcome() {
        return outcome;
    }
    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return numStatesExplored;
    }
    public double explorationTime() {
        return explorationTime;
    }

}
