import vw.learner.VWIntLearner;
import vw.learner.VWLearners;

import java.io.File;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String[] train = new String[]{
                "1:2:0.4 | a c",
                "3:0.5:0.2 | b d",
                "4:1.2:0.5 | a b c",
                "2:1:0.3 | b c",
                "3:1.5:0.7 | a d"
        };

        try {
            String cbModel = new File("/home/shaktiprakash.das/intellij-workspace/vw-jni-test/files/test.model").getAbsolutePath();
            VWIntLearner vw = VWLearners.create("--quiet --cb 4 -f " + cbModel);
            int[] trainPreds = new int[train.length];
            for (int i = 0; i < train.length; ++i) {
                trainPreds[i] = vw.learn(train[i]);
            }
            //int[] expectedTrainPreds = new int[]{1, 2, 2, 2, 2};
            vw.close();
            printPrediction(trainPreds);


            vw = VWLearners.create("--quiet -t -i " + cbModel);
            String[] test = new String[]{
                    "1:2 3:5 4:1:0.6 | a c d",
                    "1:0.5 2:1:0.4 3:2 4:1.5 | c d"
            };

            int[] testPreds = new int[test.length];
            for (int i = 0; i < testPreds.length; ++i) {
                testPreds[i] = vw.predict(test[i]);
            }
            //int[] expectedTestPreds = new int[]{4, 4};
            vw.close();
            printPrediction(testPreds);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printPrediction(int[] pred) {
        System.out.println();
        for (int x : pred) {
            System.out.print(x + " ");
        }
    }
}
