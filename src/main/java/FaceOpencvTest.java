import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;

public class FaceOpencvTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
        //-Djava.library.path=$PROJECT_DIR$\opencv\x64
        //在本项目中值为-Djava.library.path=D:\\opencv\\build\\java\\x64
        //当前使用版本是OpenCV 3.4.2
    }


    public static void main(String[] args)
    {
        Mat image = new Mat(new Size(3, 4), CvType.CV_8UC1, new Scalar(new double[]{234}));
        image.put(1, 2, new byte[]{123});
        System.out.println(image);
        System.out.println(image.dump());

        image.put(1, 2, new byte[]{200 - 256});
        System.out.println(image.dump());
        //  TraversingFolder("D:\\test");
       //TraversingFolder("D:\\pictures");
    // DetectFace("1.jpg");

     //   dilateAnderode("test");
    }

    //创建一个mat
    public static void createOneMat() {
         /*CV_<位数>{U|S|F}C(<通道数>)     位数=8|16|32|64   通道数=1|2|3|4  最多只有四通道。
        下面以一通道的来做详细的说明。
        CV_8UC1 8位无符号整数从0到255
        CV_8SC1 8位有符号整数从-128到127
        CV_16UC1 16位无符号整数从0到65536
        CV_16SC1 16位有符号整数从-32768到32767
        CV_32SC1 32位有符号整数从-2^（32-1）到2^（32-1）-1
        CV_32FC1 32位有符号小数
        CV_64FC1 64位有符号小数
        你可以使用这样的方式来创建一幅图片：
        Mat image = new Mat(new Size(3,4), CvType.CV_8UC3, new Scalar(new double[]{128,3,4}));
    　　Mat有很多中构造方法，这里只是其中一种，这样构造的mat对象含义是宽度为3个像素，高度为4个像素，8位无符号三通道图像，
       第一通道也就是Red全为128，第二通道Green全为3，第三通道Blue全是4，
       我们可以使用如下的代码打印出这个对象的信息以及每个像素点的数据。
       */
        Mat image = new Mat(new Size(3, 4), CvType.CV_8UC3, new Scalar(new double[]{128, 3, 4}));
        System.out.println(image + " width=" + image.width() + " height=" + image.height());
        System.out.println(image.dump());
    }

    //读取一张图片并展示在JavaGUI界面上
    public static void readOnePictureAndShow(String filename){
        Mat mat = Imgcodecs.imread("D:/pictures/testdilateAnderodeSrc.jpg");
        System.out.println(mat);

        ImageViewer imageViewer = new ImageViewer(mat, "第一幅图片");
        imageViewer.imshow();
    }

    //腐蚀与膨胀
    public static void dilateAnderode(String infilename) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat srcImage = Imgcodecs.imread("D:\\testdilateAnderodeSrc.jpg");

        Mat dilateImage = srcImage.clone();
        Mat erodeImage = srcImage.clone();

        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

        //膨胀
        Imgproc.dilate(srcImage, dilateImage, element, new Point(-1, -1), 1);
        //腐蚀
        Imgproc.erode(srcImage, erodeImage, element, new Point(-1, -1), 1);

        Imgcodecs.imwrite("D:\\pictures\\dilateImage.jpg", dilateImage);
        Imgcodecs.imwrite("D:\\pictures\\erodeImage.jpg", erodeImage);
    }


    //检测人脸数目
    public static void detectFace(String infilename){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.out.println("\nRunning FaceDetector");

        CascadeClassifier faceDetector = new CascadeClassifier();

        faceDetector.load("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");

        Mat image = Imgcodecs.imread(infilename);

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

        for (Rect rect : faceDetections.toArray())
        {
            Imgproc.rectangle(image, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
        String fileName = infilename.substring(infilename.lastIndexOf("\\")+1);
        //System.out.println(fileName);
       // String filename = "D:\\output_pictures\\face_"+ fileName;
        String filename = "D:\\output_test\\face_"+ fileName;

        Imgcodecs.imwrite(filename, image);

    }

    //遍历整个文件夹的目录
    public static void traversingFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        System.out.println("文件夹:" + file1.getAbsolutePath());
                        traversingFolder(file1.getAbsolutePath());
                    } else {
                        System.out.println(file1.getAbsolutePath());
                        detectFace(file1.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}
