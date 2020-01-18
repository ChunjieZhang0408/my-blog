/**
 * @Date:2019-09-24
 */
package com.example.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageUtils {

    private static final String PIC_OUT_TYPE_PNG = "png";

    private static Random random = new Random();
    //	去除相似I10o
    private static char[] ch = {'A', 'B', 'C', 'D', 'E', 'F', 'J', 'L', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9',};


    public static String generateCode(int textLength) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < textLength; i++) {
			int index = random.nextInt(ch.length);
			builder.append(ch[index]);
		}
        return builder.toString();
    }

    //	生成随机背景条纹
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    public static void captcha(HttpServletRequest request, HttpServletResponse response) {
        Random ran = new Random();
        int width = 100;
        int height = 40;
        int fontSize = height - 4;
        int verifySize = 4;
        // 创建图片对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取画布
        Graphics2D g = img.createGraphics();
        // 设置背景
        g.setColor(getRandColor(160, 200));
        g.fillRect(0, 0, width, height);
        // 写字
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
        // g.drawString(text,padding-left,padding-top);
        String code = generateCode(4);
        char[] chars = code.toCharArray();
        for (int i = 0; i < 4; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1),
                    (width / verifySize) * i + fontSize / 2, height / 2);
            g.setTransform(affine);
            g.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5, height / 2 + fontSize / 2 - 10);
        }

        g.dispose();
        request.getSession().setAttribute("vcode", code.toLowerCase());
        // 随机五条条线
        for (int i = 0; i < 5; i++) {
            g.drawLine(ran.nextInt(width), ran.nextInt(height), ran.nextInt(width), ran.nextInt(height));
        }

        // 添加噪点
        Random random = new Random();
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            img.setRGB(x, y, rgb);
        }
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("image/jpeg");
        // 4. 输出结果
        // 常见的压缩格式：jpeg(有损压缩), png(无损压缩) ...
        try {
            ImageIO.write(img, PIC_OUT_TYPE_PNG, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
