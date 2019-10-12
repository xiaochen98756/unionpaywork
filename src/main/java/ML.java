import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.Boost;
import org.opencv.ml.DTrees;
import org.opencv.ml.KNearest;
import org.opencv.ml.LogisticRegression;
import org.opencv.ml.Ml;
import org.opencv.ml.NormalBayesClassifier;
import org.opencv.ml.RTrees;
import org.opencv.ml.SVM;
import org.opencv.ml.SVMSGD;
import org.opencv.ml.TrainData;

public class ML {

    /*  opencv提供了非常多的机器学习算法用于研究。这里对这些算法进行分类学习和研究，以抛砖引玉。
        这里使用的机器学习算法包括：人工神经网络，boost,决策树，最近邻，逻辑回归，贝叶斯，随机森林，SVM等算法等。
      机器学习的过程相同，都要经历1、收集样本数据sampleData2.训练分类器mode3.对测试数据testData进行预测。
      这里使用一个在别处看到的例子，利用身高体重等原始信息预测男女的概率。
      通过一些简单的数据学习，用测试数据预测男女概率。
          */
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 训练数据，两个维度，表示身高和体重  
        float[] trainingData = { 186, 80, 185, 81, 160, 50, 161, 48 };
        // 训练标签数据，前两个表示男生0，后两个表示女生1,由于使用了多种机器学习算法，
        // 他们的输入有些不一样，所以labelsMat有三种
        float[] labels = { 0f, 0f, 0f, 0f, 1f, 1f, 1f, 1f };
        int[] labels2 = { 0, 0, 1, 1 };
        float[] labels3 = { 0, 0, 1, 1 };
        // 测试数据,先男后女  
        float[] test = { 184, 79, 159, 50 };

        Mat trainingDataMat = new Mat(4, 2, CvType.CV_32FC1);
        trainingDataMat.put(0, 0, trainingData);

        Mat labelsMat = new Mat(4, 2, CvType.CV_32FC1);
        labelsMat.put(0, 0, labels);

        Mat labelsMat2 = new Mat(4, 1, CvType.CV_32SC1);
        labelsMat2.put(0, 0, labels2);

        Mat labelsMat3 = new Mat(4, 1, CvType.CV_32FC1);
        labelsMat3.put(0, 0, labels3);

        Mat sampleMat = new Mat(2, 2, CvType.CV_32FC1);
        sampleMat.put(0, 0, test);

