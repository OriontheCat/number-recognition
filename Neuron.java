import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Neuron {
    double[] weights;

    public Neuron(int length) {
        this.weights = generateWeights(length);
    }

    public double[] generateWeights(int length) {
        double[] weights = new double[length + 1];
        // Initialize weights
        for (int i = 0; i < length; i++) {
            weights[i] = Math.random();
        }
        return weights;
    }

    public void train(ArrayList<double[]> samples, double learningRate, int epochs) {
        // Set up instance vars
        int epoch = 0;
        double error;
        // Train until error = 0 or max epochs
        do {
            error = 0;
            epoch++;
            // Shuffle samples
            Collections.shuffle(samples);
            for (int sampleIndex = 0; sampleIndex < samples.size(); sampleIndex++) {
                double[] sample = samples.get(sampleIndex);
                double target = sample[sample.length - 1];
                sample = Arrays.copyOf(sample, sample.length - 1);
                double[] inputs = new double[sample.length + 1];
                inputs[0] = -1;
                for (int i = 0; i < sample.length; i++) {
                    inputs[i + 1] = sample[i];
                }
                double output = fire(inputs);
                double sampleError = target - (output > 0 ? 1 : 0);
                error += Math.abs(sampleError);
                for (int weightIndex = 0; weightIndex < weights.length; weightIndex++) {
                    double deltaWeight = learningRate * sampleError * inputs[weightIndex];
                    weights[weightIndex] += deltaWeight;
                }
            }
            System.out.printf("Epoch: %d | Error: %f\n", epoch, error);
        } while (error != 0 && epoch < epochs);
    }

    public double fire(double[] inputs) {
        double value = 0;
        for (int i = 0; i < inputs.length; i++) {
            value += inputs[i] * weights[i];
        }
        return value;
    }

    public double fireSample(double[] sample) {
        double value = -weights[0];
        for (int i = 0; i < sample.length; i++) {
            value += sample[i] * weights[i + 1];
        }
        return value;
    }

    public void reset() {
        weights = generateWeights(weights.length);
    }
}