import vw.learner.VWFloatLearner;
import vw.learner.VWIntLearner;
import vw.learner.VWLearners;

import java.io.File;

/**
 * Created by shaktiprakash.das on 6/14/16.
 */
public class HouseLearner {

    public static void main(String[] args) {
        String[] train = new String[]{
                "0 | price:.23 sqft:.25 age:.05 2006",
                "1 2 'second_house | price:.18 sqft:.15 age:.35 1976",
                "0 1 0.5 'third_house | price:.53 sqft:.32 age:.87 1924"
        };

        String model = new File("/home/shaktiprakash.das/intellij-workspace/vw-jni-test/files/house.model").getAbsolutePath();
        VWFloatLearner vw = VWLearners.create("--quiet -l 10 -c --passes 25 --holdout_off " + model);
        float[] trainPreds = new float[train.length];
        for (int i = 0; i < train.length; ++i) {
            trainPreds[i] = vw.learn(train[i]);
        }
        vw.close();
        printPrediction(trainPreds);

        vw = VWLearners.create("--quiet -p " + model);
        String[] test = new String[]{
                "0 | price:.23 sqft:.25 age:.05 2006",
                "1 2 'second_house | price:.18 sqft:.15 age:.35 1976",
                "0 1 0.5 'third_house | price:.53 sqft:.32 age:.87 1924"
        };

        float[] testPreds = new float[test.length];
        for (int i = 0; i < testPreds.length; ++i) {
            testPreds[i] = vw.predict(test[i]);
        }
        vw.close();
        printPrediction(testPreds);
    }

    private static void printPrediction(float[] pred) {
        System.out.println();
        for (float x : pred) {
            System.out.print(x + " ");
        }
    }

}
