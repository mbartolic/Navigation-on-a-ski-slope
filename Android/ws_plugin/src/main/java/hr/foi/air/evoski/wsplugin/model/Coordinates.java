package hr.foi.air.evoski.wsplugin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Coordinates model.
 */
public class Coordinates implements Serializable{

    private List<Path> paths;

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public class Path implements Serializable{

        private float distance;

        private float weight;

        private List<Instruction> instructions;

        public float getDistance() {
            return distance;
        }

        public void setDistance(float distance) {
            this.distance = distance;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public List<Instruction> getInstructions() {
            return instructions;
        }

        public void setInstructions(List<Instruction> instructions) {
            this.instructions = instructions;
        }
    }
}