        MyAnn(trainingDataMat, labelsMat, sampleMat);
        MyBoost(trainingDataMat, labelsMat2, sampleMat);
        MyDtrees(trainingDataMat, labelsMat2, sampleMat);
        MyKnn(trainingDataMat, labelsMat3, sampleMat);
        MyLogisticRegression(trainingDataMat, labelsMat3, sampleMat);
        MyNormalBayes(trainingDataMat, labelsMat2, sampleMat);
        MyRTrees(trainingDataMat, labelsMat2, sampleMat);
        MySvm(trainingDataMat, labelsMat2, sampleMat);
        MySvmsgd(trainingDataMat, labelsMat2, sampleMat);
    }

    // 人工神经网络  
    public static Mat MyAnn(Mat trainingData, Mat labels, Mat testData) {
        // train data using aNN  
        TrainData td = TrainData.create(trainingData, Ml.ROW_SAMPLE, labels);
        Mat layerSizes = new Mat(1, 4, CvType.CV_32FC1);
        // 含有两个隐含层的网络结构，输入、输出层各两个节点，每个隐含层含两个节点  
        layerSizes.put(0, 0, new float[] { 2, 2, 2, 2 });
        ANN_MLP ann = ANN_MLP.create();
        ann.setLayerSizes(layerSizes);
        ann.setTrainMethod(ANN_MLP.BACKPROP);
        ann.setBackpropWeightScale(0.1);
        ann.setBackpropMomentumScale(0.1);
        ann.setActivationFunction(ANN_MLP.SIGMOID_SYM, 1, 1);
        ann.setTermCriteria(new TermCriteria(TermCriteria.MAX_ITER + TermCriteria.EPS, 300, 0.0));
        boolean success = ann.train(td.getSamples(), Ml.ROW_SAMPLE, td.getResponses());
        System.out.println("Ann training result: " + success);
        // ann.save("D:/bp.xml");//存储模型  
        // ann.load("D:/bp.xml");//读取模型  

        // 测试数据  
        Mat responseMat = new Mat();
        ann.predict(testData, responseMat, 0);
        System.out.println("Ann responseMat:\n" + responseMat.dump());
        for (int i = 0; i < responseMat.size().height; i++) {
            if (responseMat.get(i, 0)[0] + responseMat.get(i, i)[0] >= 1)
            { System.out.println("Girl\n");}
            if (responseMat.get(i, 0)[0] + responseMat.get(i, i)[0] < 1)
            {System.out.println("Boy\n");}
        }
        return responseMat;
    }

    // Boost  
    public static Mat MyBoost(Mat trainingData, Mat labels, Mat testData) {
        Boost boost = Boost.create();
        // boost.setBoostType(Boost.DISCRETE);  
        boost.setBoostType(Boost.GENTLE);
        boost.setWeakCount(2);
        boost.setWeightTrimRate(0.95);
        boost.setMaxDepth(2);
        boost.setUseSurrogates(false);
        boost.setPriors(new Mat());

        TrainData td = TrainData.create(trainingData, Ml.ROW_SAMPLE, labels);
        boolean success = boost.train(td.getSamples(), Ml.ROW_SAMPLE, td.getResponses());
        System.out.println("Boost training result: " + success);
        // boost.save("D:/bp.xml");//存储模型  

        Mat responseMat = new Mat();
        float response = boost.predict(testData, responseMat, 0);
        System.out.println("Boost responseMat:\n" + responseMat.dump());
        for (int i = 0; i < responseMat.height(); i++) {
            if (responseMat.get(i, 0)[0] == 0)
                System.out.println("Boy\n");
            if (responseMat.get(i, 0)[0] == 1)
                System.out.println("Girl\n");
        }
        return responseMat;
    }

    // 决策树  
    public static Mat MyDtrees(Mat trainingData, Mat labels, Mat testData) {
        DTrees dtree = DTrees.create(); // 创建分类器  
        dtree.setMaxDepth(8); // 设置最大深度  
        dtree.setMinSampleCount(2);
        dtree.setUseSurrogates(false);
        dtree.setCVFolds(0); // 交叉验证  
        dtree.setUse1SERule(false);
        dtree.setTruncatePrunedTree(false);

        TrainData td = TrainData.create(trainingData, Ml.ROW_SAMPLE, labels);
        boolean success = dtree.train(td.getSamples(), Ml.ROW_SAMPLE, td.getResponses());
        System.out.println("Dtrees training result: " + success);
        // dtree.save("D:/bp.xml");//存储模型  

        Mat responseMat = new Mat();
        float response = dtree.predict(testData, responseMat, 0);
        System.out.println("Dtrees responseMat:\n" + responseMat.dump());
        for (int i = 0; i < responseMat.height(); i++) {
            if (responseMat.get(i, 0)[0] == 0)
                System.out.println("Boy\n");
            if (responseMat.get(i, 0)[0] == 1)
                System.out.println("Girl\n");
        }
        return responseMat;
    }

    // K最邻近  
    public static Mat MyKnn(Mat trainingData, Mat labels, Mat testData) {
        final int K = 2;
        TrainData td = TrainData.create(trainingData, Ml.ROW_SAMPLE, labels);
        KNearest knn = KNearest.create();
        boolean success = knn.train(trainingData, Ml.ROW_SAMPLE, labels);
        System.out.println("Knn training result: " + success);
        // knn.save("D:/bp.xml");//存储模型  

        // find the nearest neighbours of test data  
        Mat results = new Mat();
        Mat neighborResponses = new Mat();
        Mat dists = new Mat();
        knn.findNearest(testData, K, results, neighborResponses, dists);
        System.out.println("results:\n" + results.dump());
        System.out.println("Knn neighborResponses:\n" + neighborResponses.dump());
        System.out.println("dists:\n" + dists.dump());
        for (int i = 0; i < results.height(); i++) {
            if (results.get(i, 0)[0] == 0)
                System.out.println("Boy\n");
            if (results.get(i, 0)[0] == 1)
                System.out.println("Girl\n");
        }

        return results;
    }

    // 逻辑回归  
    public static Mat MyLogisticRegression(Mat trainingData, Mat labels, Mat testData) {
        LogisticRegression lr = LogisticRegression.create();

        TrainData td = TrainData.create(trainingData, Ml.ROW_SAMPLE, labels);
        boolean success = lr.train(td.getSamples(), Ml.ROW_SAMPLE, td.getResponses());
        System.out.println("LogisticRegression training result: " + success);
        // lr.save("D:/bp.xml");//存储模型  

        Mat responseMat = new Mat();
        float response = lr.predict(testData, responseMat, 0);
        System.out.println("LogisticRegression responseMat:\n" + responseMat.dump());
        for (int i = 0; i < responseMat.height(); i++) {
            if (responseMat.get(i, 0)[0] == 0)
                System.out.println("Boy\n");
            if (responseMat.get(i, 0)[0] == 1)
                System.out.println("Girl\n");
        }
        return responseMat;
    }

    // 贝叶斯  
    public static Mat MyNormalBayes(Mat trainingData, Mat labels, Mat testData) {
        NormalBayesClassifier nb = NormalBayesClassifier.create();

        TrainData td = TrainData.create(trainingData, Ml.ROW_SAMPLE, labels);
        boolean success = nb.train(td.getSamples(), Ml.ROW_SAMPLE, td.getResponses());
        System.out.println("NormalBayes training result: " + success);
        // nb.save("D:/bp.xml");//存储模型  

        Mat responseMat = new Mat();
        float response = nb.predict(testData, responseMat, 0);
        System.out.println("NormalBayes responseMat:\n" + responseMat.dump());
        for (int i = 0; i < responseMat.height(); i++) {
            if (responseMat.get(i, 0)[0] == 0)
                System.out.println("Boy\n");
            if (responseMat.get(i, 0)[0] == 1)
                System.out.println("Girl\n");
        }
        return responseMat;
    }

    // 随机森林  
    public static Mat MyRTrees(Mat trainingData, Mat labels, Mat testData) {
        RTrees rtrees = RTrees.create();
        rtrees.setMaxDepth(4);
        rtrees.setMinSampleCount(2);
        rtrees.setRegressionAccuracy(0.f);
        rtrees.setUseSurrogates(false);
        rtrees.setMaxCategories(16);
        rtrees.setPriors(new Mat());
        rtrees.setCalculateVarImportance(false);
        rtrees.setActiveVarCount(1);
        rtrees.setTermCriteria(new TermCriteria(TermCriteria.MAX_ITER, 5, 0));
        TrainData tData = TrainData.create(trainingData, Ml.ROW_SAMPLE, labels);
        boolean success = rtrees.train(tData.getSamples(), Ml.ROW_SAMPLE, tData.getResponses());
        System.out.println("Rtrees training result: " + success);
        // rtrees.save("D:/bp.xml");//存储模型  

        Mat responseMat = new Mat();
        rtrees.predict(testData, responseMat, 0);
        System.out.println("Rtrees responseMat:\n" + responseMat.dump());
        for (int i = 0; i < responseMat.height(); i++) {
            if (responseMat.get(i, 0)[0] == 0)
                System.out.println("Boy\n");
            if (responseMat.get(i, 0)[0] == 1)
                System.out.println("Girl\n");
        }
        return responseMat;
    }

    // 支持向量机  
    public static Mat MySvm(Mat trainingData, Mat labels, Mat testData) {
        SVM svm = SVM.create();
        svm.setKernel(SVM.LINEAR);
        svm.setType(SVM.C_SVC);
        TermCriteria criteria = new TermCriteria(TermCriteria.EPS + TermCriteria.MAX_ITER, 1000, 0);
        svm.setTermCriteria(criteria);
        svm.setGamma(0.5);
        svm.setNu(0.5);
        svm.setC(1);

        TrainData td = TrainData.create(trainingData, Ml.ROW_SAMPLE, labels);
        boolean success = svm.train(td.getSamples(), Ml.ROW_SAMPLE, td.getResponses());
        System.out.println("Svm training result: " + success);
        // svm.save("D:/bp.xml");//存储模型  
        // svm.load("D:/bp.xml");//读取模型  

        Mat responseMat = new Mat();
        svm.predict(testData, responseMat, 0);
        System.out.println("SVM responseMat:\n" + responseMat.dump());
        for (int i = 0; i < responseMat.height(); i++) {
            if (responseMat.get(i, 0)[0] == 0)
                System.out.println("Boy\n");
            if (responseMat.get(i, 0)[0] == 1)
                System.out.println("Girl\n");
        }
        return responseMat;
    }

    // SGD支持向量机  
    public static Mat MySvmsgd(Mat trainingData, Mat labels, Mat testData) {
        SVMSGD Svmsgd = SVMSGD.create();
        TermCriteria criteria = new TermCriteria(TermCriteria.EPS + TermCriteria.MAX_ITER, 1000, 0);
        Svmsgd.setTermCriteria(criteria);
        Svmsgd.setInitialStepSize(2);
        Svmsgd.setSvmsgdType(SVMSGD.SGD);
        Svmsgd.setMarginRegularization(0.5f);
        boolean success = Svmsgd.train(trainingData, Ml.ROW_SAMPLE, labels);
        System.out.println("SVMSGD training result: " + success);
        // svm.save("D:/bp.xml");//存储模型  
        // svm.load("D:/bp.xml");//读取模型  

        Mat responseMat = new Mat();
        Svmsgd.predict(testData, responseMat, 0);
        System.out.println("SVMSGD responseMat:\n" + responseMat.dump());
        for (int i = 0; i < responseMat.height(); i++) {
            if (responseMat.get(i, 0)[0] == 0)
                System.out.println("Boy\n");
            if (responseMat.get(i, 0)[0] == 1)
                System.out.println("Girl\n");
        }
        return responseMat;
    }
} 