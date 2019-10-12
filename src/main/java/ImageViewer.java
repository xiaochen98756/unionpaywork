
import org.opencv.core.Mat;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by wangxiaochen on 2019/10/12.
 * 将OpenCV中的mat对象中的像素数据转成Java中的Image对象，
 * 然后使用Java的界面方法新建一个GUI，将这个Image对象显示出来
 */
public class ImageViewer {
    private JLabel imageView;

    private Mat image;
    private String windowName;

    /**
     * 如果使用junit测试时调用该方法，图像会一闪而过，可通过sleep()等方式暂时显示
     *
     * @param
     */

    public ImageViewer(Mat image) {
        this.image = image;
    }


    /**
     * @param image      要显示的mat
     * @param windowName 窗口标题
     */
    public ImageViewer(Mat image, String windowName) {
        this.image = image;
        this.windowName = windowName;
    }

    /**
     * 图片显示
     */
    public void imshow() {
        setSystemLookAndFeel();//使用什么样的页面展示风格
        Image loadedImage = toBufferedImage(image);
        JFrame frame = createJFrame(windowName, image.width(), image.height());
        imageView.setIcon(new ImageIcon(loadedImage));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 用户点击窗口关闭
    }

    private void setSystemLookAndFeel() {
        try {
//            1、Metal风格(默认)
//            String lookAndFeel ="javax.swing.plaf.metal.MetalLookAndFeel";
//            UIManager.setLookAndFeel(lookAndFeel);
//
//            2、Windows风格
//            String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//            UIManager.setLookAndFeel(lookAndFeel);
//
//            3、Windows Classic风格
//            String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
//            UIManager.setLookAndFeel(lookAndFeel);
//
//            4、Motif风格
//            String lookAndFeel ="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
//            UIManager.setLookAndFeel(lookAndFeel);
//
//            5、Mac风格 (需要在相关的操作系统上方可实现)
//            String lookAndFeel ="com.sun.java.swing.plaf.mac.MacLookAndFeel";
//            UIManager.setLookAndFeel(lookAndFeel);
//
//            6、GTK风格 (需要在相关的操作系统上方可实现)
//            String lookAndFeel ="com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
//            UIManager.setLookAndFeel(lookAndFeel);
//            7、可跨平台的默认风格
//            String lookAndFeel =UIManager.getCrossPlatformLookAndFeelClassName();
//            UIManager.setLookAndFeel(lookAndFeel);
//
//            8、当前系统的风格
//            String lookAndFeel =UIManager.getSystemLookAndFeelClassName();
//            UIManager.setLookAndFeel(lookAndFeel);

            String lookAndFeel ="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            UIManager.setLookAndFeel(lookAndFeel);
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private JFrame createJFrame(String windowName, int width, int height) {
        JFrame frame = new JFrame(windowName);
        imageView = new JLabel();
        final JScrollPane imageScrollPane = new JScrollPane(imageView);
        imageScrollPane.setPreferredSize(new Dimension(width, height));
        frame.add(imageScrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }


    private Image toBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer); // 获取所有的像素点
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }
}