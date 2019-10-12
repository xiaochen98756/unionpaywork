//import org.bytedeco.javacpp.*;
//import org.bytedeco.javacv.*;
//import org.junit.Test;
//
//import javax.swing.*;
//import java.io.File;
//import java.math.RoundingMode;
//import java.net.MalformedURLException;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import static org.bytedeco.javacpp.opencv_core.Mat;
//import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGRA2GRAY;
//
//public class JavaCVTest {
//
//    private void showFrames(String winTitle, FrameGrabber grabber) throws FrameGrabber.Exception, InterruptedException {
//        CanvasFrame canvas = new CanvasFrame(winTitle,1);//新建一个窗口
//        canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        canvas.setAlwaysOnTop(true);
//        while (true) {
//            if (!canvas.isVisible()) {
//                break;
//            }
//            Frame frame = grabber.grab();
//            canvas.showImage(frame);
//
//            Thread.sleep(50);//50毫秒刷新一次图像
//        }
//    }
//
//    private void showFramesWithFace(String winTitle, FrameGrabber grabber) throws FrameGrabber.Exception, InterruptedException {
//        OpenCVFrameConverter.ToMat convertToMat = new OpenCVFrameConverter.ToMat();
//        //File fileAbsolutePath = new File(ClassLoader.getSystemClassLoader().getResource("D:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface_improved.xml").getFile());
//        //opencv_objdetect.CvHaarClassifierCascade face_cascade=opencv_objdetect.cvLoadHaarClassifierCascade(fileAbsolutePath.getAbsolutePath(),new opencv_core.CvSize(0,0));
//        opencv_objdetect.CascadeClassifier face_cascade = new opencv_objdetect.CascadeClassifier("D:\\\\opencv\\\\sources\\\\data\\\\lbpcascades\\\\lbpcascade_frontalface_improved.xml");
//        opencv_core.RectVector faces = new opencv_core.RectVector();
//        CanvasFrame canvas = new CanvasFrame(winTitle,1);//新建一个窗口
//        canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        canvas.setAlwaysOnTop(true);
//        while (true) {
//            if (!canvas.isVisible()) {
//                break;
//            }
//            Frame frame = grabber.grab();
//            Mat mat = convertToMat.convert(frame);
//            if (mat.empty())
//                continue;
//            Mat videoMatGray = new Mat();
//            opencv_imgproc.cvtColor(mat, videoMatGray, COLOR_BGRA2GRAY);
//            opencv_imgproc.equalizeHist(videoMatGray, videoMatGray);
//            //int[] rejectLevels = new int[0];
//            //double[] levelWeights = new double[0];
//            face_cascade.detectMultiScale(videoMatGray, faces);
//            for (int i = 0; i < faces.size(); i++) {
//                opencv_core.Rect face = faces.get(i);
//                opencv_imgproc.rectangle(mat, face, opencv_core.Scalar.RED, 4, 8, 0);
//            }
//
//            //opencv_highgui.imshow(winTitle, mat);
//            //opencv_highgui.waitKey(30);
//            canvas.showImage(convertToMat.convert(mat));
//            Thread.sleep(30);//50毫秒刷新一次图像
//        }
//    }
//
//    @Test
//    public void testCamera() throws InterruptedException, FrameGrabber.Exception {
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
//        grabber.setImageWidth(1280);
//        grabber.setImageHeight(720);
//        grabber.start();   //开始获取摄像头数据
//        showFrames("Camera", grabber);
//        grabber.stop();
//        grabber.close();
//    }
//
//    @Test
//    public void testCamera1() throws FrameGrabber.Exception, InterruptedException {
//        VideoInputFrameGrabber grabber = new VideoInputFrameGrabber(0);
//        grabber.start();   //开始获取摄像头数据
//        showFrames("Camera", grabber);
//        grabber.stop();
//        grabber.close();
//    }
//
//    @Test
//    public void testCamera2() throws FrameGrabber.Exception, InterruptedException, MalformedURLException {
//        IPCameraFrameGrabber grabber = new IPCameraFrameGrabber("http://admin:12345@192.0.0.64:554/MPEG-4/ch1/main/av_stream", 30, 30, TimeUnit.SECONDS);
//        grabber.start();
//        showFrames("IPCamera", grabber);
//        grabber.stop();
//        grabber.close();
//    }
//
//    @Test
//    public void testVideo() throws FrameGrabber.Exception, InterruptedException, MalformedURLException {
//        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault("rtmp://127.0.0.1:12580/live/app"); //这里也可以是本地文件，也可以网络文件。如：rtmp://127.0.0.1:12580/live/app
//        grabber.setImageWidth(1366);
//        grabber.setImageHeight(768);
//        grabber.start();
//        showFrames("Video", grabber);
//        grabber.stop();
//        grabber.close();
//    }
//
//    @Test
//    public void testFaceRecognize() throws FrameGrabber.Exception, InterruptedException, MalformedURLException, FrameRecorder.Exception {
//        OpenCVFrameGrabber grabber = OpenCVFrameGrabber.createDefault(0);
//        grabber.start();
//        showFramesWithFace("Video", grabber);
//        grabber.stop();
//        grabber.close();
//    }
//
//
//
//
//}