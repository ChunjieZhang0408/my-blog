package com.example.config.generator;

import utils.Generator;

/**
 * @Desc BlogGenerator
 * @Author ZhangChunjie
 * @Date 2020/1/11 23:16
 * @Version 1.0
 */
public class BlogGenerator {
    static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mybatisplus?characterEncoding=utf8&useUnicode=true&serverTimezone=Asia/Shanghai";
    static String DB_USERNAME = "root";
    static String DB_PASSWORD = "root";
    public static void main(String[] args) {
        String outputDir ="D:\\code\\JavaCode\\GitHub\\my-blog\\my-blog\\src\\main\\java\\";
        System.out.println(outputDir);
        Generator.generate(outputDir,true,DB_URL,DB_USERNAME,DB_PASSWORD,"","com.example.mvc");
    }
}
