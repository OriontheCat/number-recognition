import java.util.ArrayList;
import java.util.Arrays;

class DigitRecognitionService {
    private Neuron[] _neurons;

    private double[][][] samples;

    public DigitRecognitionService() {
        _neurons = new Neuron[10];
        for (int i = 0; i < 10; i++) {
            _neurons[i] = new Neuron(35);
        }
    }

    public Neuron getNeuron(int index) {
        return _neurons[index];
    }

    public void generateSamples() {
        samples = new double[10][][];
        for (int i = 0; i < 10; i++) {
            double[][] variations = new double[30][];
            variations[0] = Arrays.copyOf(DigitRecognitionFrame.DIGITS[i], DigitRecognitionFrame.DIGITS[i].length);
            for (int j = 1; j < 30; j++) {
                variations[j] = Arrays.copyOf(DigitRecognitionFrame.DIGITS[i], DigitRecognitionFrame.DIGITS[i].length);
                double rate = 0;
                for (int k = 0; k < variations[j].length; k++) {
                    if (Math.random() < rate) {
                        if (variations[j][k] == 0)
                            variations[j][k] += Math.random();
                        else
                            variations[j][k] -= (Math.random() * variations[j][k]);
                    }
                }
                // printSample(variations[j]);
            }
            samples[i] = variations;
        }
    }

    public void printSample(double[] sample) {
        for (int i = 0; i < sample.length; i++) {
            String string = sample[i] == 1 ? "#" : sample[i] == 0 ? " " : "~";
            if ((i + 1) % 5 == 0) {
                System.out.println(string);
            } else {
                System.out.print(string);
            }
        }
    }

    public void printSampleWithTarget(double[] sampleWithTarget) {
        for (int i = 1; i < sampleWithTarget.length; i++) {
            String string = sampleWithTarget[i] == 1 ? "#" : sampleWithTarget[i] == 0 ? " " : "~";
            if ((i) % 5 == 0) {
                System.out.println(string);
            } else {
                System.out.print(string);
            }
        }
    }

    public void train() {
        ArrayList<double[]> samplesWithTargets = new ArrayList<double[]>();
        for (int neuronIndex = 0; neuronIndex < 10; neuronIndex++) {
            for (int digitIndex = 0; digitIndex < samples.length; digitIndex++) {
                double target = 0;
                if (neuronIndex == digitIndex) {
                    target = 1;
                }
                for (int variationIndex = 0; variationIndex < samples[digitIndex].length; variationIndex++) {
                    double[] sampleWithTarget = new double[36];
                    sampleWithTarget[sampleWithTarget.length - 1] = target;
                    for (int l = 0; l < 35; l++) {
                        sampleWithTarget[l] = samples[digitIndex][variationIndex][l];
                    }
                    printSampleWithTarget(sampleWithTarget);
                    samplesWithTargets.add(sampleWithTarget);
                }
            }
        }
        for (int i = 0; i <= 9; i++) {
            _neurons[i].train(samplesWithTargets, 0.2, 100);
        }
    }

    public double[] classify(double[] sample) {
        double[] oneHotVector = new double[10];
        for (int i = 0; i <= 9; i++) {
            oneHotVector[i] = _neurons[i].fireSample(sample);
        }
        return oneHotVector;
    }
}