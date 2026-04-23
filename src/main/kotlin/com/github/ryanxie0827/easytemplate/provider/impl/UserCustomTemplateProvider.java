package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 用户自定义模板提供者，读取本地 ~/.easytemplate/custom.json 文件
 * @author: ryanxie0827
 */
public class UserCustomTemplateProvider implements TemplateProvider {

    // 定义本地配置文件的存放路径 (用户根目录下的 .easytemplate 文件夹)
    private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".easytemplate";
    private static final String CONFIG_FILE = CONFIG_DIR + File.separator + "custom.json";

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> customTemplates = new ArrayList<>();
        File file = new File(CONFIG_FILE);

        // 如果文件不存在，则自动初始化一个示例文件供用户参考
        if (!file.exists()) {
            createDefaultConfigFile(file);
            // 第一次生成默认文件后，返回空列表，不加载示例模板以免污染用户环境
            return customTemplates;
        }

        // 如果文件存在，使用 IDEA 内置的 Gson 读取并反序列化 JSON
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<TemplateData>>() {}.getType();
            List<TemplateData> parsedList = gson.fromJson(reader, listType);

            if (parsedList != null) {
                customTemplates.addAll(parsedList);
            }
        } catch (Exception e) {
            System.err.println("解析 EasyTemplate 自定义配置文件失败: " + e.getMessage());
        }

        return customTemplates;
    }

    /**
     * 在本地磁盘生成一个示例 JSON 配置文件，指导用户如何自定义模板
     *
     * @param file 目标文件对象
     */
    private void createDefaultConfigFile(File file) {
        File dir = new File(CONFIG_DIR);
        if (!dir.exists()) {
            dir.mkdirs(); // 创建多级目录
        }

        try (FileWriter writer = new FileWriter(file)) {
            // 提供一个示例 JSON 结构
            String demoJson = "[\n" +
                    "  {\n" +
                    "    \"name\": \"My Demo Vue\",\n" +
                    "    \"extension\": \"vue\",\n" +
                    "    \"isInclude\": false,\n" +
                    "    \"content\": \"<template>\\\\n  <div>${NAME}</div>\\\\n</template>\"\n" +
                    "  }\n" +
                    "]";
            writer.write(demoJson);
        } catch (IOException e) {
            System.err.println("无法创建 EasyTemplate 示例配置文件: " + e.getMessage());
        }
    }
}