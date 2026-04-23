package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 数据库 SQL、配置标记语言及文档模板提供者
 * @author: ryanxie0827
 */
public class DataAndDocTemplateProvider implements TemplateProvider {

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> list = new ArrayList<>();

        // SQL 脚本
        list.add(new TemplateData("SQL Script", "sql", false,
                "-- ==================================================\n" +
                        "-- @Description: ${DESCRIPTION}\n" +
                        "-- @Author: ${AUTHOR}\n" +
                        "-- @CreateTime: ${YEAR}-${MONTH}-${DAY}\n" +
                        "-- ==================================================\n\n"
        ));

        // 文档系列
        list.add(new TemplateData("Markdown File", "md", false,
                "# ${NAME}\n\n" +
                        "> **Description:** ${DESCRIPTION}  \n" +
                        "> **Author:** ${AUTHOR}  \n" +
                        "> **Date:** ${YEAR}-${MONTH}-${DAY}  \n\n" +
                        "## Overview\n\n"
        ));

        // 现代配置语言
        list.add(new TemplateData("YAML Config", "yml", false,
                "# Description: ${DESCRIPTION}\n" +
                        "# Author: ${AUTHOR}\n" +
                        "---\n"
        ));

        list.add(new TemplateData("TOML Config", "toml", false,
                "# Description: ${DESCRIPTION}\n" +
                        "# Author: ${AUTHOR}\n\n" +
                        "[default]\n"
        ));

        // .gitignore
        list.add(new TemplateData(".gitignore", "", false,
                "# Description: Git ignore file for ${PROJECT_NAME}\n\n" +
                        "# IDEA / JetBrains\n" +
                        ".idea/\n" +
                        "*.iml\n\n" +
                        "# Compiled class file\n" +
                        "*.class\n" +
                        "target/\n" +
                        "build/\n\n" +
                        "# OS generated files\n" +
                        ".DS_Store\n" +
                        "Thumbs.db\n"
        ));

        return list;
    }
}