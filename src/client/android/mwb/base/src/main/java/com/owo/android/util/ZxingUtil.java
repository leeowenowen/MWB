package com.owo.android.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码生成器
 *
 * @blog http://sjsky.iteye.com
 * @author Michael
 */
public class ZxingUtil {

    /**
     * 生成二维码
     *
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public void encode(String contents, int width, int height, String imgPath) {
        Map<EncodeHintType, Object> hints = new Hashtable<>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageWriter.writeToStream(bitMatrix, "png",
                    new FileOutputStream(imgPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * 解析二维码
//     *
//     * @param imgPath
//     * @return
//     */
//    public String decode(String imgPath) {
//        BufferedImage image = null;
//        Result result = null;
//        try {
//            image = ImageIO.read(new File(imgPath));
//            if (image == null) {
//                System.out.println("the decode image may be not exit.");
//            }
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//
//            Map<DecodeHintType, Object> hints = new Hashtable<>();
//            hints.put(DecodeHintType.CHARACTER_SET, "GBK");
//
//            result = new MultiFormatReader().decode(bitmap, hints);
//            return result.getText();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}